package com.codeUrjc.daw.Controllers;

import com.codeUrjc.daw.Model.Ticket;
import com.codeUrjc.daw.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/tickets")
public class TicketRestController {
    @Autowired
    private TicketService ticketService;

    @GetMapping
    public Collection<Ticket> getTickets(){
        return ticketService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable long id){
        Optional<Ticket> optionalTicket = ticketService.findById(id);
        if(optionalTicket.isPresent()){
            Ticket ticket = optionalTicket.get();
            return new ResponseEntity<>(ticket, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ticket createTicket(@RequestBody Ticket ticket){
        ticketService.save(ticket);
        return ticket;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable long id,@RequestBody Ticket updateTicket){
        if(ticketService.exist(id)) {
            updateTicket.setId(id);
            ticketService.save(updateTicket);
            return new ResponseEntity<>(updateTicket, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Ticket> deleteTicket(@PathVariable long id) {
        try{
            ticketService.delete(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}