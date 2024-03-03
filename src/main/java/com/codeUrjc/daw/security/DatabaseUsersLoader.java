package com.codeUrjc.daw.security;

import com.codeUrjc.daw.Model.User;
import com.codeUrjc.daw.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseUsersLoader {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    private void initDatabase() {
        // Verificar si la base de datos ya contiene usuarios
        List<User> existingUsers = userRepository.findAll();

        if (existingUsers.isEmpty()) {
            // La base de datos está vacía, insertar los usuarios por defecto
            userRepository.save(new User("user", "pepito", "perez", "pepitoperez@gmail.com", passwordEncoder.encode("pass"), "URJC", 666666666, false, "USER"));
            userRepository.save(new User ("editor","pepita","garrido","pepatoperez@gmail.com", passwordEncoder.encode("pass"),"URJC",666666688,true,"USER"));
            userRepository.save(new User("admin", "almudena", "lopez", "almulopez@gmail.com", passwordEncoder.encode("adminpass"), "URJC", 666777777, true, "USER", "ADMIN"));
        }
    }
}