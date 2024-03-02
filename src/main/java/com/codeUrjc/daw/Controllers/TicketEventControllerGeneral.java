package com.codeUrjc.daw.Controllers;


import com.codeUrjc.daw.Model.User;
import com.codeUrjc.daw.security.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Optional;

@Controller
public class TicketEventControllerGeneral {

    @Autowired
    private UserRepository userRepository;

   @GetMapping("/")
    public String showMain(Model model, HttpServletRequest request){

       model.addAttribute("admin", request.isUserInRole("ADMIN"));
       model.addAttribute("user", request.isUserInRole("USER"));
       return "index";
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
    public String showRegistrar(Model model){

        return "registrar";
    }
    @GetMapping("/permisosUsuarios")
    public String showUsuarios(Model model, HttpServletRequest request, Principal principal){
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

            return "permisosUsuarios";
        } else {
            // Manejar el caso en el que el usuario no exista en la base de datos
            return "error"; // O devuelve a una página de error
        }
    }

    @GetMapping("/loginerror")
    public String showError(Model model){
        return "loginerror";
    }

    //La idea es que este controler se asocie a la dashboard para que solo la pueda el admin ver esa pagina
    /*@GetMapping("/dashboard")
    public String privatePage(com.codeUrjc.daw.Model model, HttpServletRequest request){

       String name = request.getUserPrincipal().getName();

        User user = userRepository.findByName(name).orElseThrow();

        model.addAttribute("username", user.getName());
        model.addAttribute("admin", request.isUserInRole("ADMIN"));

       return "dashboard";
    }*/



}
