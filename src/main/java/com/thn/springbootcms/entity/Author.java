package com.thn.springbootcms.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private byte[] photo;
    private String firstName;
    private String lastName;
    private String additional;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    public String getFirstNameAndLastName() {
        return firstName + " " + lastName;
    }
}
