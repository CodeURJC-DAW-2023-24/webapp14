package com.codeUrjc.daw.repository;

import com.codeUrjc.daw.Model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
