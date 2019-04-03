/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2p;
import java.rmi.*;
import java.util.ArrayList;
/**
 *
 * @author Jairo
 */
public interface InterfazServidor extends Remote {
     
     public InterfazUsuario login(String cliente,String pass,UserCallBack cb) throws java.rmi.RemoteException;;
     public void registro(String cliente,String pass) throws java.rmi.RemoteException;;
     public void deRegister(String cliente) throws java.rmi.RemoteException;;
     public boolean addFriendRequest(String clientePeticion,String clienteObjetivo)throws java.rmi.RemoteException;
     public ArrayList<String> getList()throws java.rmi.RemoteException;
     public void nuevaAmistad(String usuario1,String usuario2)throws java.rmi.RemoteException;
     public void desAmistad(String usuario1,String usuario2)throws java.rmi.RemoteException;
}
