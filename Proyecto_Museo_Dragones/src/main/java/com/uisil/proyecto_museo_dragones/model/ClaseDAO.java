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

public class ClaseDAO {
    private Connection connection;

    public ClaseDAO(Connection connection) {
        this.connection = connection;
    }

    public List<ClaseInfo> obtenerTodasClases() throws SQLException {
        List<ClaseInfo> clases = new ArrayList<>();
        String sql =
    "SELECT c.Id_Clase, c.Nombre_Clase, c.Descripcion_Clase, m.Ruta_Imagen " +
    "FROM Clase_Dragon c " +
    "LEFT JOIN Multimedia_Clase m ON c.Id_Clase = m.Id_Clase";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                clases.add(new ClaseInfo(
                        rs.getInt("Id_Clase"),
                        rs.getString("Nombre_Clase"),
                        rs.getString("Descripcion_Clase"),
                        rs.getString("Ruta_Imagen")
                ));
            }
        }
        return clases;
    }
}

