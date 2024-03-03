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
                if(events.count() == 0) { // Verifica si la base de datos está vacía
                    save(new Event("Festival Cultural Universitario", "Un fin de semana lleno de actividades culturales, incluyendo actuaciones musicales, exposiciones de arte y presentaciones de danza", "URJC", "12-Oct", "2 dias"));

                    for(int i = 0; i < 100; i++) {
                        save(new Event("title" + i, "description" + i, "place" + i, "fecha" + i, "duration" + i));
                    }
                }
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

            public void save(Event event) {
                events.save(event);
            }
        }


