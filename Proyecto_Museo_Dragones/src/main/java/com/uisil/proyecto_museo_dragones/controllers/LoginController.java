package com.uisil.proyecto_museo_dragones.controllers;

import com.uisil.proyecto_museo_dragones.App;
import com.uisil.proyecto_museo_dragones.model.UsuarioDAO;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtContrasena;
    
    @FXML 
    private Hyperlink linkContrasena;
    
    @FXML 
    private Hyperlink linkRegistro;

    @FXML
private void IniciarSesion() {
    String usuario = txtUsuario.getText();     // Este sería el correo
    String contrasena = txtContrasena.getText();

    UsuarioDAO usuarioDAO = new UsuarioDAO();
    boolean loginExitoso = usuarioDAO.login(usuario, contrasena);

    if (loginExitoso) {
        System.out.println("Login correcto. Abriendo ventana principal...");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/uisil/proyecto_museo_dragones/views/Main_Sala.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) txtUsuario.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } else {
        System.out.println("Usuario o contraseña incorrectos.");
        // Aquí podrías mostrar un Alert de JavaFX
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle("Error de inicio de sesión");
        alert.setHeaderText(null);
        alert.setContentText("Correo o contraseña incorrectos");
        alert.showAndWait();
    }
}
    @FXML
    private void RecuperarContrasena(ActionEvent event) {
    System.out.println("Redirigiendo a la vista de login...");
    // Aquí puedes cargar otra escena, por ejemplo:
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/uisil/proyecto_museo_dragones/views/RecuperarContrasena.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
     @FXML
    private void RegistrarCuenta(ActionEvent event) {
    System.out.println("Redirigiendo a la vista de login...");
    // Aquí puedes cargar otra escena, por ejemplo:
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/uisil/proyecto_museo_dragones/views/Registro.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}    
