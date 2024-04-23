package com.codeUrjc.daw.Controllers;

import com.codeUrjc.daw.Model.Event;
import com.codeUrjc.daw.Model.Ticket;
import com.codeUrjc.daw.Model.User;
import com.codeUrjc.daw.Service.EventService;
import com.codeUrjc.daw.Service.TicketService;
import com.codeUrjc.daw.repository.CommentRepository;
import com.codeUrjc.daw.repository.EventRepository;
import com.codeUrjc.daw.repository.UserRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

@Controller
public class TicketController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventService eventService;

    @Autowired
    private TicketService ticketService;

    @GetMapping("/inscription")
    public String showInscription(@RequestParam("id") Long eventId, Model model) {
        model.addAttribute("id", eventId);
        model.addAttribute("token", "your_csrf_token_here");
        return "inscription";
    }

    @PostMapping("/inscription")
    public ResponseEntity<byte[]> createTicket(@ModelAttribute Ticket ticket, @RequestParam("id") Long eventId, Principal principal) {
        String username = principal.getName();
        Optional<User> userOptional = userRepository.findByNICK(username);
        Optional<Event> eventOptional = eventRepository.findById(eventId);

        if (userOptional.isPresent() && eventOptional.isPresent()) {
            User user = userOptional.get();
            Event event = eventOptional.get();
            if (event.getMax_people() > event.getPeople_inscribed()) {
                int people = event.getPeople_inscribed() + 1;
                event.setPeople_inscribed(people);
                eventService.save(event);
                ticket.setUser(user);
                ticket.setEvent(event);

                ticketService.save(ticket);

                user.addEvent(event);
                userRepository.save(user);

                byte[] pdfContent = this.generatePdf(ticket.getName(), ticket.getEmail(), ticket.getSurname());
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_PDF);
                headers.setContentDispositionFormData("attachment", "inscription_details.pdf");
                return new ResponseEntity(pdfContent, headers, HttpStatus.OK);
            }
        }
        return null;
    }


    private byte[] generatePdf(String name, String email,String surname) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.newLineAtOffset(100, 700);
                contentStream.newLine();
                contentStream.showText("Detalles de la inscripción:");
                contentStream.newLine();
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Nombre: " + name);
                contentStream.newLineAtOffset(200, 0);
                contentStream.showText("Apellidos: " + surname);
                contentStream.newLineAtOffset(0, -40);
                contentStream.showText("Email: " + email);
                contentStream.newLine();
                contentStream.endText();
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            document.save(outputStream);
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
