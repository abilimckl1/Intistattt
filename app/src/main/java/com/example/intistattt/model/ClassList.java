package com.example.intistattt.model;

public class ClassList {

    private String timestart, timeend, name, subjectcode, room, class_attended, day;


    public ClassList(){

    }

    public ClassList(String timestart, String timeend, String subject, String subjectcode, String room, String class_attended) {
        this.timestart = timestart;
        this.timeend = timeend;
        this.name = subject;
        this.subjectcode = subjectcode;
        this.room = room;
        this.class_attended = class_attended;
    }



    public String getTimeStart() {
        return timestart;
    }

    public String getTimeEnd() {
        return timeend;
    }

    public String getName() {
        return name;
    }

    public String getDay() { return day; }

    public String getSubjectCode() {
        return subjectcode;
    }

    public String getRoom() {
        return room;
    }

    public String getClass_attended() { return class_attended; }
}
