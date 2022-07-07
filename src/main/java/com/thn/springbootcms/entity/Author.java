package com.thn.springbootcms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Data
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] photo;
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
    private String firstName;
    private String lastName;
    private String additional;

    public String getFirstNameAndLastName() {
        return firstName + " " + lastName;
    }

    @JsonIgnore
    public User getUser() {
        return user;
    }
}
