package com.codeUrjc.daw.Controllers.Rest;

import com.codeUrjc.daw.Model.Event;
import com.codeUrjc.daw.Model.User;
import com.codeUrjc.daw.Service.EventService;
import com.codeUrjc.daw.repository.UserRepository;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/api/events")
public class EventRestController {

    @Autowired
    private EventService eventService;

    @Autowired
    private UserRepository userRepository;

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
    public ResponseEntity<Event> createEvent(@RequestBody Event event){
        Optional<User> userOptional = userRepository.findByNICK(SecurityContextHolder.getContext().getAuthentication().getName());
        if (userOptional.isPresent() && userOptional.get().isEditor()) {
            eventService.save(event);
            URI location = fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(event.getId())
                    .toUri();
            return ResponseEntity.status(HttpStatus.CREATED).header("Location", location.toString()).body(event);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
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
        if (!eventService.exits(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Optional<User> userOptional = userRepository.findByNICK(SecurityContextHolder.getContext().getAuthentication().getName());
        if (userOptional.isPresent() && userOptional.get().isEditor()) {
            if (updateEvent.getImage()){
                Event dbEvent = eventService.findById(id).orElseThrow();
                if (dbEvent.getImage()){
                    updateEvent.setImageFile(BlobProxy.generateProxy(dbEvent.getImageFile().getBinaryStream(), dbEvent.getImageFile().length()));
                }
            }
            updateEvent.setId(id);
            eventService.save(updateEvent);

            return  new ResponseEntity<>(updateEvent, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN); // Return 403 Forbidden if the user is not an editor
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
                URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(event.getId())
                        .toUri();
                return ResponseEntity.status(HttpStatus.CREATED).header("Location", location.toString()).body(event);
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
                eventService.save(event); // Guardar el evento actualizado en la base de datos

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

    @Operation(summary = "Get recommended events for a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found recommended events", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Event.class)) }),
            @ApiResponse(responseCode = "404", description = "Recommended events not found", content = @Content)
    })
    @GetMapping("/{id}/recommendation")
    public ResponseEntity<Collection<Event>> getRecommendedEventsForUser(@PathVariable long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Collection<Event> recommendedEvents = eventService.findRecommendedEventsForUser(user);
            return ResponseEntity.ok(recommendedEvents);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Get number tecnology events ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found tecnology events", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Event.class)) }),
            @ApiResponse(responseCode = "404", description = "tecnology events not found", content = @Content)
    })
    @GetMapping("/tecnology")
    public ResponseEntity<Long> getTecnologyEvents() {
            long eventsTecnology = eventService.countTecnologia();
            if (eventsTecnology != 0 ){
                return ResponseEntity.ok(eventsTecnology);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @Operation(summary = "Get number Art events ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Art events", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Event.class)) }),
            @ApiResponse(responseCode = "404", description = "Art events not found", content = @Content)
    })
    @GetMapping("/art")
    public ResponseEntity<Long> getArtEvents() {
        long eventsArt = eventService.countArtes();
        if (eventsArt != 0 ){
            return ResponseEntity.ok(eventsArt);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @Operation(summary = "Get number Helth events ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Helth events", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Event.class)) }),
            @ApiResponse(responseCode = "404", description = "Helth events not found", content = @Content)
    })
    @GetMapping("/helth")
    public ResponseEntity<Long> getHelthEvents() {
        long eventsHelth = eventService.countSanitario();
        if (eventsHelth != 0 ){
            return ResponseEntity.ok(eventsHelth);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Get number Human events ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Human events", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Event.class)) }),
            @ApiResponse(responseCode = "404", description = "Human events not found", content = @Content)
    })
    @GetMapping("/human")
    public ResponseEntity<Long> getHumanEvents() {
        long eventsHuman = eventService.countHumanidades();
        if (eventsHuman != 0 ){
            return ResponseEntity.ok(eventsHuman);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Pageable Events ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found more events", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Event.class)) }),
            @ApiResponse(responseCode = "404", description = "More events not found", content = @Content)
    })
    @GetMapping("/pageableEvents")
    public ResponseEntity<List<Event>> loadMoreEvents(@RequestParam int page, @RequestParam int size) {
        try {
            Page<Event> eventsPage = eventService.findAll(PageRequest.of(page, size));
            List<Event> events = eventsPage.getContent();
            return ResponseEntity.ok(events);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }



}
