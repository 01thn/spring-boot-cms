package com.thn.springbootcms.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Lob
    private byte[] photo;
    private String firstName;
    private String lastName;
    private String additionalInfo;
}
