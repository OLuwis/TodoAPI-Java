package com.luwis.application.mutations;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureHttpGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.HttpGraphQlTester;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@AutoConfigureHttpGraphQlTester
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserSignup {
    
    @Autowired
    private HttpGraphQlTester tester;
    
    @Test
    void shouldReturnUserDetails() {
        String username = "Test";
        String email = "test1@gmail.com";
        String password = "123456Ab!";
        
        tester.documentName("userSignup")
        .variable("username", username)
        .variable("email", email)
        .variable("password", password)
        .execute()
        .path("$['data']['userSignup']", path -> {
            String responseUsername = path.path("['username']").entity(String.class).get();

            String responseEmail = path.path("['email']").entity(String.class).get();

            String responsePassword = path.path("['password']").entity(String.class).get();

            assertAll(
                "shouldReturnUserDetails",

                () -> assertEquals(responseUsername, username),
                () -> assertEquals(responseEmail, email),
                () -> assertTrue(new BCryptPasswordEncoder().matches(password, responsePassword))
            );
        });
    }
       
}