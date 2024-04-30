package com.codeUrjc.daw.Service;

import com.codeUrjc.daw.Model.Event;
import com.codeUrjc.daw.Model.User;
import com.codeUrjc.daw.repository.EventRepository;
import com.codeUrjc.daw.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;


@Service

public class UserService {

    @Autowired
    private UserRepository users;
    public void save(User user) {

        users.save(user);
    }

    public Collection<User> findAll() {
        return users.findAll();
    }

    public Optional<User> findById(long id) {
        return users.findById(id);
    }

    public Optional<User> findByNICK(String nick) {return users.findByNICK(nick);}
}
