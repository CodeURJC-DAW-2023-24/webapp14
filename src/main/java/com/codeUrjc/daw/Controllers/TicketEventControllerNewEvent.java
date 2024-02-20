package com.codeUrjc.daw.Controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class TicketEventControllerNewEvent {
    @GetMapping("/NewEvent")
    public String showMain(Model model){

        return "NewEvent";
    }
}
