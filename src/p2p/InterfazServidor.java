/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2p;

/**
 *
 * @author Jairo
 */
public interface InterfazServidor {
     public void registrarCliente(InterfazCliente callbackClientObject) throws java.rmi.RemoteException;
     public void borrarCliente(InterfazCliente callbackClientObject) throws java.rmi.RemoteException;
     public InterfazCliente login(String usuario,String pass);
}
