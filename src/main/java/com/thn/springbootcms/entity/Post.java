package com.thn.springbootcms.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    private User user;
    @Lob
    private byte[] photo;
    private byte[] image;
    private String title;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Author> author;
    private ZonedDateTime dateTime;
}
