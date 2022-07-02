package com.thn.springbootcms.service;

import com.thn.springbootcms.entity.Author;
import com.thn.springbootcms.entity.User;
import com.thn.springbootcms.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public Author save(Author author) {
        return authorRepository.save(author);
    }

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public List<Author> findAuthorsByUser(User user) {
        return authorRepository.findAuthorsByUser(user);
    }
}
