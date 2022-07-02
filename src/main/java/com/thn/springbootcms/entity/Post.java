package com.thn.springbootcms.entity;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //    @ManyToOne(cascade = CascadeType.ALL)
//    private User user;
    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] image;
    private String title;
    private String description;
    private LocalDateTime dateTime;

    public String getDateTime() {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
