/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uisil.proyecto_museo_dragones.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionOracle {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe"; // Cambia por tu host/puerto/SID
    private static final String USER = "Lordi"; // o el usuario que creaste
    private static final String PASS = "uisil";

    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
 }    }

