package com.beadando.demo.service;


import com.beadando.demo.users.User;

public interface UserService {

    public void registerUser(User user);

    public User findByUsername(String username);
}
