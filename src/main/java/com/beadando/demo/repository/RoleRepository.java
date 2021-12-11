package com.beadando.demo.repository;

import com.beadando.demo.semester.Semester;
import com.beadando.demo.users.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role,Long> {

    Role findByRole(String role);

    @Query(value = "SELECT ROLE FROM ROLES WHERE ID = ?1", nativeQuery = true)
    String  findRole(int id);


}
