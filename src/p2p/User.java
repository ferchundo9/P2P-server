/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2p;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fer
 */
public class User extends UnicastRemoteObject implements InterfazUsuario{
    private String name;
    private LinkedList<InterfazUsuario> amigos;
    private InterfazServidor interfaz;
    public User(String name,LinkedList<InterfazUsuario> amigos) throws RemoteException{
        super();
        this.name=name;
        this.amigos=amigos;
    }
    public User(String name,InterfazServidor i)throws RemoteException{
        super();
        this.name=name;
        this.amigos=new LinkedList<>();
        this.interfaz=i;
    }
    @Override
    public String getName(){
        return name;
    }
    @Override
    public void SendMessageToMe(String message) {
        System.out.println(message);
    }

    @Override
    public void ReceiveFriendRequest(InterfazUsuario friend) throws RemoteException{
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("El usuario:"+friend.getName()+ "te ha enviado una solicitud de amistad");
        String respuesta=null;
        try {
           
            do{
                System.out.println("Desea aceptarla");
                respuesta = bufferRead.readLine();
            }while(!(respuesta.equals("si") || respuesta.equals("no")));
            
        } catch (IOException ex) {
            System.out.println("Error escribiendo respuesta amistad");
        }

        if(respuesta.equals("si")){
            friend.AcceptFriendRequest(friend);
            
        }else if(respuesta.equals("no")){
            friend.CancelFriendRequest(friend);
        }
    }

    @Override
    public void AcceptFriendRequest(InterfazUsuario friend) throws RemoteException {
        interfaz.nuevaAmistad(name,friend.getName());
    }

    @Override
    public void CancelFriendRequest(InterfazUsuario friend) throws RemoteException {
        interfaz.desAmistad(name,friend.getName());
    }
    
}
