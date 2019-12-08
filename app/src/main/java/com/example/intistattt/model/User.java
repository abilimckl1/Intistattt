package com.example.intistattt.model;

public class User {
    private String username;
    private String password;
    private String email;
    private String name;
    private int attendance;

    public User(){

    }

    public User(String username, String password, String email, String name, int attendance){
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.attendance = attendance;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getEmail(){
        return email;
    }

    public int getAttendance() { return attendance; }

    public String getName(){
        return name;
    }
}
