package com.thn.springbootcms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Data
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] image;
    private String title;
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
    @OneToOne
    private Author author;
    private String description;
    private LocalDateTime dateTime;
    @ManyToMany
    private List<Tag> tags;

    @JsonIgnore
    public User getUser() {
        return user;
    }

    public String getDateTime() {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
