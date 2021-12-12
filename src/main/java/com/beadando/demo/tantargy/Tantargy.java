package com.beadando.demo.tantargy;


import javax.persistence.*;

@Entity
@Table(name = "Tantargyak")
public class Tantargy {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Column(nullable = false)
    private String subjectName;

    @Column(nullable = false)
    private String subjectTime;

    @Column(nullable = false)
    private String sem;



    public Tantargy(String subjectName, String subjectTime, String sem) {
        this.subjectName = subjectName;
        this.subjectTime = subjectTime;
        this.sem = sem;
    }

    public String getSem() {
        return sem;
    }

    public void setSem(String sem) {
        this.sem = sem;
    }

    public Tantargy(){

    }

    public String getSubjectAndTime(){
        String x = subjectName + "    " + subjectTime;
        return x;
    }

    public Tantargy(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectTime() {
        return subjectTime;
    }

    public void setSubjectTime(String subjectTime) {
        this.subjectTime = subjectTime;
    }
}
