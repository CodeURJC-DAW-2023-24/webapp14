package com.codeUrjc.daw.repository;

import com.codeUrjc.daw.Model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

}
