package com.codeUrjc.daw.Repository;

import com.codeUrjc.daw.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
