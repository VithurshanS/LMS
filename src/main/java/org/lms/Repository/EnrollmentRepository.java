package org.lms.Repository;

import java.util.UUID;

import org.lms.Model.Enrollment;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EnrollmentRepository implements PanacheRepositoryBase<Enrollment, UUID> {   
}
