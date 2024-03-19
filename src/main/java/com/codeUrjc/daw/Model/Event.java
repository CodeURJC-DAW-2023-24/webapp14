package com.codeUrjc.daw.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String description;

    private String place;

    private String date;


    private String duration;

    private Category category;



    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets = new ArrayList<>();

    @ManyToMany(mappedBy="events")
    private List<User> users;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public Event(String title, String description, String place, String date, String duration, List<Ticket> tickets, Category category) {
        this.title = title;
        this.description = description;
        this.place = place;
        this.date = date;
        this.duration = duration;
        this.tickets = tickets;
        this.category = category;
    }


    public void addTicket(Ticket ticket){
        tickets.add(ticket);
        ticket.setEvent(this);
    }

    public void removeTicket(Ticket ticket){
        tickets.remove(ticket);
        ticket.setEvent(null);
    }

    public Event() {

    }

    // Para el ID
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPlace() {
        return place;
    }

    public String getDate() {
        return date;
    }

    public String getDuration() {
        return duration;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", place='" + place + '\'' +
                ", date='" + date + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}

