package com.beadando.demo.repository;

import com.beadando.demo.semester.Semester;
import org.springframework.data.repository.CrudRepository;

public interface semesterRepository extends CrudRepository<Semester,Long> {

    public Semester findBySemester(String semester);

    Semester saveAndFlush(Semester semester);
    //public Semester findByID(long id);
}
