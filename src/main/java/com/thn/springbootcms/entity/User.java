package com.thn.springbootcms.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @Size(min = 5, max = 25)
    private String username;
    private String realName;
    @NotNull
    @Size(min = 5, max = 25)
    @Pattern(regexp = "([a-zA-Z]|\\d){6,}", message = "Incorrect format")
    private String password;
}
