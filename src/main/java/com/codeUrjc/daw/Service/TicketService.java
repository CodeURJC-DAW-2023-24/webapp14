package com.codeUrjc.daw.Service;

import com.codeUrjc.daw.Model.Comment;
import com.codeUrjc.daw.Model.Ticket;
import com.codeUrjc.daw.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public Collection<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    public Optional<Ticket> findById(long id) {
        return ticketRepository.findById(id);
    }

    public boolean exist(long id) {
        return ticketRepository.existsById(id);
    }

    public void delete(long id) {
        ticketRepository.deleteById(id);
    }

    public void save(Ticket ticket) {
        ticketRepository.save(ticket);
    }
}