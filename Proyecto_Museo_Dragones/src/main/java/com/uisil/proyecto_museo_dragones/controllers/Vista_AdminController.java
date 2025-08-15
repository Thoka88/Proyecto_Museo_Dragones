/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.uisil.proyecto_museo_dragones.controllers;

import com.uisil.proyecto_museo_dragones.model.ConexionOracle;
import com.uisil.proyecto_museo_dragones.model.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Vista_AdminController {

    @FXML private TableView<Usuario> tablaDatos;
    @FXML private TableColumn<Usuario, Integer> col1; // ID
@FXML private TableColumn<Usuario, String> col2; // Nombre
@FXML private TableColumn<Usuario, String> col3; // Apellido
@FXML private TableColumn<Usuario, String> col4; // Correo
@FXML private TableColumn<Usuario, String> col5; // Rol
@FXML private TableColumn<Usuario, Integer> col6; // Edad
@FXML private TableColumn<Usuario, String> col7; // Fecha creación
@FXML private TableColumn<Usuario, String> col8; // Géner
    @FXML private Label lblTitulo;

    private ObservableList<Usuario> listaUsuarios = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        lblTitulo.setText("Mantenimiento de Visitantes");

        // Asignar las propiedades de la clase Usuario a las columnas
        col1.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getId()));
        col2.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNombre()));
        col3.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getApellido()));
        col4.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCorreo()));
        col5.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getRol()));
        col6.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getEdad()));
        col7.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getFechaCreacion().toString()));
        col8.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getGenero()));

        cargarVisitantes();
    }

    private void cargarVisitantes() {
    listaUsuarios.clear();
    String sql = "SELECT ID_USUARIO, NOMBRE, APELLIDOS, CORREO, ROL, EDAD, FECHA_CREACION, GENERO " +
             "FROM USUARIOS WHERE ROL = 'VISITANTE'";


    try (Connection conn = ConexionOracle.getConexion();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            listaUsuarios.add(new Usuario(
                    rs.getInt("ID_USUARIO"),
                    rs.getString("NOMBRE"),
                    rs.getString("APELLIDOS"),
                    rs.getString("CORREO"),
                    rs.getString("ROL"),
                    rs.getInt("EDAD"),
                    rs.getDate("FECHA_CREACION").toLocalDate(),
                    rs.getString("GENERO")
                ));
        }
        tablaDatos.setItems(listaUsuarios);

    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}

