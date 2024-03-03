package com.codeUrjc.daw.Service;

import com.codeUrjc.daw.Model.Comment;
import com.codeUrjc.daw.Model.Event;
import com.codeUrjc.daw.repository.CommentRepository;
import com.codeUrjc.daw.repository.EventRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;


@Service
public class CommentService {

    @Autowired
    private CommentRepository comments;


    @PostConstruct
    public void init(){

        if(comments.count()==0){
            save(new Comment("opaaa","yipiiii"));

        }

    }





    public Collection<Comment> findAll() {
        return comments.findAll();
    }

    public Page<Comment> findAll(Pageable pageable) {
        return comments.findAll(pageable);
    }

    public Optional<Comment> findById(long id) {
        return comments.findById(id);
    }



    public void save(Comment comment) {
        comments.save(comment);


    }
}
