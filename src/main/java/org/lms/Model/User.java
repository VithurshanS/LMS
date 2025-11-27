package org.lms.Model;

import java.util.UUID;

import jakarta.persistence.*;



@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private UUID id;

    @Column(nullable=false)
    private String name;
    @Column(nullable=false)
    private String email;

    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(unique = true, nullable= false)
    private String username;
    @Column(nullable = false)
    private String password;

    public User (){}
    
    public User(String name, String email, UserRole role, String username, String password) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.username = username;
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setRole(UserRole role) {
        this.role = role;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
        public UUID getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public UserRole getRole() {
        return role;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }



    
}
