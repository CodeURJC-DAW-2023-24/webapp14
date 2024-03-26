package com.codeUrjc.daw.Controllers;


import com.codeUrjc.daw.Model.Comment;
import com.codeUrjc.daw.Model.Event;
import com.codeUrjc.daw.Model.Ticket;
import com.codeUrjc.daw.Model.User;
import com.codeUrjc.daw.Service.CommentService;
import com.codeUrjc.daw.Service.EventService;
import com.codeUrjc.daw.Service.UserService;
import com.codeUrjc.daw.repository.CommentRepository;
import com.codeUrjc.daw.repository.EventRepository;
import com.codeUrjc.daw.repository.UserRepository;
import com.codeUrjc.daw.Service.TicketService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import javax.print.DocFlavor;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class TicketEventControllerGeneral {

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private java.util.Collections Collections;
    @Autowired
    private TicketService ticketService;

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

    @GetMapping("/dashboard")
    public String showDashboard(Model model, HttpServletRequest request, Principal principal){
        model.addAttribute("admin", request.isUserInRole("ADMIN"));
        model.addAttribute("user", request.isUserInRole("USER"));
        String username = principal.getName();

        Optional<User> userOptional = userRepository.findByNICK(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            boolean isEditor = user.isEditor();

            model.addAttribute("user", user);
            model.addAttribute("isEditor", isEditor);

            model.addAttribute("countEvents",eventRepository.count());
            model.addAttribute("countUsers",userRepository.count());

            return "dashboard";
        } else {

            return "error";
        }
    }

    @GetMapping("/eventos")
    public String showEventos(Model model, HttpServletRequest request, Principal principal){
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

            return "eventos";
        } else {
            return "error";
        }
    }

    @GetMapping("/login")
    public String showLogin(Model model){
        return "login";
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
    public String createNewEvent(@ModelAttribute Event event) {
        eventService.save(event);
        return "redirect:/";
    }

    @GetMapping("/profile")
    public String showProfile(Model model, HttpServletRequest request, Principal principal){
        model.addAttribute("admin", request.isUserInRole("ADMIN"));
        model.addAttribute("user", request.isUserInRole("USER"));
        String username = principal.getName();

        Optional<User> userOptional = userRepository.findByNICK(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            boolean isEditor = user.isEditor();

            model.addAttribute("user", user);
            model.addAttribute("isEditor", isEditor);


            return "profile";
        } else {
            return "error";
        }
    }

    @GetMapping("/registrar")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registrar";
    }

    @PostMapping("/registrar")
    public String registerUser(User user) {
        user.setEncodedPassword(passwordEncoder.encode(user.getEncodedPassword()));
        user.setRoles(Collections.singletonList("USER"));
        user.setEditor(false);
        userRepository.save(user);
        return "login";
    }

    @GetMapping("/permisosUsuarios")
    public String showUsuarios(Model model, HttpServletRequest request, Principal principal){
        model.addAttribute("admin", request.isUserInRole("ADMIN"));
        model.addAttribute("user", request.isUserInRole("USER"));
        String username = principal.getName();

        Optional<User> userOptional = userRepository.findByNICK(username);

        User user = userOptional.get();
        boolean isEditor = user.isEditor();

        model.addAttribute("user", user);
        model.addAttribute("isEditor", isEditor);

        List<User> allUsers = userRepository.findAll();
        model.addAttribute("allUsers", allUsers);

        return "permisosUsuarios";
    }

    @GetMapping("/editForm")
    public String showEditForm(Model model, Principal principal) {
        String username = principal.getName();
        Optional<User> userOptional = userRepository.findByNICK(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            model.addAttribute("user", user);
            return "editForm";
        } else {
            return "error";
        }
    }

    @PostMapping("/editForm")
    public String saveEditedData(@ModelAttribute User updatedUser) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> userOptional = userRepository.findByNICK(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setName(updatedUser.getName());
            user.setSurname(updatedUser.getSurname());
            user.setEmail(updatedUser.getEmail());
            user.setStudyCenter(updatedUser.getStudyCenter());
            user.setPhone(updatedUser.getPhone());

            userRepository.save(user);

            return "redirect:/profile";
        } else {
            return "error";
        }
    }

    @GetMapping("/editEvent")
    public String showEditEvent(@RequestParam("id") Long eventId, Model model) {
        Optional<Event> eventOptional = eventService.findById(eventId);

        if (eventOptional.isPresent()) {
            Event event = eventOptional.get();
            model.addAttribute("event", event);
            return "editEvent";
        } else {
            return "error";
        }
    }

    @PostMapping("/editEvent")
    public String saveEditedEvent(@ModelAttribute Event updatedEvent, @RequestParam("id") Long id) {
        Optional<Event> eventOptional = eventRepository.findById(id);

        if (eventOptional.isPresent()) {
            Event event = eventOptional.get();
            event.setTitle(updatedEvent.getTitle());
            event.setDate(updatedEvent.getDate());
            event.setDuration(updatedEvent.getDuration());
            event.setPlace(updatedEvent.getPlace());
            event.setDescription(updatedEvent.getDescription());

            eventRepository.save(event);

            return "redirect:/eventos";
        } else {
            return "error";
        }
    }

    @GetMapping("/loginerror")
    public String showError(Model model){
        return "loginerror";
    }

    @PostMapping("/deleteEvent")
    public String deleteEvent(@RequestParam Long id) {
        eventService.deleteById(id);
        return "redirect:/eventos";
    }

    @PostMapping("/otorgarPermisos")
    public String otorgarPermisos(@RequestParam("id") Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (!user.isEditor()) {
                user.setEditor(true);
                userRepository.save(user);
            }
            return "redirect:/permisosUsuarios";
        } else {
            return "error";
        }
    }

    @PostMapping("/quitarPermisos")
    public String quitarPermisos(@RequestParam("id") Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.isEditor()) {
                user.setEditor(false);
                userRepository.save(user);
            }

            return "redirect:/permisosUsuarios";
        } else {

            return "error";
        }
    }

    @GetMapping("/load-more-events")
    @ResponseBody
    public List<Event> loadMoreEvents(@RequestParam int page, @RequestParam int size) {
        Page<Event> eventsPage = eventService.findAll(PageRequest.of(page, size));
        return eventsPage.getContent();
    }

    @GetMapping("/review")
    public String showReview(Model model, HttpServletRequest request){
        boolean isAdmin = request.isUserInRole("ADMIN");
        boolean isUser = request.isUserInRole("USER");

        model.addAttribute("admin", isAdmin);
        model.addAttribute("user", isUser);

        if (isAdmin || isUser) {
            String currentUserNickname = request.getUserPrincipal().getName();
            List<Comment> userComments = commentRepository.findByUserNICK(currentUserNickname);
            model.addAttribute("userComments", userComments);
        }
        return "review";
    }

    @GetMapping("/CreateReview")
    public String showCreateReview(@RequestParam("id") Long eventId,Model model, HttpServletRequest request, Principal principal){

        model.addAttribute("id", eventId);
        model.addAttribute("token", "your_csrf_token_here");
        model.addAttribute("admin", request.isUserInRole("ADMIN"));
        model.addAttribute("user", request.isUserInRole("USER"));
        String username = principal.getName();

        Optional<User> userOptional = userRepository.findByNICK(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            boolean isEditor = user.isEditor();

            model.addAttribute("user", user);
            model.addAttribute("isEditor", isEditor);

            return "redirect:/event/" + eventId;
        } else {
            return "error";
        }
    }

    @PostMapping("/CreateReview")
    public String registerReview(@ModelAttribute Comment comment,@RequestParam("id") Long eventid, Model model, Principal principal) {
        String nick = principal.getName();
        Optional<User> userOptional = userRepository.findByNICK(nick);
        Optional<Event> eventOptional = eventRepository.findById(eventid);
        if(userOptional.isPresent() && eventOptional.isPresent()){
            User user = userOptional.get();
            Event event = eventOptional.get();

            comment.setEvent(event);
            comment.setUser(user);
            comment.setNick(nick);

            commentService.save(comment);

        }
        return "redirect:/event/" + eventid;
    }

    @GetMapping("/inscripcion")
    public String showInscripcion(@RequestParam("id") Long eventId, Model model) {
        model.addAttribute("id", eventId);
        model.addAttribute("token", "your_csrf_token_here");
        return "inscripcion";
    }

    @PostMapping("/inscripcion")
    public ResponseEntity<byte[]> createTicket(@ModelAttribute Ticket ticket, @RequestParam("id") Long eventId, Principal principal) {
        String username = principal.getName();
        Optional<User> userOptional = userRepository.findByNICK(username);
        Optional<Event> eventOptional = eventRepository.findById(eventId);

        if (userOptional.isPresent() && eventOptional.isPresent()) {
            User user = userOptional.get();
            Event event = eventOptional.get();
            if (event.getMax_people() > event.getPeople_inscribed()) {
                int people = event.getPeople_inscribed() + 1;
                event.setPeople_inscribed(people);
                eventService.save(event);
                ticket.setUser(user);
                ticket.setEvent(event);

                ticketService.save(ticket);

                byte[] pdfContent = this.generatePdf(ticket.getName(), ticket.getEmail(), ticket.getSurname());
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_PDF);
                headers.setContentDispositionFormData("attachment", "inscription_details.pdf");
                return new ResponseEntity(pdfContent, headers, HttpStatus.OK);
            }
        }
        return null;
    }

    private byte[] generatePdf(String name, String email,String surname) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.newLineAtOffset(100, 700);
                contentStream.newLine();
                contentStream.showText("Detalles de la inscripción:");
                contentStream.newLine();
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Nombre: " + name);
                contentStream.newLineAtOffset(200, 0);
                contentStream.showText("Apellidos: " + surname);
                contentStream.newLineAtOffset(0, -40);
                contentStream.showText("Email: " + email);
                contentStream.newLine();
                contentStream.endText();
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            document.save(outputStream);
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}