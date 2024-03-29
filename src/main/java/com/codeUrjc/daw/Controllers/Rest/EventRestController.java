package com.codeUrjc.daw.Controllers.Rest;

import com.codeUrjc.daw.Model.Event;
import com.codeUrjc.daw.Service.EventService;
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

    @GetMapping("/")
    public Collection<Event> getEvents(){
        return eventService.findAll();
    }

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

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Event createEvent(@RequestBody Event event){
        eventService.save(event);
        return event;
    }

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Event> deleteEvent(@PathVariable long id){
        try{
            eventService.delete(id);

            return new ResponseEntity<>(null, HttpStatus.OK);
        }catch (EmptyResultDataAccessException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}/image")
    public ResponseEntity<Object> uploadImage(@PathVariable long id, @RequestParam MultipartFile imageFile) throws IOException {

        Event event = eventService.findById(id).orElseThrow();

        URI location = fromCurrentRequest().build().toUri();

        event.setImage(true);
        event.setImageFile(BlobProxy.generateProxy(imageFile.getInputStream(), imageFile.getSize()));
        eventService.save(event);

        return ResponseEntity.created(location).build();
    }

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

    @DeleteMapping("/{id}/image")
    public ResponseEntity<Object> deleteImage(@PathVariable long id) throws IOException {

        Event event = eventService.findById(id).orElseThrow();

        event.setImageFile(null);
        event.setImage(false);

        eventService.save(event);

        return ResponseEntity.noContent().build();
    }

}
