package com.codeUrjc.daw.TicketEvent;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class TicketEventController {

    @GetMapping("/")
    public String showMain(Model model){

        return "index";
    }
}
