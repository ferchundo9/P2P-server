/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2p;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 *
 * @author fer
 */
public class P2PServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, SQLException, IllegalAccessException {
    Connection conexion= null;
    Statement sentenciaSQL= null;
    ResultSet consulta= null,cifrado=null;
    PreparedStatement sentencia=null;
    String usuario1=null,cifrada=null,input=null,valores=null;
    boolean user=false;
    
    String controlador = "com.mysql.jdbc.Driver";
    Class.forName(controlador).newInstance();
    String URL_bd = "jdbc:mysql://127.0.0.1:3306/p2p";
    String usuario = "jairo";
    String contraseña = "jairo";
    conexion = DriverManager.getConnection(URL_bd, usuario, contraseña);
    sentenciaSQL = conexion.createStatement();
    
    }
    
}
