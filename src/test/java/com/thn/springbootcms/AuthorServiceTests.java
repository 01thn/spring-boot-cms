package com.thn.springbootcms;

import com.thn.springbootcms.entity.Author;
import com.thn.springbootcms.entity.User;
import com.thn.springbootcms.repository.AuthorRepository;
import com.thn.springbootcms.service.AuthorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTests {
    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    private User user;

    private Author author;


    @BeforeEach
    public void setup() {
        user = new User();
        user.setId(1L);
        user.setUsername("Testtest");
        user.setEmail("test@test.com");
        user.setPassword("testtest");

        author = new Author();
        author.setId(1L);
        author.setFirstName("Test");
        author.setLastName("Test");
        author.setUser(user);

        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void saveAnEmployeeTwiceButGetOneSuccess() {
        when(authorRepository.save(author)).thenReturn(author);
        when(authorRepository.findAuthorByFirstNameAndLastName(author.getFirstName(), author.getLastName())).thenReturn(Optional.empty());
        var savedAuthor1 = authorService.save(author);
        assertThat(savedAuthor1).isPresent();
        when(authorRepository.findAuthorByFirstNameAndLastName(author.getFirstName(), author.getLastName())).thenReturn(Optional.of(author));
        var savedAuthor2 = authorService.save(author);
        assertThat(savedAuthor2).isEmpty();
    }
}
