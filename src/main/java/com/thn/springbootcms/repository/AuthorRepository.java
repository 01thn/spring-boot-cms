package com.thn.springbootcms.repository;

import com.thn.springbootcms.entity.Author;
import com.thn.springbootcms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findAuthorsByUser(User user);

    Optional<Author> findAuthorByFirstNameAndLastName(String firstname, String lastname);
}
