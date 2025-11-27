package org.lms.Repository;

import java.util.UUID;

import org.lms.Model.Student;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StudentRepository implements PanacheRepositoryBase<Student, UUID> {   
}
