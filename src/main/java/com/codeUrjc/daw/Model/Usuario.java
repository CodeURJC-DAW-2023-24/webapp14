package com.codeUrjc.daw.Model;

public class Usuario {
    private String dni;
    private String nombre;
    private String apellidos;
    private String nick;
    private String contra;
    private String telefono;
    private String email;
    private String centro;

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getNick() {
        return nick;
    }


    public String getContra() {
        return contra;
    }

    public String getCentro() {
        return centro;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCentro(String centro) {
        this.centro = centro;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
