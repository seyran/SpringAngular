package com.seyrancom.consulting.test.integration;

import com.seyrancom.consulting.app.domain.embeddable.EmailAddress;
import com.seyrancom.consulting.app.domain.entity.User;
import com.seyrancom.consulting.app.service.UserService;
import com.seyrancom.consulting.test.core.AbstractSpringRunner;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class UserServiceImplTest extends AbstractSpringRunner {

    public static final String USERNAME = "test123";

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testFake() {

        assertTrue("is true", true);
    }
/*
    @Test
    public void testUpdateUserMaxCaloriesPerDay() {

        //userService.updateUserMaxCaloriesPerDay("test123", 300L);

        User user = findUserByUsername(USERNAME);
        assertNotNull(user);
        //assertTrue("The user calories where not updated: ");
    }
*/

/*    @Test
    public void testFindUserByUsername() {
        User user = findUserByUsername(USERNAME);
        assertNotNull("User is mandatory", user);
        assertTrue("Unexpected user " + user.getUsername(), user.getUsername().equals(USERNAME));
    }*/

    @Test
    public void testUserNotFound() {
        User user = findUserByUsername("doesnotexist");
        assertNull("User must be null", user);
    }

    @Test
    public void testCreateValidUser() {
        userService.createUser("test222", "test@gmail.com", "Password3");
        User user = findUserByUsername("test222");

        assertTrue("username not expected " + user.getUsername(), "test222".equals(user.getUsername()) );
        assertTrue("email not expected " + user.getEmail(), user.getEmail().equals(new EmailAddress("test@gmail.com")) );

        assertTrue("password not expected " + user.getPassword(), passwordEncoder.matches("Password3", user.getPassword().getValue()) );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBlankUser() {
        userService.createUser("", "test@gmail.com", "Password3");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUsernameLength() {
        userService.createUser("test", "test@gmail.com", "Password3");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUsernameAvailable() {
        userService.createUser("test123", "test@gmail.com", "Password3");
        userService.createUser("test123", "test@gmail.com", "Password3");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBlankEmail() {
        userService.createUser("test001", "", "Password3");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidEmail() {
        userService.createUser("test001", "test", "Password3");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBlankPassword() {
        userService.createUser("test002", "test@gmail.com", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPasswordPolicy() {
        userService.createUser("test003", "test@gmail.com", "Password");
    }


    private User findUserByUsername(String username) {
        //TypedQuery<User> query = entityManager.createQuery("select u from User u where username = :username", User.class);
        List<User> users = entityManager.createNamedQuery(User.FIND_BY_USERNAME, User.class).setParameter("username", username).getResultList();
        return users.size() == 1 ? users.get(0) : null;
    }


}