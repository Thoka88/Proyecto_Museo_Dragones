package com.uisil.proyecto_museo_dragones;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;  // guarda el stage principal por si lo necesitas luego

       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/uisil/proyecto_museo_dragones/views/Login.fxml"));
        Parent root = fxmlLoader.load();
        
       

        Scene scene = new Scene(root, 720, 680);

        primaryStage.setTitle("Login Museo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}