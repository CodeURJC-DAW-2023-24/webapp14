package com.codeUrjc.daw.Controllers;


import com.codeUrjc.daw.Model.Comment;
import com.codeUrjc.daw.Model.Event;
import com.codeUrjc.daw.Model.Ticket;
import com.codeUrjc.daw.Model.User;
import com.codeUrjc.daw.Service.CommentService;
import com.codeUrjc.daw.Service.EventService;
import com.codeUrjc.daw.repository.CommentRepository;
import com.codeUrjc.daw.repository.EventRepository;
import com.codeUrjc.daw.repository.UserRepository;
import com.codeUrjc.daw.service.TicketService;
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

        // Crear un objeto PageRequest con el número de página 0 y el tamaño de página 10
        PageRequest pageRequest = PageRequest.of(0, 10);

        // Utilizar el objeto PageRequest para obtener la primera página de eventos
        Page<Event> events = eventService.findAll(pageRequest);

        model.addAttribute("events", events.getContent());

        return "index";
    }

    @GetMapping("/event/{id}")
    public String showEvent(Model model, HttpServletRequest request, @PathVariable long id) {

        model.addAttribute("admin", request.isUserInRole("ADMIN"));
        model.addAttribute("user", request.isUserInRole("USER"));

        Optional<Event> optionalEvent = eventService.findById(id);
        if(optionalEvent.isPresent()){
            Event event = optionalEvent.get();
            model.addAttribute("event", event);

            return "showEvent";
        }else {
            return "error";
        }

    }





    @GetMapping("/dashboard")
    public String showDashboard(Model model, HttpServletRequest request, Principal principal){
        model.addAttribute("admin", request.isUserInRole("ADMIN"));
        model.addAttribute("user", request.isUserInRole("USER"));
        String username = principal.getName(); // Obtener el nombre de usuario autenticado

        // Buscar el usuario en la base de datos por su NICK
        Optional<User> userOptional = userRepository.findByNICK(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            boolean isEditor = user.isEditor();

            // Agregar la información del usuario y si es editor al modelo
            model.addAttribute("user", user);
            model.addAttribute("isEditor", isEditor);

            // Aquí puedes agregar otros atributos al modelo según sea necesario
            model.addAttribute("countEvents",eventRepository.count());
            model.addAttribute("countUsers",userRepository.count());

            return "dashboard";
        } else {
            // Manejar el caso en el que el usuario no exista en la base de datos
            return "error"; // O devuelve a una página de error
        }
    }

    @GetMapping("/eventos")
    public String showEventos(Model model, HttpServletRequest request, Principal principal){
        model.addAttribute("admin", request.isUserInRole("ADMIN"));
        model.addAttribute("user", request.isUserInRole("USER"));
        String username = principal.getName(); // Obtener el nombre de usuario autenticado

        // Buscar el usuario en la base de datos por su NICK
        Optional<User> userOptional = userRepository.findByNICK(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            boolean isEditor = user.isEditor();

            // Agregar la información del usuario y si es editor al modelo
            model.addAttribute("user", user);
            model.addAttribute("isEditor", isEditor);


            // Aquí puedes agregar otros atributos al modelo según sea necesario
            // Obtener la lista de todos los usuarios
            List<Event> allEvents = eventRepository.findAll();
            model.addAttribute("allEvents", allEvents);

            return "eventos";
        } else {
            // Manejar el caso en el que el usuario no exista en la base de datos
            return "error"; // O devuelve a una página de error
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
        String username = principal.getName(); // Obtener el nombre de usuario autenticado

        // Buscar el usuario en la base de datos por su NICK
        Optional<User> userOptional = userRepository.findByNICK(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            boolean isEditor = user.isEditor();

            // Agregar la información del usuario y si es editor al modelo
            model.addAttribute("user", user);
            model.addAttribute("isEditor", isEditor);

            // Aquí puedes agregar otros atributos al modelo según sea necesario

            return "NewEvent";
        } else {
            // Manejar el caso en el que el usuario no exista en la base de datos
            return "error"; // O devuelve a una página de error
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
        String username = principal.getName(); // Obtener el nombre de usuario autenticado

        // Buscar el usuario en la base de datos por su NICK
        Optional<User> userOptional = userRepository.findByNICK(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            boolean isEditor = user.isEditor();

            // Agregar la información del usuario y si es editor al modelo
            model.addAttribute("user", user);
            model.addAttribute("isEditor", isEditor);

            // Aquí puedes agregar otros atributos al modelo según sea necesario

            return "profile";
        } else {
            // Manejar el caso en el que el usuario no exista en la base de datos
            return "error"; // O devuelve a una página de error
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
        String username = principal.getName(); // Obtener el nombre de usuario autenticado

        // Buscar el usuario en la base de datos por su NICK
        Optional<User> userOptional = userRepository.findByNICK(username);

        User user = userOptional.get();
        boolean isEditor = user.isEditor();

        // Agregar la información del usuario y si es editor al modelo
        model.addAttribute("user", user);
        model.addAttribute("isEditor", isEditor);

        // Obtener la lista de todos los usuarios
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
            // Actualizar los datos del usuario con los valores recibidos del formulario
            user.setName(updatedUser.getName());
            user.setSurname(updatedUser.getSurname());
            user.setEmail(updatedUser.getEmail());
            user.setStudyCenter(updatedUser.getStudyCenter());
            user.setPhone(updatedUser.getPhone());

            // Guardar los cambios en la base de datos
            userRepository.save(user);

            return "redirect:/profile"; // Redirigir al perfil después de guardar los cambios
        } else {
            return "error"; // O devuelve a una página de error si el usuario no existe
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
        // Obtener el ID del evento actualizado
        Optional<Event> eventOptional = eventRepository.findById(id);

        if (eventOptional.isPresent()) {
            Event event = eventOptional.get();
            // Actualizar los datos del evento con los valores recibidos del formulario
            event.setTitle(updatedEvent.getTitle());
            event.setFecha(updatedEvent.getFecha());
            event.setDuration(updatedEvent.getDuration());
            event.setPlace(updatedEvent.getPlace());
            event.setDescription(updatedEvent.getDescription()); // Asegúrate de actualizar la descripción también

            eventRepository.save(event);

            return "redirect:/eventos"; // Redirigir al perfil después de guardar los cambios
        } else {
            return "error"; // O devuelve a una página de error si el evento no existe
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
                user.setEditor(true); // Otorgar permisos de editor al usuario
                userRepository.save(user); // Guardar el usuario actualizado en la base de datos
            }
            // Redirigir a la página de permisos de usuario o a donde desees
            return "redirect:/permisosUsuarios";
        } else {
            // Manejar el caso en el que el usuario no exista en la base de datos
            return "error"; // O devuelve a una página de error
        }
    }

    @PostMapping("/quitarPermisos")
    public String quitarPermisos(@RequestParam("id") Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.isEditor()) {
                user.setEditor(false); // Otorgar permisos de editor al usuario
                userRepository.save(user); // Guardar el usuario actualizado en la base de datos
            }
            // Redirigir a la página de permisos de usuario o a donde desees
            return "redirect:/permisosUsuarios";
        } else {
            // Manejar el caso en el que el usuario no exista en la base de datos
            return "error"; // O devuelve a una página de error
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
            List<Comment> allComments = commentRepository.findAll();
            model.addAttribute("allComments", allComments);
        }

        return "review";
    }

    @GetMapping("/CreateReview")
    public String showCreateReview(Model model, HttpServletRequest request, Principal principal){

        model.addAttribute("admin", request.isUserInRole("ADMIN"));
        model.addAttribute("user", request.isUserInRole("USER"));
        String username = principal.getName(); // Obtener el nombre de usuario autenticado

        // Buscar el usuario en la base de datos por su NICK
        Optional<User> userOptional = userRepository.findByNICK(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            boolean isEditor = user.isEditor();

            // Agregar la información del usuario y si es editor al modelo
            model.addAttribute("user", user);
            model.addAttribute("isEditor", isEditor);

            // Aquí puedes agregar otros atributos al modelo según sea necesario

            return "createReview";
        } else {
            // Manejar el caso en el que el usuario no exista en la base de datos
            return "error"; // O devuelve a una página de error
        }


    }
    @PostMapping("/CreateReview")
    public String registerReview(@ModelAttribute Comment comment, Model model, Principal principal) {
        // Obtener el nombre de usuario del objeto Principal
        String nick = principal.getName();

        // Establecer el nick del usuario en el comentario
        comment.setNick(nick);

        // Guardar el comentario en la base de datos
        commentService.save(comment);

        return "redirect:/";
    }


    @GetMapping("/inscripcion")
    public String showInscripcion(Model model){

        return "inscripcion";
    }

    @PostMapping("/inscripcion")
    public ResponseEntity<byte[]> createTicket(@ModelAttribute Ticket ticket) {
        // Guardar el ticket en la base de datos
        ticketService.save(ticket);

        // Generar el PDF con los detalles de la inscripción
        byte[] pdfContent = generatePdf(ticket.getName(), ticket.getEmail(),ticket.getSurname());

        // Configurar los encabezados de la respuesta para indicar que se está enviando un archivo PDF
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "inscription_details.pdf");

        // Devolver una respuesta con el contenido del PDF y los encabezados configurados
        return new ResponseEntity<>(pdfContent, headers, HttpStatus.OK);
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
