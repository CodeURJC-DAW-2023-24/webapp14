package com.codeUrjc.daw.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Comment {

    @Id
    @GeneratedValue
    private String id;


    @Column
    private String nick;

    @Column
    private String description;

    @Column
    private String likes;

    public Comment(){}

    public Comment(String nick, String description, String likes) {
        this.nick = nick;
        this.description = description;
        this.likes = likes;
    }


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

    public String getLikes() {
        return likes;
    }


    // Setters
    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public void setId(String id) {
        this.id = id;
    }


}
