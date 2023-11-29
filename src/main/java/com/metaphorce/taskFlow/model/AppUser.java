package com.metaphorce.taskFlow.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "AppUser")
public class AppUser {

    @Id
    private String username;
    @Column
    @NotBlank(message = "Name is mandatory")
    private String name;
    @Column
    @NotBlank(message = "password is mandatory")
    @Size(min = 8, message = "The password should be at least 8 characters")
    private String password;
    @Column
    @NotBlank(message = "Team is mandatory")
    private String team;
    @Column
    @NotBlank(message = "Role is mandatory")
    private String role;

    public AppUser() { }
    public AppUser(String username, String name, String password, String team, String role) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.team = team;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public Role getRole() {
        return Role.valueOf(role);
    }

    public void setRole(Role role) {
        this.role = role.toString();
    }
}
