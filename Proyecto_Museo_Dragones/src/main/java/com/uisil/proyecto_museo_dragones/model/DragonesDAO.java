/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uisil.proyecto_museo_dragones.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thoka
 */
public class DragonesDAO {

    public List<DragonInfo> obtenerTodosDragones() {
        List<DragonInfo> lista = new ArrayList<>();

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

            while (rs.next()) {
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
                lista.add(dragon);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
    public boolean insertarDragon(DragonInfo dragon, int idClase) {
        String sqlDesc = "INSERT INTO Descripcion_Ruta (Habilidad, Habitad, Se_Puede_Entrenar, Ataque, Velocidad, Armadura, Poder_Fuego, Limite_Disparos, Veneno, Mandibula, Sigilo, Descripcion) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        String sqlRuta = "INSERT INTO Ruta (Nombre_Imagen, Ruta_Imagen, Ruta_Sonido, Id_Descripcion) VALUES (?, ?, ?, ?)";

        String sqlDragon = "INSERT INTO Dragones (Nombre_Dragon, Id_Ruta) VALUES (?, ?)";

        try (Connection con = ConexionOracle.getConexion()) {
            con.setAutoCommit(false);

            // 1️⃣ Insert en Descripcion_Ruta
            try (PreparedStatement psDesc = con.prepareStatement(sqlDesc, Statement.RETURN_GENERATED_KEYS)) {
                psDesc.setString(1, dragon.getHabilidad());
                psDesc.setString(2, dragon.getHabitad());
                psDesc.setString(3, dragon.getSePuedeEntrenar());
                psDesc.setInt(4, dragon.getAtaque());
                psDesc.setInt(5, dragon.getVelocidad());
                psDesc.setInt(6, dragon.getArmadura());
                psDesc.setInt(7, dragon.getPoderFuego());
                psDesc.setInt(8, dragon.getLimiteDisparos());
                psDesc.setString(9, dragon.getVeneno());
                psDesc.setString(10, dragon.getMandibula());
                psDesc.setString(11, dragon.getSigilo());
                psDesc.setString(12, dragon.getDescripcionDragon());
                psDesc.executeUpdate();

                ResultSet rsDesc = psDesc.getGeneratedKeys();
                int idDescripcion = 0;
                if (rsDesc.next()) idDescripcion = rsDesc.getInt(1);

                // 2️⃣ Insert en Ruta
                try (PreparedStatement psRuta = con.prepareStatement(sqlRuta, Statement.RETURN_GENERATED_KEYS)) {
                    psRuta.setString(1, dragon.getNombreDragon().replace(" ", "_") + ".jpeg"); 
                    psRuta.setString(2, dragon.getRutaImagen());
                    psRuta.setString(3, dragon.getRutaSonido());
                    psRuta.setInt(4, idDescripcion);
                    psRuta.executeUpdate();

                    ResultSet rsRuta = psRuta.getGeneratedKeys();
                    int idRuta = 0;
                    if (rsRuta.next()) idRuta = rsRuta.getInt(1);

                    // 3️⃣ Insert en Dragones
                    try (PreparedStatement psDragon = con.prepareStatement(sqlDragon)) {
                        psDragon.setString(1, dragon.getNombreDragon());
                        psDragon.setInt(2, idRuta);
                        psDragon.executeUpdate();
                    }

                    // 4️⃣ Vincular Clase_Dragon (opcional)
                    String sqlClase = "INSERT INTO Clase_Dragon (Nombre_Clase, Descripcion_Clase, Id_Ruta) VALUES (?, ?, ?)";
                    try (PreparedStatement psClase = con.prepareStatement(sqlClase)) {
                        psClase.setString(1, dragon.getClase());
                        psClase.setString(2, dragon.getDescripcionClase());
                        psClase.setInt(3, idRuta);
                        psClase.executeUpdate();
                    }
                }
            }

            con.commit();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 🔹 Actualizar dragón existente
    public boolean actualizarDragon(DragonInfo dragon) {
        String sql = "UPDATE Dragones d " +
                     "JOIN Ruta r ON d.Id_Ruta = r.Id_Ruta " +
                     "JOIN Descripcion_Ruta dr ON r.Id_Descripcion = dr.Id_Descripcion " +
                     "SET d.Nombre_Dragon = ?, r.Ruta_Imagen = ?, r.Ruta_Sonido = ?, " +
                     "dr.Habilidad = ?, dr.Habitad = ?, dr.Se_Puede_Entrenar = ?, dr.Ataque = ?, dr.Velocidad = ?, " +
                     "dr.Armadura = ?, dr.Poder_Fuego = ?, dr.Limite_Disparos = ?, dr.Veneno = ?, dr.Mandibula = ?, dr.Sigilo = ?, dr.Descripcion = ? " +
                     "WHERE d.Id_Dragon = ?";

        try (Connection con = ConexionOracle.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dragon.getNombreDragon());
            ps.setString(2, dragon.getRutaImagen());
            ps.setString(3, dragon.getRutaSonido());
            ps.setString(4, dragon.getHabilidad());
            ps.setString(5, dragon.getHabitad());
            ps.setString(6, dragon.getSePuedeEntrenar());
            ps.setInt(7, dragon.getAtaque());
            ps.setInt(8, dragon.getVelocidad());
            ps.setInt(9, dragon.getArmadura());
            ps.setInt(10, dragon.getPoderFuego());
            ps.setInt(11, dragon.getLimiteDisparos());
            ps.setString(12, dragon.getVeneno());
            ps.setString(13, dragon.getMandibula());
            ps.setString(14, dragon.getSigilo());
            ps.setString(15, dragon.getDescripcionDragon());
            ps.setInt(16, dragon.getIdDragon());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 🔹 Eliminar dragón
    public boolean eliminarDragon(int idDragon) {
        String sql = "DELETE FROM Dragones WHERE Id_Dragon = ?";
        try (Connection con = ConexionOracle.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idDragon);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}


