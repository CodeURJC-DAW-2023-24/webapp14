package com.codeUrjc.daw.Model;

import java.util.List;

public class User {
    private String id;
    private String name;
    private String surname;
    private String nick;
    private String password;
    private String phone;
    private String email;
    private String studyCenter;
    private List<String> roles;

    public User(){}

    public User(String id, String name, String surname, String nick, String password, String phone, String email, String studyCenter) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.nick = nick;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.studyCenter = studyCenter;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getNick() {
        return nick;
    }


    public String getPassword() {
        return password;
    }

    public String getStudyCenter() {
        return studyCenter;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudyCenter(String studyCenter) {
        this.studyCenter = studyCenter;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
