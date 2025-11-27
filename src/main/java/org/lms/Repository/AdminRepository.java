package org.lms.Repository;

import java.util.UUID;

import org.lms.Model.Admin;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AdminRepository implements PanacheRepositoryBase<Admin, UUID> {   
}
