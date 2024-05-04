package com.codeUrjc.daw.security.jwt;

public class LoginRequest {

	private String NICK;
	private String password;

	public LoginRequest() {
	}

	public LoginRequest(String username, String password) {
		this.NICK = username;
		this.password = password;
	}

	public String getUsername() {
		return NICK;
	}

	public void setUsername(String username) {
		this.NICK = NICK;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginRequest [username=" + NICK + ", password=" + password + "]";
	}
}
