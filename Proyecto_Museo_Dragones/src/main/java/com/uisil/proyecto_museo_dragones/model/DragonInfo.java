/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uisil.proyecto_museo_dragones.model;

/**
 *
 * @author Thoka
 */
public class DragonInfo {
    private int idDragon;
    private String nombreDragon;
    private String clase;
    private String descripcionClase;
    private String habilidad;
    private String habitad;
    private String sePuedeEntrenar;
    private int ataque, velocidad, armadura, poderFuego, limiteDisparos;
    private String veneno, mandibula, sigilo;
    private String descripcionDragon;
    private String rutaImagen;
    private String rutaSonido;

    public DragonInfo(int idDragon, String nombreDragon, String clase, String descripcionClase, String habilidad, String habitad, String sePuedeEntrenar, int ataque, int velocidad, int armadura, int poderFuego, int limiteDisparos, String veneno, String mandibula, String sigilo, String descripcionDragon, String rutaImagen, String rutaSonido) {
        this.idDragon = idDragon;
        this.nombreDragon = nombreDragon;
        this.clase = clase;
        this.descripcionClase = descripcionClase;
        this.habilidad = habilidad;
        this.habitad = habitad;
        this.sePuedeEntrenar = sePuedeEntrenar;
        this.ataque = ataque;
        this.velocidad = velocidad;
        this.armadura = armadura;
        this.poderFuego = poderFuego;
        this.limiteDisparos = limiteDisparos;
        this.veneno = veneno;
        this.mandibula = mandibula;
        this.sigilo = sigilo;
        this.descripcionDragon = descripcionDragon;
        this.rutaImagen = rutaImagen;
        this.rutaSonido = rutaSonido;
    }

    public int getIdDragon() {
        return idDragon;
    }

    public String getNombreDragon() {
        return nombreDragon;
    }

    public String getClase() {
        return clase;
    }

    public String getDescripcionClase() {
        return descripcionClase;
    }

    public String getHabilidad() {
        return habilidad;
    }

    public String getHabitad() {
        return habitad;
    }

    public String getSePuedeEntrenar() {
        return sePuedeEntrenar;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public int getArmadura() {
        return armadura;
    }

    public int getPoderFuego() {
        return poderFuego;
    }

    public int getLimiteDisparos() {
        return limiteDisparos;
    }

    public String getVeneno() {
        return veneno;
    }

    public String getMandibula() {
        return mandibula;
    }

    public String getSigilo() {
        return sigilo;
    }

    public String getDescripcionDragon() {
        return descripcionDragon;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public String getRutaSonido() {
        return rutaSonido;
    }

    public void setIdDragon(int idDragon) {
        this.idDragon = idDragon;
    }

    public void setNombreDragon(String nombreDragon) {
        this.nombreDragon = nombreDragon;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public void setDescripcionClase(String descripcionClase) {
        this.descripcionClase = descripcionClase;
    }

    public void setHabilidad(String habilidad) {
        this.habilidad = habilidad;
    }

    public void setHabitad(String habitad) {
        this.habitad = habitad;
    }

    public void setSePuedeEntrenar(String sePuedeEntrenar) {
        this.sePuedeEntrenar = sePuedeEntrenar;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public void setArmadura(int armadura) {
        this.armadura = armadura;
    }

    public void setPoderFuego(int poderFuego) {
        this.poderFuego = poderFuego;
    }

    public void setLimiteDisparos(int limiteDisparos) {
        this.limiteDisparos = limiteDisparos;
    }

    public void setVeneno(String veneno) {
        this.veneno = veneno;
    }

    public void setMandibula(String mandibula) {
        this.mandibula = mandibula;
    }

    public void setSigilo(String sigilo) {
        this.sigilo = sigilo;
    }

    public void setDescripcionDragon(String descripcionDragon) {
        this.descripcionDragon = descripcionDragon;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public void setRutaSonido(String rutaSonido) {
        this.rutaSonido = rutaSonido;
    }

 
}

