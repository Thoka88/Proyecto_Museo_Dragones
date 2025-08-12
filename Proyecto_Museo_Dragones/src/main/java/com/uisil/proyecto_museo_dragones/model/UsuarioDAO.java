/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uisil.proyecto_museo_dragones.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class UsuarioDAO {
    public void registrarUsuario(String nombre, String correo, String contrasena, String rol) {
        String sql = "INSERT INTO USUARIOS (nombre, correo, contrasena, rol) VALUES (?, ?, ?, ?)";
        try (Connection con = ConexionOracle.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setString(2, correo);
            ps.setString(3, HashUtil.sha256(contrasena)); // Hash en código
            ps.setString(4, rol);
            ps.executeUpdate();

            System.out.println("Usuario registrado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean login(String correo, String contrasena) {
        String sql = "SELECT contrasena FROM USUARIOS WHERE correo = ?";
        try (Connection con = ConexionOracle.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, correo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String hashGuardado = rs.getString("contrasena");
                String hashIngresado = HashUtil.sha256(contrasena);
                return hashGuardado.equals(hashIngresado);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
