package com.beadando.demo.repository;

import com.beadando.demo.users.User;
import org.springframework.data.repository.CrudRepository;


public interface userRepository extends CrudRepository<User,Long> {

    public User findByUsername(String username);

}
