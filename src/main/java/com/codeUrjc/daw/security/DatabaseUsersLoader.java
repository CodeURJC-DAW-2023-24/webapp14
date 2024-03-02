package com.codeUrjc.daw.security;

import com.codeUrjc.daw.Model.User;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseUsersLoader {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    private void initDatabase() {

        userRepository.save(new User ("user","pepito","perez","pepitoperez@gmail.com", passwordEncoder.encode("pass"),"URJC",666666666,"USER"));
        userRepository.save(new User ("admin","almudena","lopez","almulopez@gmail.com", passwordEncoder.encode("adminpass"),"URJC",666777777,"USER", "ADMIN"));
    }
}