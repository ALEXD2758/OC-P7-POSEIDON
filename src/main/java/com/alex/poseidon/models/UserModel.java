package com.alex.poseidon.models;

import com.alex.poseidon.config.ValidPassword;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class UserModel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    @Column(name = "username")
    @NotBlank(message = "Username is mandatory")
    private String username;
    @ValidPassword
    @NotBlank(message = "Password is mandatory")
    private String nonHashedPassword;
    @Column(name = "password")
    private String password;
    @Column(name = "fullname")
    @NotBlank(message = "FullName is mandatory")
    private String fullname;
    @Column(name = "role")
    @NotBlank(message = "Role is mandatory")
    private String role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNonHashedPassword() {
        return nonHashedPassword;
    }

    public void setNonHashedPassword(String nonHashedPassword) {
        this.nonHashedPassword = nonHashedPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}