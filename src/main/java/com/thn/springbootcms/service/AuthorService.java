package com.thn.springbootcms.service;

import com.thn.springbootcms.entity.Author;
import com.thn.springbootcms.entity.User;
import com.thn.springbootcms.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public Optional<Author> save(Author author) {
        if (findAuthorByFirstNameAndLastName(author.getFirstName(), author.getLastName()).isPresent()) {
            return Optional.empty();
        }
        return Optional.of(authorRepository.save(author));
    }

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public List<Author> findAuthorsByUser(User user) {
        return authorRepository.findAuthorsByUser(user);
    }

    public Optional<Author> findAuthorByFirstNameAndLastName(String firstname, String lastname) {
        return authorRepository.findAuthorByFirstNameAndLastName(firstname, lastname);
    }
}
