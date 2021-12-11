package com.beadando.demo.tantargy;


public class Tantargy {

    private String subjectName;

    private String subjectTime;

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
