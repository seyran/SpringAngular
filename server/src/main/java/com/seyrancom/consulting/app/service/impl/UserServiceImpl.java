package com.seyrancom.consulting.app.service.impl;

import com.seyrancom.consulting.app.domain.embeddable.Password;
import com.seyrancom.consulting.app.domain.entity.User;
import com.seyrancom.consulting.app.repository.jpa.UserRepository;
import com.seyrancom.consulting.app.service.UserService;
import com.seyrancom.consulting.app.domain.embeddable.EmailAddress;
import com.seyrancom.consulting.app.domain.enums.UserRole;
import com.seyrancom.consulting.core.service.AbstractService;
import com.seyrancom.consulting.core.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static com.seyrancom.consulting.app.service.util.ValidationUtils.assertMinimumLength;
import static com.seyrancom.consulting.app.service.util.ValidationUtils.assertNotBlank;

/**
 * Business service for User entity related operations
 */

@AppService
public class UserServiceImpl extends AbstractService implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * creates a new user in the database
     *
     * @param username - the username of the new user
     * @param email    - the user email
     * @param password - the user plain text password
     */
    @Override
    @Transactional
    public void createUser(String username, String email, String password) {

        assertNotBlank(username, "Username cannot be empty.");
        assertMinimumLength(username, 6, "Username must have at least 6 characters.");
        EmailAddress.assertEmail(email);
        Password.assertPassword(password);

        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("The username is already exist.");
        }

        User user = new User(username, new Password(password, passwordEncoder.encode(password)), UserRole.USER, new EmailAddress(email));

        userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username).get();
    }

/*    @Transactional(readOnly = true)
    public Long findTodaysCaloriesForUser(String username) {
        return userRepository.findTodaysCaloriesForUser(username);
    }*/

}