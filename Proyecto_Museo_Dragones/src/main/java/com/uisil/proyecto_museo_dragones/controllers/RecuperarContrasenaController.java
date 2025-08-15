/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.uisil.proyecto_museo_dragones.controllers;

import static com.uisil.proyecto_museo_dragones.model.HashUtil.sha256;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.util.Duration;
import javafx.animation.PauseTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RecuperarContrasenaController {

    @FXML
    private TextField correoField;

    @FXML
    private Button recuperarBtn;

    @FXML
    private Label mensajeLabel;

    // Contraseña predeterminada
    private final String CONTRASENA_PREDETERMINADA = "patito1234";

    @FXML
    private void initialize() {
        recuperarBtn.setOnAction(e -> resetearContrasena());
    }

   private void resetearContrasena() {
    String correo = correoField.getText().trim();
    if (correo.isEmpty()) {
        mensajeLabel.setText("Por favor ingresa un correo válido.");
        return;
    }

    boolean exito = actualizarContrasenaEnBD(correo, CONTRASENA_PREDETERMINADA);

    if (exito) {
        mensajeLabel.setText("Contraseña reseteada a: " + CONTRASENA_PREDETERMINADA);

        // Esperar 3 segundos y redirigir al login
        PauseTransition pausa = new PauseTransition(Duration.seconds(3));
        pausa.setOnFinished(e -> {
            try {
                // Cerrar la ventana actual
                Stage stageActual = (Stage) mensajeLabel.getScene().getWindow();
                stageActual.close();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/uisil/proyecto_museo_dragones/views/Login.fxml"));
                Parent root = loader.load();
                Stage stageLogin = new Stage();
                stageLogin.setScene(new Scene(root, 720, 680)); // mismo tamaño que Login
                stageLogin.setResizable(false);
                stageLogin.setTitle("Login Museo");
                stageLogin.show();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        pausa.play();

    } else {
        mensajeLabel.setText("No se encontró el correo en la base de datos.");
    }
}

   private boolean actualizarContrasenaEnBD(String correo, String nuevaContrasena) {
    // URL para Oracle XE
    String url = "jdbc:oracle:thin:@localhost:1521:XE";
    String usuarioDB = "Thoka";   // ejemplo: "system" o el usuario de tu esquema
    String passwordDB = "uisil"; // contraseña del usuario
    String sql = "UPDATE usuarios SET contrasena = ? WHERE correo = ?";

    // Generar el hash de la nueva contraseña
    String hash = sha256(nuevaContrasena);
    if (hash == null) return false;

    try (Connection conn = DriverManager.getConnection(url, usuarioDB, passwordDB);
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, hash); // Guardar el hash, no la contraseña en texto
        pstmt.setString(2, correo);

        int filas = pstmt.executeUpdate();
        return filas > 0; // true si se actualizó al menos un registro

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}}

