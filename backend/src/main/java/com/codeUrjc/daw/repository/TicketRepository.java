package com.codeUrjc.daw.repository;

import com.codeUrjc.daw.Model.Event;
import com.codeUrjc.daw.Model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TicketRepository  extends JpaRepository<Ticket, Long> {

}
