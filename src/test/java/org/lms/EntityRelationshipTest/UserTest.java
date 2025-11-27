package org.lms.EntityRelationshipTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.lms.Model.User;
import org.lms.Model.UserRole;
import org.hibernate.PropertyValueException;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;

@QuarkusTest
public class UserTest {
    @Inject
    EntityManager em;

    @Test
    @Transactional
    public void testUserCreation() {
        User user = new User("user1", "user1@gmail.com", UserRole.ADMIN, "user11", "password");
        em.persist(user);
        em.flush();
        
        assertNotNull(user.getId());
        assertEquals("user1", user.getName());
        assertEquals("user1@gmail.com", user.getEmail());
        assertEquals(UserRole.ADMIN, user.getRole());
        assertEquals("user11", user.getUsername());
        assertEquals("password", user.getPassword());
    }

    @Test
    @Transactional
    public void testEmailCannotBeNull() {
        PropertyValueException exception = assertThrows(PropertyValueException.class, () -> {
            User user = new User("Test User", null, UserRole.STUDENT, "testuseremailnull", "password123");
            em.persist(user);
        }, "Email should not be null");
        assertNotNull(exception);
    }

    @Test
    @Transactional
    public void testRoleCannotBeNull() {
        PropertyValueException exception = assertThrows(PropertyValueException.class, () -> {
            User user = new User("Test User", "testrole@example.com", null, "testuserrolenull", "password123");
            em.persist(user);
        }, "Role should not be null");
        assertNotNull(exception);
    }

    @Test
    @Transactional
    public void testUsernameCannotBeNull() {
        PropertyValueException exception = assertThrows(PropertyValueException.class, () -> {
            User user = new User("Test User", "testusername@example.com", UserRole.STUDENT, null, "password123");
            em.persist(user);
        }, "Username should not be null");
        assertNotNull(exception);
    }

    @Test
    @Transactional
    public void testPasswordCannotBeNull() {
        PropertyValueException exception = assertThrows(PropertyValueException.class, () -> {
            User user = new User("Test User", "testpassword@example.com", UserRole.STUDENT, "testuserpassnull", null);
            em.persist(user);
        }, "Password should not be null");
        assertNotNull(exception);
    }

    @Test
    @Transactional
    public void testNameCanNotBeNull() {
        PropertyValueException exception = assertThrows(PropertyValueException.class, () -> {
            User user = new User(null, "testnamenull@example.com", UserRole.STUDENT, "testusenamenull", "password123");
            em.persist(user);

        }, "Name cannot be null");
        assertNotNull(exception);
        
    }

    @Test
    @Transactional
    public void testUsernameUniqueness()  {
        User user1 = new User("User One", "user1unique@example.com", UserRole.STUDENT, "uniqueusername123", "password123");
        em.persist(user1);
        em.flush();
        User user2 = new User("User Two", "user2unique@example.com", UserRole.LECTURER, "uniqueusername123", "password456");
        em.persist(user2);
        
        PersistenceException exception = assertThrows(PersistenceException.class, () -> {
            em.flush();
        }, "Username should be unique");
        assertNotNull(exception);
    }

}
