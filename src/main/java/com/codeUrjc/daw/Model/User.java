package com.codeUrjc.daw.Model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;


import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;


@Entity
public class User implements Serializable {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name="native",strategy="native")
    private String id;

    @Column
    private String name;

   @Column
    private String surname;
   @Column
   private String nick;
   @Column
   private String password;

   @Column
   private String phone;

   @Column
   private String email;

   @Column
   private String studyCenter;

    //usamos Set para que no se repitan los roles
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable( name ="user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;


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


    //getters
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

    public Set<Role> getRoles() {
        return roles;
    }


    //setters

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", nick='" + nick + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", studyCenter='" + studyCenter + '\'' +
                ", roles=" + roles +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(getId(), user.getId()) && Objects.equals(getName(), user.getName()) && Objects.equals(getSurname(), user.getSurname()) && Objects.equals(getNick(), user.getNick()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getPhone(), user.getPhone()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getStudyCenter(), user.getStudyCenter()) && Objects.equals(getRoles(), user.getRoles());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSurname(), getNick(), getPassword(), getPhone(), getEmail(), getStudyCenter(), getRoles());
    }
}
