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

    @Column(name = "user_id", nullable = false)
    private UUID userId;



    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy="student")
    private List<Enrollment> studentEnrollments;

    public Student() {}

    public Student(UUID userId, Department department) {
        this.userId = userId;
        this.department = department;
    }

    public void setUserId(String userId) {
        this.userId = UUID.fromString(userId);
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

    public UUID getUserId() {
        return userId;
    }



    public Department getDepartment() {
        return department;
    }
}
