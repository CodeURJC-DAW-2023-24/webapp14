package com.codeUrjc.daw.Controllers;

import com.codeUrjc.daw.Model.Event;
import com.codeUrjc.daw.Model.User;
import com.codeUrjc.daw.Service.EventService;
import com.codeUrjc.daw.repository.EventRepository;
import com.codeUrjc.daw.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventService eventService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/dashboard")
    public String showDashboard(Model model, HttpServletRequest request, Principal principal){
        model.addAttribute("admin", request.isUserInRole("ADMIN"));
        model.addAttribute("user", request.isUserInRole("USER"));
        String username = principal.getName();

        Optional<User> userOptional = userRepository.findByNICK(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            boolean isEditor = user.isEditor();



            model.addAttribute("user", user);
            model.addAttribute("isEditor", isEditor);

            model.addAttribute("countEvents",eventRepository.count());
            model.addAttribute("countUsers",userRepository.count());
            model.addAttribute("countTecnologia", eventService.countTecnologia());
            model.addAttribute("countArtes", eventService.countArtes());
            model.addAttribute("countSanitario", eventService.countSanitario());
            model.addAttribute("countHumanidades", eventService.countHumanidades());

            return "dashboard";
        } else {

            return "error";
        }
    }

    @GetMapping("/login")
    public String showLogin(Model model){
        return "login";
    }

    @GetMapping("/loginerror")
    public String showError(Model model){
        return "loginerror";
    }

    @GetMapping("/profile")
    public String showProfile(Model model, HttpServletRequest request, Principal principal) {
        String username = principal.getName();
        Optional<User> userOptional = userRepository.findByNICK(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            List<Event> events = eventRepository.findByUsersContaining(user); // Obtener los eventos asociados al usuario

            boolean isEditor = user.isEditor();

            model.addAttribute("user", user);
            model.addAttribute("isEditor", isEditor);
            model.addAttribute("events", events); // Agregar los eventos al modelo

            return "profile";
        } else {
            return "error";
        }
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(User user) {
        user.setEncodedPassword(passwordEncoder.encode(user.getEncodedPassword()));
        user.setRoles(Collections.singletonList("USER"));
        user.setEditor(false);
        userRepository.save(user);
        return "login";
    }

    @GetMapping("/userPermissions")
    public String showUsuarios(Model model, HttpServletRequest request, Principal principal){
        model.addAttribute("admin", request.isUserInRole("ADMIN"));
        model.addAttribute("user", request.isUserInRole("USER"));
        String username = principal.getName();

        Optional<User> userOptional = userRepository.findByNICK(username);

        User user = userOptional.get();
        boolean isEditor = user.isEditor();

        model.addAttribute("user", user);
        model.addAttribute("isEditor", isEditor);

        List<User> allUsers = userRepository.findAll();
        model.addAttribute("allUsers", allUsers);

        return "userPermissions";
    }

    @GetMapping("/editForm")
    public String showEditForm(Model model, Principal principal) {
        String username = principal.getName();
        Optional<User> userOptional = userRepository.findByNICK(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            model.addAttribute("user", user);
            return "editForm";
        } else {
            return "error";
        }
    }

    @PostMapping("/editForm")
    public String saveEditedData(@ModelAttribute User updatedUser) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> userOptional = userRepository.findByNICK(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setName(updatedUser.getName());
            user.setSurname(updatedUser.getSurname());
            user.setEmail(updatedUser.getEmail());
            user.setStudyCenter(updatedUser.getStudyCenter());
            user.setPhone(updatedUser.getPhone());

            userRepository.save(user);

            return "redirect:/profile";
        } else {
            return "error";
        }
    }

    @PostMapping("/grantPermissions")
    public String grantPermissions(@RequestParam("id") Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (!user.isEditor()) {
                user.setEditor(true);
                userRepository.save(user);
            }
            return "redirect:/userPermissions";
        } else {
            return "error";
        }
    }

    @PostMapping("/removePermissions")
    public String removePermissions(@RequestParam("id") Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.isEditor()) {
                user.setEditor(false);
                userRepository.save(user);
            }

            return "redirect:/userPermissions";
        } else {

            return "error";
        }
    }

}
