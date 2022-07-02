package com.thn.springbootcms.repository;

import com.thn.springbootcms.entity.Author;
import com.thn.springbootcms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findAuthorsByUser(User user);
}
