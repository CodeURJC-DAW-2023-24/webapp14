package com.codeUrjc.daw.Controllers.Rest;

import com.codeUrjc.daw.Model.Ticket;
import com.codeUrjc.daw.Service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/tickets")
public class TicketRestController {
    @Autowired
    private TicketService ticketService;

    @Operation(summary = "Get all tickets")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the tickets", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Ticket.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Tickets not found", content = @Content)
    })

    @GetMapping
    public Collection<Ticket> getTickets(){
        return ticketService.findAll();
    }

    @Operation(summary = "Get a ticket by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the ticket", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Ticket.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Ticket not found", content = @Content)
    })

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

    @Operation(summary = "Post a new ticket")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ticket created", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Ticket.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Ticket not created", content = @Content)
    })

    @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket){
        ticketService.save(ticket);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(ticket.getId())
                .toUri();
        return ResponseEntity.status(HttpStatus.CREATED).header("Location", location.toString()).body(ticket);
    }

    @Operation(summary = "Put a ticket by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ticket updated", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Ticket.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Ticket not update", content = @Content)
    })

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable long id, @RequestBody Ticket updateTicket) {
        if (updateTicket == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (ticketService.exist(id)) {
            updateTicket.setId(id);
            ticketService.save(updateTicket);
            return new ResponseEntity<>(updateTicket, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete a ticket by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ticket deleted", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Ticket.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Ticket not deleted", content = @Content)
    })

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