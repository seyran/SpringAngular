package com.seyrancom.consulting.app.service.impl;

import com.seyrancom.consulting.app.domain.entity.User;
import com.seyrancom.consulting.app.domain.enums.UserRole;
import com.seyrancom.consulting.app.service.UserService;
import com.seyrancom.consulting.core.service.AbstractService;
import com.seyrancom.consulting.core.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

/**
 * UserDetails service that reads the user credentials from the database, using a JPA repository.
 */
@AppService
public class UserDetailsServiceImpl extends AbstractService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByUsername(username);

        if (user == null) {
            String message = "Username not found" + username;
            logger.info(message);
            throw new UsernameNotFoundException(message);
        }
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(UserRole.USER.getLabel()));

        logger.info("Found user in database: " + user);

        return new org.springframework.security.core.userdetails.User(username, user.getPassword().getValue(), roles);
        //return new org.springframework.security.core.userdetails.User(username, user.getPasswordDigest(), authorities);
    }

    /*List<User> findAllUsers();

    @Secured("ADMIN")
    void updateUser(User user);

    @Secured({ "ROLE_DBA", "ADMIN" })
    void deleteUser();*/

/*
    @PostAuthorize ("returnObject.enums == authentication.name")
    User findById(int id);

    @PreAuthorize("hasRole('ADMIN')")
    void updateUser(User user);

    @PreAuthorize("hasRole('ADMIN') AND hasRole('DBA')")
    void deleteUser(int id);
*/

}