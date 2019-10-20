package com.seyrancom.consulting.test.integration;

import com.seyrancom.consulting.app.repository.jpa.UserRepository;
import com.seyrancom.consulting.test.core.AbstractSpringRunner;
import com.sun.security.auth.UserPrincipal;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserRestWebServiceTest extends AbstractSpringRunner {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testStatus() throws Exception {
        getMockMvc().perform(get("/status")
                .contentType(MediaType.APPLICATION_JSON)
                .content("200")
                .accept(MediaType.APPLICATION_JSON)
                .principal(new UserPrincipal(UserServiceImplTest.USERNAME)))
                .andDo(print())
                .andExpect(status().isOk());

        //User user = userRepository.findByUsername(UserServiceTest.USERNAME).get();
        //assertTrue("max calories not updated" + user.getMaxCaloriesPerDay(), user.getMaxCaloriesPerDay() == 200);
    }
/*

    @Test
    public void testUpdateMaxCalories() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content("200")
                .accept(MediaType.APPLICATION_JSON)
                .principal(new UserPrincipal(UserServiceTest.USERNAME)))
                .andDo(print())
                .andExpect(status().isOk());

        User user = userRepository.findByUsername(UserServiceTest.USERNAME).get();
        //assertTrue("max calories not updated" + user.getMaxCaloriesPerDay(), user.getMaxCaloriesPerDay() == 200);
    }

    @Test
    public void testCreateUser() throws Exception {
        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"testing123\", \"plainTextPassword\": \"Password5\", \"email\": \"test@gmail.com\"}")
                .accept(MediaType.APPLICATION_JSON)
                .principal(new UserPrincipal(UserServiceTest.USERNAME)))
                .andDo(print())
                .andExpect(status().isOk());

        User user = userRepository.findByUsername("testing123").get();
        //assertTrue("email not correct: " + user.getEmail(), "test@gmail.com".equals(user.getEmail()));
    }
*/


}