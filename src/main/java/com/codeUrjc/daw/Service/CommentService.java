package com.codeUrjc.daw.Service;

import com.codeUrjc.daw.Model.Comment;
import com.codeUrjc.daw.Model.Event;
import com.codeUrjc.daw.repository.CommentRepository;
import com.codeUrjc.daw.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CommentService {

    @Autowired
    private CommentRepository comments;

    public void save(Comment comment) {
        comments.save(comment);
    }
}
