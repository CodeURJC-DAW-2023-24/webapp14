package com.codeUrjc.daw.repository;

import com.codeUrjc.daw.Model.Category;
import com.codeUrjc.daw.Model.Event;
import com.codeUrjc.daw.Model.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAll();
    Optional<Event> findById(Long id);

    void deleteById(Long id);

    List<Event> findByUsersContaining(User user);

    List<Event> findByCategory(Category category);

}
