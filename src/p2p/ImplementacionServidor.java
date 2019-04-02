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
/**
 *
 * @author Jairo
 */
public class ImplementacionServidor  extends UnicastRemoteObject implements InterfazServidor{
    private Vector clientList;
    public ImplementacionServidor() throws RemoteException
    {
        super();
        clientList = new Vector();
    }
    public InterfazCliente login(String usuario,String pass)
    {
        InterfazCliente a = null;
        return a;
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
