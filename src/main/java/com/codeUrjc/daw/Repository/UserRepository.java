package com.codeUrjc.daw.Repository;

import com.codeUrjc.daw.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
