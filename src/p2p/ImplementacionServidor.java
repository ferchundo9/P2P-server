/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2p;
import java.io.IOException;
import java.rmi.*;
import java.rmi.server.*;
import java.util.Vector;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Jairo
 */
public class ImplementacionServidor  extends UnicastRemoteObject implements InterfazServidor{
    private Vector clientList;
    private Connection conexion=null;
    private Statement sentencia=null;
    private PreparedStatement sentenciaSQL=null;
    private ResultSet respuesta=null,cifrado=null;
    private String cifrada;
    public ImplementacionServidor(Connection conexion) throws RemoteException, SQLException
    {
        super();
        clientList = new Vector();
        this.conexion=conexion;
        this.sentencia=conexion.createStatement();
    }
    
    @Override
    public void deRegister(InterfazCliente cliente) throws java.rmi.RemoteException
    {
        try {
            String enviar="DELETE FROM usuarios WHERE nombre=?";
            sentenciaSQL=conexion.prepareStatement(enviar);
            sentenciaSQL.setString(1,cliente.getName());
            sentenciaSQL.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ImplementacionServidor.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    @Override
    public void registro(InterfazCliente cliente,String pass)  throws java.rmi.RemoteException
    {
        try {
            String enviar="INSERT INTO p2p.usuarios VALUES(?,SHA1(?))";
            sentenciaSQL=conexion.prepareStatement(enviar);
            sentenciaSQL.setString(1,cliente.getName());
            sentenciaSQL.setString(2, pass);
            sentenciaSQL.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ImplementacionServidor.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
    
    @Override
    public boolean login(InterfazCliente cliente,String pass) throws java.rmi.RemoteException
    {
        try {
            String query="SELECT * FROM usuarios";
            respuesta=sentencia.executeQuery(query);
            String enviar="SELECT SHA1(?) as p";
            sentenciaSQL=conexion.prepareStatement(enviar);
            sentenciaSQL.setString(1, pass);
            cifrado=sentenciaSQL.executeQuery();
            cifrado.next();
            cifrada=cifrado.getString("p");
            while(respuesta.next())
            {
                if(respuesta.getString("nombre").equals(cliente.getName()) && respuesta.getString("pass").equals(cifrada))
                {
                    return true;
                }
            }
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(ImplementacionServidor.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return false;
    }
    
    @Override
     public synchronized void registrarCliente(InterfazCliente callbackClientObject) throws java.rmi.RemoteException{
      if (!(clientList.contains(callbackClientObject))) 
      {
         clientList.addElement(callbackClientObject);
         System.out.println("Nuevo cliente registrado! ");
    }
  }  

  @Override
  public synchronized void borrarCliente(InterfazCliente callbackClientObject) throws java.rmi.RemoteException{
    if (clientList.removeElement(callbackClientObject)) 
    {
      System.out.println("Cliente Eliminado ");
    } 
    else 
    {   
       System.out.println("Ese cliente no estaba registrado");
    }
  } 
}
