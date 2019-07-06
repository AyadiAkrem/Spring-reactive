package com.itdeep.reactive.entities;


import lombok.Data;
import org.springframework.data.annotation.Id;


@org.springframework.data.mongodb.core.mapping.Document
@Data
public class User {

    public String firstName;
    public String lastName;
    public String email;
    public boolean active;
    @Id
    private String id;
    private String password;

    public User() {
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = String.format("%s.%s@itdeep.com", firstName, lastName);
    }
}
