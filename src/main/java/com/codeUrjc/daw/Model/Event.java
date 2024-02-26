package com.codeUrjc.daw.Model;

import jakarta.persistence.*;


import java.sql.Time;
import java.util.Date;
import java.util.List;

@Entity
public class Event {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    private String title;
    private String description;
    private String place;
    private Date date;
    private int hour;
    private int duration;
    private int n_tickets;
    private int n_registered;
    private EventState state;

    @ManyToMany(mappedBy = "event")
    private List<User> users ;
    private String category;

    public Event() {

    }
    public Event(Long id, String title, String description, String place, Date date, int hour, int duration, int n_tickets, int n_registered, List<User> users, String category) {
        super();
        this.id = id;
        this.title = title;
        this.description = description;
        this.place = place;
        this.date = date;
        this.hour = hour;
        this.duration = duration;
        this.n_tickets = n_tickets;
        this.n_registered = n_registered;
        this.users = users;
        this.category = category;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getN_tickets() {
        return n_tickets;
    }

    public void setN_tickets(int n_tickets) {
        this.n_tickets = n_tickets;
    }

    public int getN_registered() {
        return n_registered;
    }

    public void setN_registered(int n_registered) {
        this.n_registered = n_registered;
    }

    public EventState getState() {
        return state;
    }

    public void setState(EventState state) {
        this.state = state;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }



}
