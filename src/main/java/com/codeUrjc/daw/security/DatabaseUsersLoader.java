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
    	
    	userRepository.save(new User("user", passwordEncoder.encode("pass"), "USER"));
		userRepository.save(new User("admin", passwordEncoder.encode("adminpass"), "USER", "ADMIN"));
    }
}
