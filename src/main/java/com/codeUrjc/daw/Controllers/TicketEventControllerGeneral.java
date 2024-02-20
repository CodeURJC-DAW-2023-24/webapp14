package com.codeUrjc.daw.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TicketEventControllerGeneral {

   @GetMapping("/")
    public String showMain(Model model){

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
    @GetMapping("/usuarios")
    public String showUsuarios(Model model){

        return "usuarios";
    }

}
