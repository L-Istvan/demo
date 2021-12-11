package com.beadando.demo.repository;

import com.beadando.demo.users.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface userRepository extends CrudRepository<User,Long> {

    public User findByUsername(String username);

    @Query(value = "SELECT ID FROM USERS WHERE USERNAME = ?1", nativeQuery = true)
    int findID(String username);

    @Query(value = "SELECT ROLE_ID FROM USERS_ROLES WHERE USER_ID = ?1", nativeQuery = true)
    int finduserRole(int id);

}
