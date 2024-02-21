package com.codeUrjc.daw.Services;

import com.codeUrjc.daw.Model.Event;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class EventService {
    private ConcurrentHashMap<Long, Event> events = new ConcurrentHashMap<>();
    private AtomicLong nextId = new AtomicLong();

    //Faltaría crear operacion de buscar todos los eventos y la de los precargados.
    public Event findById(long id){
        return events.get(id);
    }
    public void save(Event event){
        long id = nextId.getAndIncrement();
        this.events.remove(id);
    }
}
