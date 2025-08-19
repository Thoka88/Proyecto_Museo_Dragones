/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.uisil.proyecto_museo_dragones.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Visitantes_SalasController {

    @FXML
private ImageView imgClase1;
@FXML
private ImageView imgClase2;
@FXML
private ImageView imgClase3;

@FXML
private Label lblDesc1;
@FXML
private Label lblDesc2;
@FXML
private Label lblDesc3;

@FXML
public void initialize() {
    // Asignar imágenes
    imgClase1.setImage(new Image(getClass().getResourceAsStream("")));
    imgClase2.setImage(new Image(getClass().getResourceAsStream("/imagenes/clase2.png")));
    imgClase3.setImage(new Image(getClass().getResourceAsStream("/imagenes/clase3.png")));

    // Descripciones
    lblDesc1.setText("Clase de Matemáticas: Aprende álgebra, geometría y más.");
    lblDesc2.setText("Clase de Historia: Descubre las civilizaciones del mundo.");
    lblDesc3.setText("Clase de Ciencias: Explora física, química y biología.");

    // Eventos al hacer click
    imgClase1.setOnMouseClicked(e -> abrirVista("/vistas/Clase1.fxml"));
    imgClase2.setOnMouseClicked(e -> abrirVista("/vistas/Clase2.fxml"));
    imgClase3.setOnMouseClicked(e -> abrirVista("/vistas/Clase3.fxml"));
}

private void abrirVista(String rutaFXML) {
    try {
        Parent root = FXMLLoader.load(getClass().getResource(rutaFXML));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
}


    
}
