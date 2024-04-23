package com.codeUrjc.daw.Controllers;

import com.codeUrjc.daw.Model.Comment;
import com.codeUrjc.daw.Model.Event;
import com.codeUrjc.daw.Model.User;
import com.codeUrjc.daw.Service.CommentService;
import com.codeUrjc.daw.repository.CommentRepository;
import com.codeUrjc.daw.repository.EventRepository;
import com.codeUrjc.daw.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CommentService commentService;


    @GetMapping("/review")
    public String showReview(Model model, HttpServletRequest request){
        boolean isAdmin = request.isUserInRole("ADMIN");
        boolean isUser = request.isUserInRole("USER");

        model.addAttribute("admin", isAdmin);
        model.addAttribute("user", isUser);

        if (isAdmin || isUser) {
            String currentUserNickname = request.getUserPrincipal().getName();
            List<Comment> userComments = commentRepository.findByUserNICK(currentUserNickname);
            model.addAttribute("userComments", userComments);
        }
        return "review";
    }

    @GetMapping("/CreateReview")
    public String showCreateReview(@RequestParam("id") Long eventId, Model model, HttpServletRequest request, Principal principal){

        model.addAttribute("id", eventId);
        model.addAttribute("token", "your_csrf_token_here");
        model.addAttribute("admin", request.isUserInRole("ADMIN"));
        model.addAttribute("user", request.isUserInRole("USER"));
        String username = principal.getName();

        Optional<User> userOptional = userRepository.findByNICK(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            boolean isEditor = user.isEditor();

            model.addAttribute("user", user);
            model.addAttribute("isEditor", isEditor);

            return "redirect:/event/" + eventId;
        } else {
            return "error";
        }
    }

    @PostMapping("/CreateReview")
    public String registerReview(@ModelAttribute Comment comment, @RequestParam("id") Long eventid, Model model, Principal principal) {
        String nick = principal.getName();
        Optional<User> userOptional = userRepository.findByNICK(nick);
        Optional<Event> eventOptional = eventRepository.findById(eventid);
        if(userOptional.isPresent() && eventOptional.isPresent()){
            User user = userOptional.get();
            Event event = eventOptional.get();

            comment.setEvent(event);
            comment.setUser(user);
            comment.setNick(nick);

            commentService.save(comment);

        }
        return "redirect:/event/" + eventid;
    }
}
