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
import java.rmi.*;
import java.net.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
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
        
    InputStreamReader is = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(is);
    String portNum, registryURL;
    try{     
      System.out.println("Introduzca el puerto:");
      portNum = (br.readLine()).trim();
      int RMIPortNum = Integer.parseInt(portNum);
      startRegistry(RMIPortNum);
      ImplementacionServidor exportedObj = new ImplementacionServidor();
      registryURL = "rmi://localhost:" + portNum + "/aplicacion";
      Naming.rebind(registryURL, exportedObj);
      System.out.println("Servidor preparado.");
    }
    catch (Exception re) {
      System.out.println(
        "Excepcion en servidor: " + re);
    } 

    String controlador = "com.mysql.jdbc.Driver";
    Class.forName(controlador).newInstance();
    String URL_bd = "jdbc:mysql://127.0.0.1:3306/p2p";
    String usuario = "jairo";
    String contraseña = "jairo";
    conexion = DriverManager.getConnection(URL_bd, usuario, contraseña);
    sentenciaSQL = conexion.createStatement();
  }
    
    private static void startRegistry(int RMIPortNum)throws RemoteException
    {
    try {
        Registry registry = LocateRegistry.getRegistry(RMIPortNum);
        registry.list( );  
    }
    catch (RemoteException e) { 
      Registry registry = LocateRegistry.createRegistry(RMIPortNum);
        registry.list( );  
    }
    
 }
}
    

