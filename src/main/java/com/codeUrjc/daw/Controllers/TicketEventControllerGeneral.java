package com.codeUrjc.daw.Controllers;


import com.codeUrjc.daw.security.User;
import com.codeUrjc.daw.security.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
    public String showDashboard(Model model){

        return "dashboard";
    }

    @GetMapping("/eventos")
    public String showEventos(Model model){

        return "eventos";
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
    public String showNewEvent(Model model){

        return "NewEvent";
    }

    @GetMapping("/profile")
    public String showProfile(Model model){

        return "profile";
    }

    @GetMapping("/registrar")
    public String showRegistrar(Model model){

        return "registrar";
    }
    @GetMapping("/permisosUsuarios")
    public String showUsuarios(Model model){

        return "permisosUsuarios";
    }

    @GetMapping("/loginerror")
    public String showError(Model model){
        return "loginerror";
    }

    //La idea es que este controler se asocie a la dashboard para que solo la pueda el admin ver esa pagina
    /*@GetMapping("/dashboard")
    public String privatePage(Model model, HttpServletRequest request){

       String name = request.getUserPrincipal().getName();

        User user = userRepository.findByName(name).orElseThrow();

        model.addAttribute("username", user.getName());
        model.addAttribute("admin", request.isUserInRole("ADMIN"));

       return "dashboard";
    }*/



}
