package com.example.intistattt.model;

public class SubjectList {

    private String name, subjectcode;
    private int classattended, totalclassattendance;

    public SubjectList(){

    }

    public SubjectList(String subject, String subjectcode, int classattended, int totalclassattendance) {
        this.name = subject;
        this.subjectcode = subjectcode;
        this.classattended = classattended;
        this.totalclassattendance = totalclassattendance;
    }

    public int getClassAttended() {
        return classattended;
    }

    public int getTotalClassAttendance() {
        return totalclassattendance;
    }

    public String getName() {
        return name;
    }

    public String getSubjectCode() {
        return subjectcode;
    }
}
