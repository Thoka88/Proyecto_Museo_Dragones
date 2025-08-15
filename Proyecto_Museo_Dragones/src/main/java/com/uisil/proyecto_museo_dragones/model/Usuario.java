/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uisil.proyecto_museo_dragones.model;

import java.time.LocalDate;

public class Usuario {
    private int id;
    private String nombre;
    private String apellido;
    private String correo;
    private String rol;
    private int edad;
    private LocalDate fechaCreacion;
    private String genero;

    public Usuario(int id, String nombre, String apellido, String correo, String rol, int edad, LocalDate fechaCreacion, String genero) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.rol = rol;
        this.edad = edad;
        this.fechaCreacion = fechaCreacion;
        this.genero = genero;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public String getRol() {
        return rol;
    }

    public int getEdad() {
        return edad;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public String getGenero() {
        return genero;
    }
}
