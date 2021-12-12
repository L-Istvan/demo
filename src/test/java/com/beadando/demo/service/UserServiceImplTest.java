package com.beadando.demo.service;

import static org.mockito.BDDMockito.*;
import static org.testng.Assert.assertEquals;
import com.beadando.demo.repository.userRepository;
import com.beadando.demo.users.User;
import org.mockito.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Optional;


public class UserServiceImplTest {

    private static final Long USER_id = 1L;
    private static final String USER_lastname = "Lastname";
    private static final String USER_username = "Username";
    private static final String USER_password = "pass";
    private static final String USER_confirmpassword = "pass";
    private static final String USER_faculty = "1";


    @InjectMocks
    private UserServiceImpl underTest;


    @Mock
    private userRepository userRepo;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);

    }

    @AfterMethod
    public void tearDown() {
        underTest = null;

    }

    @Test
    public void testRegisterUser() {
        User expected = createUser();
        given(userRepo.save(expected)).willReturn(expected);
        User actual = createUser();
        then(userRepo).should().save(expected);
        assertEquals(actual,expected);
    }

    @Test
    public void testLoadUserByUsername() {

        UserDetails userDetails;



    }

    @Test
    public void testFindByUsername() {
        //given
        User expected = createUser();
        given(userRepo.findByUsername(USER_username)).willReturn(expected);
        //when
        User actual = underTest.findByUsername(USER_username);
        //then
        then(userRepo).should().findByUsername(USER_username);
        assertEquals(actual,expected);
    }

    private User createUser(){
        User user = new User();
        user.setId(USER_id);
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