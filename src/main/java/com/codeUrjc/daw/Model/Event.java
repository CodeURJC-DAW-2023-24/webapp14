package com.codeUrjc.daw.Model;

import java.util.Date;

public class Event {

    private long idEvent;
    private String title;
    private String description;
    private String place;
    private Date date;
    private int duration;
    private int n_tickets;
    private int n_registered;
    private EventState state;

    public Event(long idEvent, String title, String description, String place, Date date, int duration, int n_tickets, int n_registered) {
        this.idEvent = idEvent;
        this.title = title;
        this.description = description;
        this.place = place;
        this.date = date;
        this.duration = duration;
        this.n_tickets = n_tickets;
        this.n_registered = n_registered;
    }

    public long getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(long idEvent) {
        this.idEvent = idEvent;
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
}
