/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.uisil.proyecto_museo_dragones.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class RegistroController {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellidos;

    @FXML
    private TextField txtEdad;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtContrasena;

    @FXML
    private RadioButton rbMasculino;

    @FXML
    private RadioButton rbFemenino;

    @FXML
    private void onRegistrar() {
        // Aquí puedes recoger los datos ingresados:
        String nombre = txtNombre.getText();
        String apellidos = txtApellidos.getText();
        String edad = txtEdad.getText();
        String email = txtEmail.getText();
        String contrasena = txtContrasena.getText();
        String genero = rbMasculino.isSelected() ? "Masculino" : rbFemenino.isSelected() ? "Femenino" : "No seleccionado";

        // Ejemplo de uso
        System.out.println("Nombre: " + nombre);
        System.out.println("Apellidos: " + apellidos);
        System.out.println("Edad: " + edad);
        System.out.println("Email: " + email);
        System.out.println("Contraseña: " + contrasena);
        System.out.println("Género: " + genero);

        // Aquí puedes agregar la lógica para registrar al usuario (guardar en DB, validar, etc.)
    }
}