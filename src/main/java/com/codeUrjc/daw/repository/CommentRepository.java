package com.codeUrjc.daw.repository;

import com.codeUrjc.daw.Model.Comment;
import com.codeUrjc.daw.Model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAll();

}
