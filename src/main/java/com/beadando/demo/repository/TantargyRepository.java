package com.beadando.demo.repository;

import com.beadando.demo.semester.Semester;
import com.beadando.demo.tantargy.Tantargy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TantargyRepository extends CrudRepository<Tantargy,Long> {

    @Query(value = "SELECT * FROM TANTARGYAK WHERE SEM = ?1", nativeQuery = true)
    List<Tantargy> findTantargyak(String sem);

}
