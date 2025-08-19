/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uisil.proyecto_museo_dragones.model;

import java.sql.*;
import java.util.*;

public class Sala_Clase_DAO {

    public List<Sala_Clase> obtenerTodasSalas() {
        List<Sala_Clase> salas = new ArrayList<>();

String sql =
    "SELECT d.Id_Dragon, d.Nombre_Dragon, " +
    "       c.Nombre_Clase, c.Descripcion_Clase, " +
    "       car.Habilidad, car.Habitad, car.Se_Puede_Entrenar, " +
    "       car.Ataque, car.Velocidad, car.Armadura, car.Poder_Fuego, " +
    "       car.Limite_Disparos, car.Veneno, car.Mandibula, car.Sigilo, " +
    "       car.Descripcion, " +
    "       md.Ruta_Imagen AS Ruta_Imagen, " +
    "       md.Ruta_Sonido AS Ruta_Sonido, " +
    "       mc.Ruta_Imagen AS Ruta_Clase " +
    "FROM Dragones d " +
    "JOIN Clase_Dragon c ON d.Id_Clase = c.Id_Clase " +
    "LEFT JOIN Caracteristicas car ON d.Id_Dragon = car.Id_Dragon " +
    "LEFT JOIN Multimedia_Dragon md ON d.Id_Dragon = md.Id_Dragon " +
    "LEFT JOIN Multimedia_Clase mc ON c.Id_Clase = mc.Id_Clase";




        try (Connection con = ConexionOracle.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            Map<Integer, Sala_Clase> mapaSalas = new HashMap<>();

            while (rs.next()) {
                int idClase = rs.getInt("Id_Clase"); // ✅ ahora sí existe en el SELECT

                DragonInfo dragon = new DragonInfo(
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
                );

                // Recupera la sala existente o crea una nueva
                Sala_Clase sala = mapaSalas.getOrDefault(idClase,
                        new Sala_Clase(
                                idClase,
                                rs.getString("Nombre_Clase"),
                                rs.getString("Descripcion_Clase"),
                                new ArrayList<>()
                        )
                );

                sala.getDragones().add(dragon);
                mapaSalas.put(idClase, sala);
            }

            salas.addAll(mapaSalas.values());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return salas;
    }
}


