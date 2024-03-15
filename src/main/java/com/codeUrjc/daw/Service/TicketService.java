package com.codeUrjc.daw.Service;

import com.codeUrjc.daw.Model.Ticket;
import com.codeUrjc.daw.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public void save(Ticket ticket) {
        ticketRepository.save(ticket);
    }
}