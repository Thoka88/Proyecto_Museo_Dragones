/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uisil.proyecto_museo_dragones.model;

import java.util.ArrayList;
import java.util.List;

public class Sala_Clase {
    private int idSala;
    private String nombreClase;
    private String descripcionClase;
    private List<DragonInfo> dragones; // 3 dragones por sala

    public Sala_Clase(int idSala, String nombreClase, String descripcionClase, ArrayList<DragonInfo> dragones) {
        this.idSala = idSala;
        this.nombreClase = nombreClase;
        this.descripcionClase = descripcionClase;
        this.dragones = dragones;
    }

    public int getIdSala() { return idSala; }
    public String getNombreClase() { return nombreClase; }
    public String getDescripcionClase() { return descripcionClase; }
    public List<DragonInfo> getDragones() { return dragones; }
}
