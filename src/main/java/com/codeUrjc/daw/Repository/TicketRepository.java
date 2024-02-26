package com.codeUrjc.daw.Repository;

import com.codeUrjc.daw.Model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, String> {
}
