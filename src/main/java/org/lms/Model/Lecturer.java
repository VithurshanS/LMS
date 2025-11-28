package org.lms.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="lecturer")
public class Lecturer {

    @Id
    @GeneratedValue
    @Column(name = "lecturer_id")
    private UUID id;

    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "lecturer", cascade = CascadeType.ALL)
    private List<Module> teachingModules = new ArrayList<>();

    
    public Lecturer (){}

    public Lecturer(User user, Department department) {
        if (user != null && user.getRole() != UserRole.LECTURER) {
            throw new IllegalArgumentException("User is not a lecturerr");
        }
        this.user = user;
        this.department = department;
    }

    public void setUser(User user) {
        if (user != null && user.getRole() != UserRole.LECTURER) {
            throw new IllegalArgumentException("User is not a lecturer");
        }
        this.user = user;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Module> getTeachingModules() {
        return teachingModules;
    }

    public void setTeachingModules(List<Module> teachingModules) {
        this.teachingModules = teachingModules;
    }

    public UUID getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Department getDepartment() {
        return department;
    }

}
