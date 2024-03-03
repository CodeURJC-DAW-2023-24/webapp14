package com.codeUrjc.daw.Service;

import com.codeUrjc.daw.Model.Event;
import com.codeUrjc.daw.repository.EventRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
@Service
public class EventService {
    @Autowired
    private EventRepository events;

    @PostConstruct
  public void init() {

        save(new Event("Festival Cultural Universitario", "Un fin de semana lleno de actividades culturales, incluyendo actuaciones musicales, exposiciones de arte y presentaciones de danza", "Campus Universitario, Universidad Rey Juan Carlos","5 al 7 de abril de 2024"," días"));



    }

    public Collection<Event> findAll() {
        return events.findAll();
    }

    public Page<Event> findAll(Pageable pageable) {
        return events.findAll(pageable);
    }


    public Optional<Event> findById(long id) {
        return events.findById(id);
    }

    public void save(Event post) {

        events.save(post);
    }

    public boolean exist(long id) {
        return events.existsById(id);
    }

    public void delete(long id) {
        events.deleteById(id);
    }

}
