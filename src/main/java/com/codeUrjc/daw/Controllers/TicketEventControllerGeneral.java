package com.codeUrjc.daw.Controllers;


import com.codeUrjc.daw.Model.Event;
import com.codeUrjc.daw.Model.User;
import com.codeUrjc.daw.Service.EventService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class TicketEventControllerGeneral {

    @Autowired
    private EventRepository eventRepository;
    private EventRepository eventService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    private java.util.Collections Collections;



    @GetMapping("/")
    public String showMain(Model model, HttpServletRequest request, Pageable page){

        model.addAttribute("admin", request.isUserInRole("ADMIN"));
        model.addAttribute("user", request.isUserInRole("USER"));

        Page<Event> events = eventRepository.findAll(page);

        model.addAttribute("events", events);
        model.addAttribute("hasNext", events.hasNext());
        model.addAttribute("nextPage", events.getNumber()+1);
        //model.addAttribute("prevPage", events.getNumber()-1);
        // Verifica si hay una página anterior antes de calcular su número
        if (events.getNumber() > 0) {
            model.addAttribute("hasPrev", true);
            model.addAttribute("prevPage", events.getNumber() - 1);
        } else {
            model.addAttribute("hasPrev", false);
            model.addAttribute("prevPage", 0); // O cualquier otro valor adecuado
        }


        return "index";
    }

    @GetMapping("/event/{id}")
    public String showEvent(Model model, @PathVariable long id) {

        Event event = eventService.findById(id).orElseThrow();

        model.addAttribute("event", event);

        return "showEvent";
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






    @GetMapping("/loginerror")
    public String showError(Model model){
        return "loginerror";
    }




}
