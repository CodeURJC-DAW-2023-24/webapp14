package com.codeUrjc.daw.Controllers.Rest;

import com.codeUrjc.daw.Model.Event;
import com.codeUrjc.daw.Service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/api/events")
public class EventRestController {

    @Autowired
    private EventService eventService;

    @Operation(summary = "Get all events")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the events", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Event.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Events not found", content = @Content)
    })
    @GetMapping("/")
    public Collection<Event> getEvents(){
        return eventService.findAll();
    }

    @Operation(summary = "Get a event by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the event", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Event.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
           @ApiResponse(responseCode = "404", description = "Event not found", content = @Content)
    })

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEvent(@PathVariable long id){
        Optional<Event> op = eventService.findById(id);
        if (op.isPresent()){
            Event event = op.get();
            return new ResponseEntity<>(event, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Post a new event")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Event created", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Event.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Event not created", content = @Content)
    })
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Event createEvent(@RequestBody Event event){
        eventService.save(event);
        return event;
    }

    @Operation(summary = "Put a event by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event updated", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Event.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Event not update", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable long id, @RequestBody Event updateEvent) throws SQLException{
        if (eventService.exits(id)){
            if (updateEvent.getImage()){
                Event dbEvent = eventService.findById(id).orElseThrow();
                if (dbEvent.getImage()){
                    updateEvent.setImageFile(BlobProxy.generateProxy(dbEvent.getImageFile().getBinaryStream(), dbEvent.getImageFile().length()));
                }
            }
            updateEvent.setId(id);
            eventService.save(updateEvent);

            return  new ResponseEntity<>(updateEvent, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete a event by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event deleted", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Event.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Event not deleted", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Event> deleteEvent(@PathVariable long id){
        try{
            eventService.delete(id);

            return new ResponseEntity<>(null, HttpStatus.OK);
        }catch (EmptyResultDataAccessException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Post a image ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event image created", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Event.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Event image not created", content = @Content)
    })
    @PostMapping("/{id}/image")
    public ResponseEntity<Event> uploadEventImage(@PathVariable long id, @RequestParam("imageFile") MultipartFile imageFile) {
        try {
            Optional<Event> eventOptional = eventService.findById(id);
            if (eventOptional.isPresent()) {
                Event event = eventOptional.get();
                eventService.setEventImageFromMultipartFile(event, imageFile);
                eventService.save(event);
                return ResponseEntity.ok(event);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Operation(summary = "Put a image ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event image updated", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Event.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Event image not created", content = @Content)
    })
    @PutMapping("/{id}/image")
    public ResponseEntity<Event> updateEventImage(@PathVariable long id, @RequestParam("imageFile") MultipartFile imageFile) {
        try {
            Optional<Event> eventOptional = eventService.findById(id);
            if (eventOptional.isPresent()) {
                Event event = eventOptional.get();
                eventService.setEventImageFromMultipartFile(event, imageFile);
                eventService.save(event);
                return ResponseEntity.ok(event);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Operation(summary = "Get a image event by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event image found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Event.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Event image not found", content = @Content)
    })
    @GetMapping("/{id}/image")
    public ResponseEntity<Object> downloadImage(@PathVariable long id) throws SQLException {

        Event event = eventService.findById(id).orElseThrow();

        if (event.getImageFile() != null) {

            Resource file = new InputStreamResource(event.getImageFile().getBinaryStream());

            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                    .contentLength(event.getImageFile().length()).body(file);

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete a image event by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event image deleted", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Event.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Event not deleted", content = @Content)
    })
    @DeleteMapping("/{id}/image")
    public ResponseEntity<Object> deleteImage(@PathVariable long id) throws IOException {

        Event event = eventService.findById(id).orElseThrow();

        event.setImageFile(null);
        event.setImage(false);

        eventService.save(event);

        return ResponseEntity.noContent().build();
    }

}
