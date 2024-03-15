package com.codeUrjc.daw.Service;

import com.codeUrjc.daw.Model.Event;
import com.codeUrjc.daw.Model.User;
import com.codeUrjc.daw.repository.EventRepository;
import com.codeUrjc.daw.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service

public class UserService {

    @Autowired
    private UserRepository users;
    public void save(User user) {

        users.save(user);
    }
}
