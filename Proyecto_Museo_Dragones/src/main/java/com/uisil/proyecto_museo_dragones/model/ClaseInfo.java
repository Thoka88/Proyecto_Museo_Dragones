/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uisil.proyecto_museo_dragones.model;

public class ClaseInfo {
    private int idClase;
    private String nombreClase;
    private String descripcionClase;
    private String rutaImagen;

    public ClaseInfo(int idClase, String nombreClase, String descripcionClase, String rutaImagen) {
        this.idClase = idClase;
        this.nombreClase = nombreClase;
        this.descripcionClase = descripcionClase;
        this.rutaImagen = rutaImagen;
    }

    public int getIdClase() { return idClase; }
    public String getNombreClase() { return nombreClase; }
    public String getDescripcionClase() { return descripcionClase; }
    public String getRutaImagen() { return rutaImagen; }
}

