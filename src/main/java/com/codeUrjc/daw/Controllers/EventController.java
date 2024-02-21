package com.codeUrjc.daw.Controllers;

import com.codeUrjc.daw.Services.EventService;
import com.codeUrjc.daw.Model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EventController {
    @Autowired
    private EventService eventService;
    @PostMapping("/event/new")
    public String newEvent(Model model, Event event){
        eventService.save(event);
        return "eventos";
    }

}
