package org.lms.Repository;

import java.util.UUID;

import org.lms.Model.Lecturer;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LecturerRepository implements PanacheRepositoryBase<Lecturer, UUID> {
}
