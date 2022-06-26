package com.thn.springbootcms.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    private User user;
    @Lob
    private byte[] image;
    private String title;
    private String description;
    private LocalDateTime dateTime;
}
