
package com.uisil.proyecto_museo_dragones.controllers;

import com.uisil.proyecto_museo_dragones.model.DragonDAO;
import com.uisil.proyecto_museo_dragones.model.DragonInfo;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.util.List;

public class Vista_Clases_DragonesController {

    @FXML
    private VBox contenedorDragones;

    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
        cargarDragones();
    }

    private void cargarDragones() {
        try {
            DragonDAO dao = new DragonDAO(connection);
            List<DragonInfo> dragones = dao.obtenerTodosDragones(); // 👈 Método en DAO

            for (DragonInfo d : dragones) {
                // Imagen del dragón
                ImageView img = new ImageView(new Image(
                        getClass().getResourceAsStream(d.getRutaImagen())  // 👈 ruta desde resources
                ));
                img.setFitWidth(200);
                img.setFitHeight(150);
                img.setPreserveRatio(true);

                // Nombre
                Label lblNombre = new Label(d.getNombreDragon());
                lblNombre.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

                // Clase
                Label lblClase = new Label("Clase: " + d.getClase());
                lblClase.setStyle("-fx-font-size: 14px; -fx-font-style: italic;");

                // Descripción
                Label lblDesc = new Label(d.getDescripcionDragon());
                lblDesc.setWrapText(true);
                lblDesc.setMaxWidth(400);

                VBox card = new VBox(10, img, lblNombre, lblClase, lblDesc);
                card.setStyle("-fx-background-color: #f5f5f5; -fx-padding: 15; -fx-border-color: #ccc; -fx-border-radius: 8; -fx-background-radius: 8;");
                card.setMaxWidth(450);

                contenedorDragones.getChildren().add(card);
                System.out.println("Cargando dragones...");
                System.out.println("Total dragones: " + dragones.size());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



