/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.uisil.proyecto_museo_dragones.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Visitantes_SalasController {

    @FXML
    private ImageView logoImage;

    @FXML
    private Button btnSeccion1, btnSeccion2, btnSeccion3, btnAnterior, btnSiguiente;

    @FXML
    private TextField txtBuscar;

    

    @FXML
    private Label lblNombreSala;

    @FXML
    public void initialize() {
        // Logo
        //logoImage.setImage(new Image(getClass().getResourceAsStream("/images/logo.png")));

        // Tipos de búsqueda
        

        // Eventos de botones
        btnSeccion1.setOnAction(e -> mostrarSeccion(1));
        btnSeccion2.setOnAction(e -> mostrarSeccion(2));
        btnSeccion3.setOnAction(e -> mostrarSeccion(3));

       
    }

    private void mostrarSeccion(int seccion) {
        lblNombreSala.setText("Sección " + seccion);
        System.out.println("Mostrando Sección " + seccion);
    }

    
}
