package com.codeUrjc.daw.Services;

import com.codeUrjc.daw.Model.Event;
import com.codeUrjc.daw.Repository.EventRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    @Autowired
    private EventRepository events;

    @PostConstruct
    public void init(){

    }

    public void save (Event event){
        events.save(event);
    }
}
