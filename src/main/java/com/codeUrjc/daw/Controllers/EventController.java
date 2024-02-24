package com.codeUrjc.daw.Controllers;


import com.codeUrjc.daw.Model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EventController {

    @PostMapping("/eventos/new")
    public String newEvent(Model model, Event event, @RequestParam("date") String dateStr, @RequestParam("hour") String hourStr, @RequestParam("duration") String durationStr, @RequestParam("n_tickets") String n_ticketsStr) {


    return "eventos";


    }
}
