package com.codeUrjc.daw.Controllers;

import com.codeUrjc.daw.Model.Comment;
import com.codeUrjc.daw.Model.Event;
import com.codeUrjc.daw.Model.User;
import com.codeUrjc.daw.Service.EventService;
import com.codeUrjc.daw.repository.CommentRepository;
import com.codeUrjc.daw.repository.EventRepository;
import com.codeUrjc.daw.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Controller
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private EventService eventService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String showMain(Model model, HttpServletRequest request){
        model.addAttribute("admin", request.isUserInRole("ADMIN"));
        model.addAttribute("user", request.isUserInRole("USER"));
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<Event> events = eventService.findAll(pageRequest);
        model.addAttribute("events", events.getContent());
        return "index";
    }

    @GetMapping("/event/{id}")
    public String showEvent(@PathVariable long id, Model model, HttpServletRequest request) {
        model.addAttribute("admin",request.isUserInRole("ADMIN"));
        model.addAttribute("user",request.isUserInRole("USER"));

        Optional<Event> eventOptional = eventRepository.findById(id);
        List<Comment> allComments = commentRepository.findAll();
        model.addAttribute("allComments", allComments);

        if (eventOptional.isPresent()) {
            Event event = eventOptional.get();
            model.addAttribute("event", event);

            List<Comment> eventComments = commentRepository.findByEventId(id);
            model.addAttribute("eventComments", eventComments);

            return "showEvent";
        } else {
            return "error";
        }
    }

    @GetMapping("/events")
    public String showEvents(Model model, HttpServletRequest request, Principal principal){
        model.addAttribute("admin", request.isUserInRole("ADMIN"));
        model.addAttribute("user", request.isUserInRole("USER"));
        String username = principal.getName();

        Optional<User> userOptional = userRepository.findByNICK(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            boolean isEditor = user.isEditor();

            model.addAttribute("user", user);
            model.addAttribute("isEditor", isEditor);

            List<Event> allEvents = eventRepository.findAll();
            model.addAttribute("allEvents", allEvents);

            return "events";
        } else {
            return "error";
        }
    }

    @GetMapping("/NewEvent")
    public String showNewEvent(Model model, HttpServletRequest request, Principal principal){
        model.addAttribute("admin", request.isUserInRole("ADMIN"));
        model.addAttribute("user", request.isUserInRole("USER"));
        String username = principal.getName();

        Optional<User> userOptional = userRepository.findByNICK(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            boolean isEditor = user.isEditor();

            model.addAttribute("user", user);
            model.addAttribute("isEditor", isEditor);


            return "NewEvent";
        } else {
            return "error";
        }
    }

    @PostMapping("/NewEvent")
    public String createNewEvent(@ModelAttribute Event event, @RequestParam("imageField") MultipartFile imageFile) {
        try {
            if (!imageFile.isEmpty()) {
                event.setImageFile(BlobProxy.generateProxy(imageFile.getInputStream(), imageFile.getSize()));
                event.setImage(true); // Asegúrate de setear el atributo 'image' como true
            }
            eventService.save(event);
            return "redirect:/";
        } catch (IOException e) {
            return "error";
        }
    }




    @GetMapping("/editEvent")
    public String showEditEvent(@RequestParam("id") Long eventId, Model model, Principal principal, HttpServletRequest request) {
        Optional<Event> eventOptional = eventService.findById(eventId);
        model.addAttribute("admin", request.isUserInRole("ADMIN"));
        model.addAttribute("user", request.isUserInRole("USER"));
        String username = principal.getName();

        Optional<User> userOptional = userRepository.findByNICK(username);

        if (eventOptional.isPresent() && userOptional.isPresent()) {
            User user = userOptional.get();
            boolean isEditor = user.isEditor();
            model.addAttribute("user", user);
            model.addAttribute("isEditor", isEditor);
            Event event = eventOptional.get();
            model.addAttribute("event", event);
            return "editEvent";
        } else {
            return "error";
        }
    }

    @PostMapping("/editEvent")
    public String saveEditedEvent(@ModelAttribute Event updatedEvent, @RequestParam("id") Long id, boolean removeImage, MultipartFile imageField) throws  IOException, SQLException {
        Optional<Event> eventOptional = eventRepository.findById(id);

        if (eventOptional.isPresent()) {
            Event event = eventOptional.get();
            event.setTitle(updatedEvent.getTitle());
            event.setDate(updatedEvent.getDate());
            event.setDuration(updatedEvent.getDuration());
            event.setPlace(updatedEvent.getPlace());
            event.setDescription(updatedEvent.getDescription());

            updateImage(updatedEvent, removeImage, imageField);

            eventRepository.save(event);

            return "redirect:/events";
        } else {
            return "error";
        }
    }

    @PostMapping("/deleteEvent")
    public String deleteEvent(@RequestParam Long id) {
        eventService.delete(id);
        return "redirect:/events";
    }

    @GetMapping("/load-more-events")
    @ResponseBody
    public List<Event> loadMoreEvents(@RequestParam int page, @RequestParam int size) {
        Page<Event> eventsPage = eventService.findAll(PageRequest.of(page, size));
        return eventsPage.getContent();
    }

    @GetMapping("/events/{id}/image")
    public ResponseEntity<Object> downloadImage(@PathVariable long id) throws SQLException{
        Optional<Event> event = eventService.findById(id);
        if (event.isPresent() && event.get().getImageFile() != null){
            Resource file = new InputStreamResource(event.get().getImageFile().getBinaryStream());

            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg").contentLength(event.get().getImageFile().length()).body(file);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    private void updateImage(Event event, boolean removeImage, MultipartFile imageField) throws IOException, SQLException {

        if (imageField != null && !imageField.isEmpty()) {
            event.setImageFile(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));
            event.setImage(true);
        } else {
            if (removeImage) {
                event.setImageFile(null);
                event.setImage(false);
            } else {
                // Mantener la misma imagen cargándola antes de actualizar el evento
                Event dbEvent = eventService.findById(event.getId()).orElseThrow();
                if (dbEvent.getImage()) {
                    event.setImageFile(BlobProxy.generateProxy(dbEvent.getImageFile().getBinaryStream(),
                            dbEvent.getImageFile().length()));
                    event.setImage(true);
                }
            }
        }
    }



}
