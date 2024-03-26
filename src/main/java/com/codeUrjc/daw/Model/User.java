
package com.codeUrjc.daw.Model;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "USER")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique = true)
	private String NICK;

	private String name;

	private String surname;

	private String email;

	private String encodedPassword;

	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;

	private String studyCenter;

	private long phone;

	private boolean editor;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Ticket> tickets;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Comment> comments;


	@ManyToMany
	private List<Event> events;

	public User(){
	}
	/*public User(String name, String encodedPassword, String... roles) {
       this.name = name;
       this.encodedPassword = encodedPassword;
       this.roles = List.of(roles);
    }*/
	public User(String NICK, String name, String surname, String email, String encodedPassword, String studyCenter, long phone, boolean editor ,List<Ticket> tickets, String... roles) {
		this.NICK = NICK;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.encodedPassword = encodedPassword;
		this.studyCenter = studyCenter;
		this.phone = phone;
		this.editor = editor;
		this.tickets = tickets;
		this.roles = List.of(roles);
	}

	public void addTicket(Ticket ticket){
		tickets.add(ticket);
		ticket.setUser(this);
	}

	public void removeTicket(Ticket ticket){
		tickets.remove(ticket);
		ticket.setUser(null);
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEncodedPassword() {
		return encodedPassword;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNICK() {
		return NICK;
	}

	public void setNICK(String NICK) {
		this.NICK = NICK;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getStudyCenter() {
		return studyCenter;
	}
	public void setStudyCenter(String studyCenter) {
		this.studyCenter = studyCenter;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}

	public boolean isEditor() {
		return editor;
	}

	public void setEditor(boolean editor) {
		this.editor = editor;
	}

	public void setEncodedPassword(String encodedPassword) {
		this.encodedPassword = encodedPassword;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public void addEvent(Event event) {
		if (events == null) {
			events = new ArrayList<>();
		}
		events.add(event);
	}

}
