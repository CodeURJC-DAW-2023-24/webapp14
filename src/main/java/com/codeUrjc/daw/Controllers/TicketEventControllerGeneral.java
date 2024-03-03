package com.codeUrjc.daw.Controllers;


import com.codeUrjc.daw.Model.Comment;
import com.codeUrjc.daw.Model.Event;
import com.codeUrjc.daw.Model.User;
import com.codeUrjc.daw.Service.CommentService;
import com.codeUrjc.daw.Service.EventService;
import com.codeUrjc.daw.repository.CommentRepository;
import com.codeUrjc.daw.repository.EventRepository;
import com.codeUrjc.daw.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.print.DocFlavor;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class TicketEventControllerGeneral {

    @Autowired
    private EventService eventService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    private java.util.Collections Collections;



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

            return "eventos";
        } else {
            // Manejar el caso en el que el usuario no exista en la base de datos
            return "error"; // O devuelve a una página de error
        }
    }

    @GetMapping("/inscripcion")
    public String showInscripcion(Model model){

        return "inscripcion";
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
    public String showeditEvent(Model model){

        return "editEvent";
    }

    @GetMapping("/loginerror")
    public String showError(Model model){
        return "loginerror";
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
    public String showReview(Model model){
        return "review";
    }








    @GetMapping("/CreateReview")
    public String showCreateReview(Model model, HttpServletRequest request, Principal principal){


        return "createReview";


    }

    @PostMapping("/CreateReview")
    public String registerReview(@ModelAttribute Comment comment){

        comment.setNick(comment.getNick());
        comment.setDescription(comment.getDescription());


        commentService.save(comment);
        return "redirect:/";



    }








}
