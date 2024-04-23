package com.codeUrjc.daw.Model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Comment {

    @Id
    @GeneratedValue
    private Long id;


    @Column
    private String nick;

    @Column
    private String description;
    @ManyToOne
    @JsonIgnore
    private Event event;

    @ManyToOne
    @JsonIgnore
    private User user;



    public Comment(String description,Event event,User user) {
        this.description = description;
        this.event = event;
        this.user = user;
    }

    public Comment(String description,String nick) {
        this.description = description;
        this.nick = nick;
    }

    public Comment(){}

    // Getters
    public Long getId() {
        return id;
    }
    public String getNick() {
        return nick;
    }

    public String getDescription() {
        return description;
    }

    // Setters
    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
