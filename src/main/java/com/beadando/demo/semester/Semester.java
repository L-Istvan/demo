package com.beadando.demo.semester;

import javax.persistence.*;

@Entity
@Table(name = "Semesters")
public class Semester {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Column(nullable = false)
    private String subjects;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String semester;


    public Semester(){

    }

    public Semester(String semester, String subjects) {
        this.semester = semester;
        this.subjects = subjects;
    }

    public Semester(long id, String subjects, String username, String semester) {
        this.id = id;
        this.subjects = subjects;
        this.username = username;
        this.semester = semester;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
