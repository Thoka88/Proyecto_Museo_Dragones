/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.uisil.proyecto_museo_dragones.controllers;

import com.uisil.proyecto_museo_dragones.model.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import javafx.animation.PauseTransition;
import javafx.stage.Stage;
import javafx.util.Duration;

public class RegistroController {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellidos;

    @FXML
    private TextField txtEdad;

    @FXML
    private TextField txtCorreo;

    @FXML
    private PasswordField txtContrasena;

    @FXML
    private RadioButton rbMasculino;

    @FXML
    private RadioButton rbFemenino;
    
        private Usuario usuarioEditar = null;

    @FXML
private void onRegistrar() {
    String nombre = txtNombre.getText().trim();
    String apellidos = txtApellidos.getText().trim();
    String edadStr = txtEdad.getText().trim();
    String correo = txtCorreo.getText().trim();
    String contrasena = txtContrasena.getText().trim();
    String genero = rbMasculino.isSelected() ? "Masculino" :
                    rbFemenino.isSelected() ? "Femenino" : "No seleccionado";

    if (nombre.isEmpty() || apellidos.isEmpty() || edadStr.isEmpty() || correo.isEmpty() || contrasena.isEmpty()) {
        mostrarAlerta("Error", "Todos los campos son obligatorios", Alert.AlertType.ERROR);
        return;
    }

    int edad;
    try {
        edad = Integer.parseInt(edadStr);
    } catch (NumberFormatException e) {
        mostrarAlerta("Error", "La edad debe ser un número", Alert.AlertType.ERROR);
        return;
    }

    try (Connection con = getConexion()) {

        if (usuarioEditar == null) {
            // --- AGREGAR USUARIO ---
            String sqlInsert = "INSERT INTO usuarios (nombre, apellidos, edad, correo, contrasena, genero, rol) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmtInsert = con.prepareStatement(sqlInsert);
            stmtInsert.setString(1, nombre);
            stmtInsert.setString(2, apellidos);
            stmtInsert.setInt(3, edad);
            stmtInsert.setString(4, correo);
            stmtInsert.setString(5, encriptarSHA256(contrasena));
            stmtInsert.setString(6, genero);
            stmtInsert.setString(7, rolPredeterminado);
            stmtInsert.executeUpdate();
            mostrarAlerta("Éxito", "Usuario agregado correctamente", Alert.AlertType.INFORMATION);

        } else {
            // --- EDITAR USUARIO ---
            String sqlUpdate = "UPDATE usuarios SET nombre = ?, apellidos = ?, edad = ?, correo = ?, contrasena = ?,  genero = ?, rol = ? " +
                               "WHERE id_usuario = ?";
            PreparedStatement stmtUpdate = con.prepareStatement(sqlUpdate);
            stmtUpdate.setString(1, nombre);
            stmtUpdate.setString(2, apellidos);
            stmtUpdate.setInt(3, edad);
            stmtUpdate.setString(4, correo);
            stmtUpdate.setString(5, encriptarSHA256(contrasena));
            stmtUpdate.setString(6, genero);
            stmtUpdate.setString(7, rolPredeterminado);
            stmtUpdate.setInt(8, usuarioEditar.getId()); // <-- se usa el ID del usuario a editar
            stmtUpdate.executeUpdate();
            mostrarAlerta("Éxito", "Usuario editado correctamente", Alert.AlertType.INFORMATION);
        }

        limpiarCampos();

        // Cerrar ventana después de 3 segundos
        PauseTransition pausa = new PauseTransition(Duration.seconds(3));
        pausa.setOnFinished(e -> {
            Stage stage = (Stage) txtNombre.getScene().getWindow();
            stage.close();
        });
        pausa.play();

    } catch (SQLException e) {
        e.printStackTrace();
        mostrarAlerta("Error", "No se pudo guardar el usuario: " + e.getMessage(), Alert.AlertType.ERROR);
    }
}
public void setUsuarioParaEditar(Usuario u) {
    usuarioEditar = u;
    txtNombre.setText(u.getNombre());
    txtApellidos.setText(u.getApellido());
    txtEdad.setText(String.valueOf(u.getEdad()));
    txtCorreo.setText(u.getCorreo());
    if (u.getGenero().equals("Masculino")) {
        rbMasculino.setSelected(true);
    } else if (u.getGenero().equals("Femenino")) {
        rbFemenino.setSelected(true);
    }
}



    private Connection getConexion() throws SQLException {
        // Conexión a Oracle
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String usuario = "THOKA"; // tu schema
        String password = "uisil";
        return DriverManager.getConnection(url, usuario, password);
    }

    private String encriptarSHA256(String texto) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(texto.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al encriptar la contraseña", e);
        }
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtApellidos.clear();
        txtEdad.clear();
        txtCorreo.clear();
        txtContrasena.clear();
        rbMasculino.setSelected(false);
        rbFemenino.setSelected(false);
    }
    private String rolPredeterminado = "VISITANTE";

public void setRolPredeterminado(String rol) {
    this.rolPredeterminado = rol;
}

}

