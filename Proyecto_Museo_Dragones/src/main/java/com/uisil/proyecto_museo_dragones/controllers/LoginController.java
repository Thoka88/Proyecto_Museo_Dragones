package com.uisil.proyecto_museo_dragones.controllers;

import com.uisil.proyecto_museo_dragones.App;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtContrasena;

    @FXML
    private void IniciarSesion() {
        String usuario = txtUsuario.getText();
        String contrasena = txtContrasena.getText();

        // Aquí puedes poner tu lógica
        System.out.println("Usuario: " + usuario);
        System.out.println("Contraseña: " + contrasena);
    }
}
