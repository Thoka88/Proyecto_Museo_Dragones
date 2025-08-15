/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.uisil.proyecto_museo_dragones.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

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

    @FXML
    private void onRegistrar() {
        String nombre = txtNombre.getText().trim();
        String apellidos = txtApellidos.getText().trim();
        String edadStr = txtEdad.getText().trim();
        String correo = txtCorreo.getText().trim();
        String contrasena = txtContrasena.getText();
        String genero = rbMasculino.isSelected() ? "Masculino" :
                        rbFemenino.isSelected() ? "Femenino" : "No seleccionado";
        String tipoUsuario = "Visitante";

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

            // Verificar si el correo ya existe
            String sqlCorreo = "SELECT COUNT(*) FROM usuarios WHERE correo = ?";
            PreparedStatement stmtCorreo = con.prepareStatement(sqlCorreo);
            stmtCorreo.setString(1, correo);
            ResultSet rsCorreo = stmtCorreo.executeQuery();
            if (rsCorreo.next() && rsCorreo.getInt(1) > 0) {
                mostrarAlerta("Error", "El correo ya está registrado", Alert.AlertType.ERROR);
                return;
            }

            // Verificar si el usuario (nombre + apellidos) ya existe
            String sqlUsuario = "SELECT COUNT(*) FROM usuarios WHERE nombre = ? AND apellidos = ?";
            PreparedStatement stmtUsuario = con.prepareStatement(sqlUsuario);
            stmtUsuario.setString(1, nombre);
            stmtUsuario.setString(2, apellidos);
            ResultSet rsUsuario = stmtUsuario.executeQuery();
            if (rsUsuario.next() && rsUsuario.getInt(1) > 0) {
                mostrarAlerta("Error", "Ya existe un usuario con ese nombre y apellidos", Alert.AlertType.ERROR);
                return;
            }

            // Encriptar contraseña
            String hashContrasena = encriptarSHA256(contrasena);

            // Insertar en la base de datos
            String sqlInsert = "INSERT INTO usuarios (nombre, apellidos, edad, correo, contrasena, genero, rol) VALUES (?, ?, ?, ?, ?, ?, 'VISITANTE')";
            PreparedStatement stmtInsert = con.prepareStatement(sqlInsert);
            stmtInsert.setString(1, nombre);
            stmtInsert.setString(2, apellidos);
            stmtInsert.setInt(3, edad);
            stmtInsert.setString(4, correo);
            stmtInsert.setString(5, hashContrasena);
            stmtInsert.setString(6, genero);
           

            int filas = stmtInsert.executeUpdate();
            if (filas > 0) {
                mostrarAlerta("Éxito", "Usuario visitante registrado correctamente", Alert.AlertType.INFORMATION);
                limpiarCampos();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo registrar el usuario: " + e.getMessage(), Alert.AlertType.ERROR);
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
}

