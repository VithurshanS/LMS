package org.lms.Model;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue
    @Column(name = "student_id")
    private UUID id;

    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy="student")
    private List<Enrollment> studentEnrollments;

    public Student() {}

    public Student(User user, Department department) {
        if (user != null && user.getRole() != UserRole.STUDENT) {
            throw new IllegalArgumentException("User is not a student");
        }
        this.user = user;
        this.department = department;
    }

    public void setUser(User user) {
        if (user != null && user.getRole() != UserRole.STUDENT) {
            throw new IllegalArgumentException("User is not a student");
        }
        this.user = user;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Enrollment> getStudentEnrollments() {
        return studentEnrollments;
    }

    public void setStudentEnrollments(List<Enrollment> studentEnrollments) {
        this.studentEnrollments = studentEnrollments;
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
