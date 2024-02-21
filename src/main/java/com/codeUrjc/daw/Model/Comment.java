package com.codeUrjc.daw.Model;

public class Comment {
    private String nick;
    private String description;
    private String likes;

    public Comment(){}

    public Comment(String nick, String description, String likes) {
        this.nick = nick;
        this.description = description;
        this.likes = likes;
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

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }
}
