package org.lms.EntityRelationshipTest;

import org.junit.jupiter.api.Test;
import org.lms.Model.Department;



import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@QuarkusTest
public class DepartmentTest {
    @Inject
    EntityManager em;

    @Test
    @Transactional
    public void DepartmentCreationTest(){
        Department dept = new Department("Mathematics");
        em.persist(dept);
    }
    
}
