package com.codeUrjc.daw.Model;

import jakarta.persistence.*;

import java.util.List;


@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String name;
    private String surname;
    private String phone;
    private String description;
    private String email;
    private List<Integer> price;

    public Ticket() {}

    public Ticket(String id, String name, String surname, String phone, String description, String email, List<Integer> price) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.description = description;
        this.email = email;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Integer> getPrice() {
        return price;
    }

    public void setPrice(List<Integer> price) {
        this.price = price;
    }


}
