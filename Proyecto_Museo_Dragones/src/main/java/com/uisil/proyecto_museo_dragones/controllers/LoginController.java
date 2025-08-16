package com.uisil.proyecto_museo_dragones.controllers;

import com.uisil.proyecto_museo_dragones.App;
import com.uisil.proyecto_museo_dragones.model.Usuario;
import com.uisil.proyecto_museo_dragones.model.UsuarioDAO;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
    String usuario = txtUsuario.getText();
    String contrasena = txtContrasena.getText();

    UsuarioDAO usuarioDAO = new UsuarioDAO();
    Usuario usuarioLogueado = usuarioDAO.loginYObtenerUsuario(usuario, contrasena);

    if (usuarioLogueado != null) {
        try {
            Stage stageLogin = (Stage) txtUsuario.getScene().getWindow();
            stageLogin.close();

            String vista;
            if (usuarioLogueado.getRol().equalsIgnoreCase("ADMIN")) {
                vista = "/com/uisil/proyecto_museo_dragones/views/Vista_Admin.fxml";
            } else {
                vista = "/com/uisil/proyecto_museo_dragones/views/Visitantes_Salas.fxml";
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource(vista));
            Parent root = loader.load();

            Stage stageMain = new Stage();
            stageMain.setTitle("Museo de Dragones");
            stageMain.setScene(new Scene(root, 1024, 768));
            stageMain.setResizable(false);
            stageMain.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    } else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error de inicio de sesión");
        alert.setHeaderText(null);
        alert.setContentText("Correo o contraseña incorrectos");
        alert.showAndWait();
    }
}


    @FXML
    private void RecuperarContrasena(ActionEvent event) {
    System.out.println("Redirigiendo a la vista de recuperar contraseña...");
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/uisil/proyecto_museo_dragones/views/RecuperarContrasena.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 720, 680); // mismo tamaño que login
        stage.setScene(scene);
        stage.setResizable(false); // opcional
        stage.setTitle("Recuperar Contraseña");
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

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 720, 680); // mismo tamaño que login
        stage.setScene(scene);
        stage.setResizable(false); // opcional
        stage.setTitle("Recuperar Contraseña");
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}}    
