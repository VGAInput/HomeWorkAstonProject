package com.paskhinproject.HomeWorkAstonProject.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "application_users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;

    public AppUser(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
