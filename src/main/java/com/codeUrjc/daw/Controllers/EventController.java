package com.codeUrjc.daw.Controllers;


import com.codeUrjc.daw.Model.Event;
import com.codeUrjc.daw.Services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class EventController {
    @Autowired
    private EventService eventService;

    @PostMapping("/eventos/new")
    public String newEvent(Model model, Event event, @RequestParam("date") String dateStr, @RequestParam("hour") String hourStr, @RequestParam("duration") String durationStr, @RequestParam("n_tickets") String n_ticketsStr) {



            // Guardar el evento
            eventService.save(event);

            // Redirigir a la página de eventos
            return "eventos";

    }
}
