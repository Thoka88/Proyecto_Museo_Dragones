/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.uisil.proyecto_museo_dragones.controllers;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RecuperarContrasenaController {

    @FXML
    private TextField correoField;
    
    @FXML
    private Button recuperarBtn;
    
    @FXML
    private Label nuevaContrasenaLabel;
    
    @FXML
    private Label contrasenaGenerada;

    @FXML
    private void initialize() {
        // Configurar el evento del botón
        recuperarBtn.setOnAction(e -> recuperarContrasena());
    }

    private void recuperarContrasena() {
        if (!correoField.getText().isEmpty()) {
            // Generar una contraseña aleatoria
            String nuevaContrasena = generarContrasenaAleatoria();
            contrasenaGenerada.setText(nuevaContrasena);
            
            // Mostrar los elementos
            nuevaContrasenaLabel.setVisible(true);
            contrasenaGenerada.setVisible(true);
            
            // Aquí iría la lógica real para enviar la contraseña al correo
            System.out.println("Contraseña generada para " + correoField.getText() + ": " + nuevaContrasena);
        }
    }

    private String generarContrasenaAleatoria() {
        // Genera una contraseña alfanumérica de 7 caracteres
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            int index = (int) (Math.random() * caracteres.length());
            sb.append(caracteres.charAt(index));
        }
        return sb.toString();
    }
}
