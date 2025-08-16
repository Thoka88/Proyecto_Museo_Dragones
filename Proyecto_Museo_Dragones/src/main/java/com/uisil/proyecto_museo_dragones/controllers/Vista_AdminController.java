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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Vista_AdminController {

    @FXML private VBox panelUsuarios;
    @FXML private TableView<Usuario> tablaDatos;
    @FXML private TableColumn<Usuario, Integer> col1; 
    @FXML private TableColumn<Usuario, String> col2;
    @FXML private TableColumn<Usuario, String> col3;
    @FXML private TableColumn<Usuario, String> col4;
    @FXML private TableColumn<Usuario, String> col5;
    @FXML private TableColumn<Usuario, Integer> col6;
    @FXML private TableColumn<Usuario, String> col7;
    @FXML private TableColumn<Usuario, String> col8;
    @FXML private Label lblTitulo;

    @FXML private Button btnMostrarVisitantes;
    @FXML private Button btnMostrarAdmins;
    @FXML private Button btnAgregar;
    @FXML private Button btnEditar;
    @FXML private Button btnEliminar;

    private enum RolActivo { VISITANTE, ADMIN }
    private RolActivo rolActivo = RolActivo.VISITANTE;

    private Usuario usuarioSeleccionado;
    private ObservableList<Usuario> listaUsuarios = FXCollections.observableArrayList();
    private ObservableList<Usuario> listaAdmins = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        panelUsuarios.setVisible(false);

        // Configurar columnas
        col1.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getId()));
        col2.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNombre()));
        col3.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getApellido()));
        col4.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCorreo()));
        col5.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getRol()));
        col6.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getEdad()));
        col7.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getFechaCreacion().toString()));
        col8.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getGenero()));

        tablaDatos.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                usuarioSeleccionado = tablaDatos.getSelectionModel().getSelectedItem();
            }
        });

        // Botones del menú lateral
        btnMostrarVisitantes.setOnAction(e -> mostrarVisitantes());
        btnMostrarAdmins.setOnAction(e -> mostrarAdmins());

        // Botones de acción
        btnAgregar.setOnAction(e -> agregar());
        btnEditar.setOnAction(e -> editar());
        btnEliminar.setOnAction(e -> eliminar());
    }

    // ========================= VISITANTES =========================\
    @FXML
    private void mostrarVisitantes() {
        rolActivo = RolActivo.VISITANTE;
        lblTitulo.setText("Mantenimiento de Visitantes");
        panelUsuarios.setVisible(true);
        cargarVisitantes();
    }
    @FXML
    private void cargarVisitantes() {
        listaUsuarios.clear();
        String sql = "SELECT ID_USUARIO, NOMBRE, APELLIDOS, CORREO, ROL, EDAD, FECHA_CREACION, GENERO FROM USUARIOS WHERE ROL = 'VISITANTE'";
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

    private void eliminarUsuario() {
        if (usuarioSeleccionado != null) {
            String sql = "DELETE FROM USUARIOS WHERE ID_USUARIO = ?";
            try (Connection conn = ConexionOracle.getConexion();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, usuarioSeleccionado.getId());
                int filas = stmt.executeUpdate();
                if (filas > 0) {
                    listaUsuarios.remove(usuarioSeleccionado);
                    usuarioSeleccionado = null;
                    System.out.println("Visitante eliminado correctamente.");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            mostrarAlertaError("Selecciona un usuario", "Debes seleccionar un visitante para eliminar.");
        }
    }

    // ========================= ADMIN =========================
    @FXML
    private void mostrarAdmins() {
        rolActivo = RolActivo.ADMIN;
        lblTitulo.setText("Mantenimiento de Administradores");
        panelUsuarios.setVisible(true);
        cargarAdmins();
    }
    @FXML
    private void cargarAdmins() {
        listaAdmins.clear();
        String sql = "SELECT ID_USUARIO, NOMBRE, APELLIDOS, CORREO, ROL, EDAD, FECHA_CREACION, GENERO FROM USUARIOS WHERE ROL = 'ADMIN'";
        try (Connection conn = ConexionOracle.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                listaAdmins.add(new Usuario(
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
            tablaDatos.setItems(listaAdmins);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void eliminarAdmin() {
        if (usuarioSeleccionado != null) {
            String sql = "DELETE FROM USUARIOS WHERE ID_USUARIO = ?";
            try (Connection conn = ConexionOracle.getConexion();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, usuarioSeleccionado.getId());
                int filas = stmt.executeUpdate();
                if (filas > 0) {
                    listaAdmins.remove(usuarioSeleccionado);
                    usuarioSeleccionado = null;
                    System.out.println("Administrador eliminado correctamente.");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            mostrarAlertaError("Selecciona un usuario", "Debes seleccionar un administrador para eliminar.");
        }
    }

    // ========================= ACCIONES COMUNES =========================
    private void agregar() {
        if (rolActivo == RolActivo.VISITANTE)
            abrirFormularioRegistro(null);
        else
            abrirFormularioRegistroAdmin(null);
    }

    private void editar() {
        if (usuarioSeleccionado == null) {
            mostrarAlertaError("Selecciona un usuario", "Debes seleccionar un usuario para editar.");
            return;
        }
        if (rolActivo == RolActivo.VISITANTE)
            abrirFormularioRegistro(usuarioSeleccionado);
        else
            abrirFormularioRegistroAdmin(usuarioSeleccionado);
    }

    private void eliminar() {
        if (rolActivo == RolActivo.VISITANTE)
            eliminarUsuario();
        else
            eliminarAdmin();
    }

    private void abrirFormularioRegistro(Usuario usuario) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/uisil/proyecto_museo_dragones/views/Registro.fxml"));
            Parent root = loader.load();
            RegistroController controller = loader.getController();

            if (usuario != null)
                controller.setUsuarioParaEditar(usuario);

            Stage stage = new Stage();
            stage.setTitle(usuario == null ? "Agregar Visitante" : "Editar Visitante");
            stage.setScene(new Scene(root, 720, 680));
            stage.setResizable(false);
            stage.showAndWait();

            cargarVisitantes();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void abrirFormularioRegistroAdmin(Usuario usuario) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/uisil/proyecto_museo_dragones/views/Registro.fxml"));
            Parent root = loader.load();
            RegistroController controller = loader.getController();

            controller.setRolPredeterminado("ADMIN");

            if (usuario != null)
                controller.setUsuarioParaEditar(usuario);

            Stage stage = new Stage();
            stage.setTitle(usuario == null ? "Agregar Admin" : "Editar Admin");
            stage.setScene(new Scene(root, 720, 680));
            stage.setResizable(false);
            stage.showAndWait();

            cargarAdmins();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void mostrarAlertaError(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    @FXML
    public void salirProgramaConConfirmacion() {
    Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
    alerta.setTitle("Confirmar salida");
    alerta.setHeaderText(null);
    alerta.setContentText("¿Seguro que deseas salir del programa?");

    // Mostrar alerta y esperar respuesta
    if (alerta.showAndWait().get() == ButtonType.OK) {
        System.exit(0);
    }
}

}




