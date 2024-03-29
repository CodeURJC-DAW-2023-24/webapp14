package com.codeUrjc.daw.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.sql.Blob;
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

    private int people_inscribed;
    private int max_people;



    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets = new ArrayList<>();

    @ManyToMany(mappedBy="events")
    private List<User> users;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @Lob
    @JsonIgnore
    private Blob imageFile;
    private boolean image;

    public Event(String title, String description, String place, String date, String duration, List<Ticket> tickets, Category category, int max_people, int people_inscribed) {
        this.title = title;
        this.description = description;
        this.place = place;
        this.date = date;
        this.duration = duration;
        this.tickets = tickets;
        this.category = category;
        this.max_people = max_people;
        this.people_inscribed = people_inscribed;
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

    public int getMax_people() {
        return max_people;
    }

    public void setMax_people(int max_people) {
        this.max_people = max_people;
    }

    public int getPeople_inscribed() {
        return people_inscribed;
    }

    public void setPeople_inscribed(int people_inscribed) {
        this.people_inscribed = people_inscribed;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Blob getImageFile() {
        return imageFile;
    }

    public void setImageFile(Blob imageFile) {
        this.imageFile = imageFile;
    }


    public boolean getImage(){
        return this.image;
    }
    public void setImage(boolean image) {
        this.image = image;
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

