package com.beadando.demo.repository;

import com.beadando.demo.semester.Semester;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface semesterRepository extends CrudRepository<Semester,Long> {

    public Semester findBysubjects (String subjects);
    @Query(value = "SELECT * FROM SEMESTERS WHERE USERNAME = ?1", nativeQuery = true)
    List<Semester> findSubjects(String username);


    Semester saveAndFlush(Semester semester);

}


