package com.beadando.demo.service;

import com.beadando.demo.repository.userRepository;
import com.beadando.demo.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private userRepository userrRepo;

    private User user;

    @Autowired
    public UserServiceImpl(userRepository userrRepo){
        this.userrRepo = userrRepo;
    }


    @Override
    public void registerUser(User user) {
        User u = userrRepo.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if( user == null ){
            throw new UsernameNotFoundException(username);
        }
        return new UserDetail(user);
    }

    @Override
    public User findByUsername(String username){
        return userrRepo.findByUsername(username);
    }


}
