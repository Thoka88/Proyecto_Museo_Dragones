/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uisil.proyecto_museo_dragones.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.CallableStatement;

public class UsuarioDAO {

    public Usuario loginYObtenerUsuario(String usuarioOCorreo, String contrasena) {
        String sql = "SELECT ID_USUARIO, NOMBRE, APELLIDOS, CORREO, ROL, EDAD, FECHA_CREACION, GENERO, CONTRASENA " +
                     "FROM USUARIOS WHERE CORREO = ? OR NOMBRE = ?";

        try (Connection con = ConexionOracle.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuarioOCorreo);
            ps.setString(2, usuarioOCorreo);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String hashGuardado = rs.getString("CONTRASENA");
                String hashIngresado = HashUtil.sha256(contrasena);

                if (hashGuardado.equals(hashIngresado)) {
                    return new Usuario(
                        rs.getInt("ID_USUARIO"),
                        rs.getString("NOMBRE"),
                        rs.getString("APELLIDOS"),
                        rs.getString("CORREO"),
                        rs.getString("ROL"),
                        rs.getInt("EDAD"),
                        rs.getDate("FECHA_CREACION").toLocalDate(),
                        rs.getString("GENERO")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Si usuario no existe o contraseña incorrecta
    }
}

    

