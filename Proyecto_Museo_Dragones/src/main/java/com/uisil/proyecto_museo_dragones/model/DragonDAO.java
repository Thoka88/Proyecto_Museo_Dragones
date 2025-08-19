/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uisil.proyecto_museo_dragones.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DragonDAO {
    private Connection connection;

    public DragonDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para obtener TODOS los dragones con su info y multimedia
    public List<DragonInfo> obtenerTodosDragones() throws SQLException {
        List<DragonInfo> dragones = new ArrayList<>();

        String sql =
            "SELECT d.Id_Dragon, d.Nombre_Dragon, " +
            "       c.Nombre_Clase, c.Descripcion_Clase, " +
            "       car.Habilidad, car.Habitad, car.Se_Puede_Entrenar, " +
            "       car.Ataque, car.Velocidad, car.Armadura, car.Poder_Fuego, car.Limite_Disparos, " +
            "       car.Veneno, car.Mandibula, car.Sigilo, car.Descripcion, " +
            "       m.Ruta_Imagen, m.Ruta_Sonido " +
            "FROM Dragones d " +
            "JOIN Clase_Dragon c ON d.Id_Clase = c.Id_Clase " +
            "LEFT JOIN Caracteristicas car ON d.Id_Dragon = car.Id_Dragon " +
            "LEFT JOIN Multimedia_Dragon m ON d.Id_Dragon = m.Id_Dragon";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                dragones.add(new DragonInfo(
                        rs.getInt("Id_Dragon"),
                        rs.getString("Nombre_Dragon"),
                        rs.getString("Nombre_Clase"),
                        rs.getString("Descripcion_Clase"),
                        rs.getString("Habilidad"),
                        rs.getString("Habitad"),
                        rs.getString("Se_Puede_Entrenar"),
                        rs.getInt("Ataque"),
                        rs.getInt("Velocidad"),
                        rs.getInt("Armadura"),
                        rs.getInt("Poder_Fuego"),
                        rs.getInt("Limite_Disparos"),
                        rs.getString("Veneno"),
                        rs.getString("Mandibula"),
                        rs.getString("Sigilo"),
                        rs.getString("Descripcion"),
                        rs.getString("Ruta_Imagen"),
                        rs.getString("Ruta_Sonido")
                ));
            }
        }

        return dragones;
    }
}






