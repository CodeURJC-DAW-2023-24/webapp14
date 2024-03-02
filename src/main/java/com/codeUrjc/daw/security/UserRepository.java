package com.codeUrjc.daw.security;

import com.codeUrjc.daw.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByNICK(String NICK);
    Optional<User> findById(Long id);

    List<User> findAll();


}
