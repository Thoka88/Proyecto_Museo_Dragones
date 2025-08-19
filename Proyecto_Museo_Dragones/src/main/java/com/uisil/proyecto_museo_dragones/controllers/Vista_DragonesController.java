/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.uisil.proyecto_museo_dragones.controllers;

import com.uisil.proyecto_museo_dragones.model.DragonDAO;
import com.uisil.proyecto_museo_dragones.model.DragonInfo;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.util.List;

public class Vista_DragonesController {

    @FXML
    private Label lblClaseSeleccionada;

    @FXML
    private GridPane gridDragones;

    private Connection connection;
    private int idClase;

    public void setData(Connection connection, int idClase, String nombreClase) {
        this.connection = connection;
        this.idClase = idClase;
        lblClaseSeleccionada.setText("Dragones de la " + nombreClase);
        cargarDragones();
    }

    private void cargarDragones() {
        try {
            DragonDAO dao = new DragonDAO(connection);
            List<DragonInfo> dragones = dao.obtenerTodosDragones();

            int col = 0, row = 0;
            for (DragonInfo d : dragones) {
                ImageView imageView = new ImageView(new Image(d.getRutaImagen()));
                imageView.setFitWidth(120);
                imageView.setFitHeight(120);

                Label lblNombre = new Label(d.getNombreDragon());
                lblNombre.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

                VBox box = new VBox(10, imageView, lblNombre);
                box.setStyle("-fx-alignment: center; -fx-padding: 10; -fx-background-color: #dddddd; -fx-border-color: black;");

                // TODO: al hacer clic, abrir una ventana con toda la info del dragón
                box.setOnMouseClicked(e -> mostrarDetalleDragon(d));

                gridDragones.add(box, col, row);
                col++;
                if (col == 4) { col = 0; row++; }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mostrarDetalleDragon(DragonInfo dragon) {
        // Aquí puedes abrir un nuevo FXML con toda la info detallada del dragón
        System.out.println("Mostrar detalles de: " + dragon.getNombreDragon());
    }
}

