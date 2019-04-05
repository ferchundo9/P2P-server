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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Jairo
 */
public class ImplementacionServidor  extends UnicastRemoteObject implements InterfazServidor{
    private HashMap<String,InterfazUsuario> clientList;
    private Connection conexion=null;
    private Statement sentencia=null;
    private PreparedStatement sentenciaSQL=null;
    private ResultSet respuesta=null,cifrado=null;
    private String cifrada;
    
    public ImplementacionServidor(Connection conexion) throws RemoteException, SQLException
    {
        super();
        clientList = new HashMap<>();
        this.conexion=conexion;
        this.sentencia=conexion.createStatement();
    }
    
    @Override
    public void deRegister(String cliente) throws java.rmi.RemoteException
    {
        try {
            delogin(clientList.get(cliente));
            String enviar="DELETE FROM amigos WHERE usuario1=?";
            sentenciaSQL=conexion.prepareStatement(enviar);
            sentenciaSQL.setString(1,cliente);
            sentenciaSQL.execute();
            enviar="DELETE FROM amigos WHERE usuario2=?";
            sentenciaSQL=conexion.prepareStatement(enviar);
            sentenciaSQL.setString(1,cliente);
            sentenciaSQL.execute();
            enviar="DELETE FROM usuarios WHERE nombre=?";
            sentenciaSQL=conexion.prepareStatement(enviar);
            sentenciaSQL.setString(1,cliente);
            sentenciaSQL.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ImplementacionServidor.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    @Override
    public void registro(String cliente,String pass)  throws java.rmi.RemoteException
    {
        try {
            String enviar="INSERT INTO usuarios VALUES(?,SHA1(?))";
            sentenciaSQL=conexion.prepareStatement(enviar);
            sentenciaSQL.setString(1,cliente);
            sentenciaSQL.setString(2, pass);
            sentenciaSQL.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ImplementacionServidor.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
    
    @Override
    public InterfazUsuario login(String cliente,String pass,UserCallBack cb) throws java.rmi.RemoteException
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
                if(respuesta.getString("nombre").equals(cliente) && respuesta.getString("pass").equals(cifrada))
                {
                    InterfazUsuario usuario=(InterfazUsuario)new User(cliente,this,cb);
                    if (!(clientList.containsKey(usuario.getName()))) 
                    {
                        clientList.put(usuario.getName(),usuario);
                        System.out.println("Nuevo cliente registrado! ");
                        enviar="SELECT * FROM amigos WHERE usuario1=?";
                        sentenciaSQL=conexion.prepareStatement(enviar);
                        sentenciaSQL.setString(1,cliente);
                        respuesta=sentenciaSQL.executeQuery();
                        while(respuesta.next())
                        {
                            Set <String> claves=clientList.keySet();
                            for(String key:claves)
                            {
                                if(respuesta.getString("usuario2").equals(key))
                                {
                                    usuario.getAmigos().put(key,clientList.get(key));
                                    clientList.get(key).getAmigos().put(usuario.getName(), usuario);
                                }
                            }
                        }
                    }
                    return usuario;
                }
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(ImplementacionServidor.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return null;
    }
    
    

  

    @Override
    public boolean addFriendRequest(String clientePeticion,String clienteObjetivo) throws RemoteException {
        if(clientList.containsKey(clienteObjetivo) && clientList.containsKey(clientePeticion) ){
            clientList.get(clienteObjetivo).getCallBack().ReceiveFriendRequest(clientList.get(clientePeticion),clientList.get(clienteObjetivo));
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<String> getList() throws RemoteException {
        Set<String> keys=clientList.keySet();
        ArrayList<String> nombres=new ArrayList<>();
        for(String key:keys){
            nombres.add(key);
        }
        return nombres;
    }
    @Override
    public void nuevaAmistad(String usuario1,String usuario2)throws RemoteException
    {
        String enviar="INSERT INTO amigos values(?,?)";
        try {
            sentenciaSQL=conexion.prepareStatement(enviar);
            sentenciaSQL.setString(1, usuario1);
            sentenciaSQL.setString(2, usuario2);
            System.out.println(sentenciaSQL);
            sentenciaSQL.execute();
            sentenciaSQL=conexion.prepareStatement(enviar);
            sentenciaSQL.setString(1, usuario2);
            sentenciaSQL.setString(2, usuario1);
            System.out.println(sentenciaSQL);
            sentenciaSQL.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ImplementacionServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void desAmistad(String usuario1,String usuario2)throws RemoteException
    {
        String enviar="DELETE FROM amigos WHERE usuario1=? AND usuario2=?";
        try {
            sentenciaSQL=conexion.prepareStatement(enviar);
            sentenciaSQL.setString(1, usuario1);
            sentenciaSQL.setString(2, usuario2);
            sentenciaSQL.execute();
            sentenciaSQL=conexion.prepareStatement(enviar);
            sentenciaSQL.setString(1, usuario2);
            sentenciaSQL.setString(2, usuario1);
            sentenciaSQL.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ImplementacionServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delogin(InterfazUsuario cliente) throws RemoteException {
        Set <String> claves=cliente.getAmigos().keySet();
        for(String key:claves){
            clientList.get(key).getAmigos().remove(cliente.getName());
        }
        clientList.remove(cliente.getName());
        
    }
}
