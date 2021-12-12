package com.beadando.demo.service;

import com.beadando.demo.users.User;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import static org.mockito.BDDMockito.*;
import static org.testng.Assert.*;

public class UserDetailTest {

    private static final Long USER_id = 1L;
    private static final String USER_lastname = "Lastname";
    private static final String USER_username = "Username";
    private static final String USER_password = "pass";
    private static final String USER_confirmpassword = "pass";
    private static final String USER_faculty = "1";



    @InjectMocks
    private UserDetail underTest;


    @Mock
    private User user;


    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterMethod
    public void tearDown() {
        underTest = null;
    }

    @Test
    public void testGetAuthorities() {

    }

    @Test
    public void testGetPassword() {
        User u = createUser();
        assertEquals(u.getPassword(),USER_password);

    }

    @Test
    public void testGetUsername() {
        User u = createUser();
        assertEquals(u.getUsername(),USER_username);
    }

    @Test
    public void testIsAccountNonExpired() {

    }

    @Test
    public void testIsAccountNonLocked() {
    }

    @Test
    public void testIsCredentialsNonExpired() {
    }

    @Test
    public void testIsEnabled() {
    }


    private User createUser(){
        User user = new User();
        user.setId(USER_id);
        user.setUsername(USER_username);
        user.setLastname(USER_lastname);
        user.setPassword(USER_password);
        user.setConfirmpassword(USER_confirmpassword);
        user.setFaculty(USER_faculty);
        return user;
    }

    Optional<User> createOptionalUser(){
        return Optional.of(createUser());
    }

}