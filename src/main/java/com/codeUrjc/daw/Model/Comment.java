package com.codeUrjc.daw.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Comment {

    @Id
    @GeneratedValue
    private String id;


    @Column
    private String nick;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "autor")
    private User autor;

    @ManyToOne
    @JsonIgnore
    private Event event;





    public Comment(String id, String nick, String description, User autor) {
        this.id = id;
        this.nick = nick;
        this.description = description;
        this.autor = autor;
    }

    public Comment(){}

    // Getters
    public String getId() {
        return id;
    }
    public String getNick() {
        return nick;
    }

    public String getDescription() {
        return description;
    }

    public User getAutor() {
        return autor;
    }

    public void setAutor(User autor) {
        this.autor = autor;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    // Setters
    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public void setId(String id) {
        this.id = id;
    }


}
