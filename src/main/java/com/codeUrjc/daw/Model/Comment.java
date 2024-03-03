package com.codeUrjc.daw.Model;



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




    public Comment(String nick, String description) {
        this.nick = nick;
        this.description = description;
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


}
