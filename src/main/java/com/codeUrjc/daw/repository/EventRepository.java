package com.codeUrjc.daw.repository;

import com.codeUrjc.daw.Model.Event;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAll();
    Optional<Event> findById(Long id);

    void deleteById(Long id);
}
