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
    public User(String name,LinkedList<InterfazUsuario> amigos) throws RemoteException{
        super();
        this.name=name;
        this.amigos=amigos;
    }
    public User(String name)throws RemoteException{
        super();
        this.name=name;
        this.amigos=new LinkedList<>();
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
            friend.AcceptFriendRequest();
            
        }else if(respuesta.equals("no")){
            friend.CancelFriendRequest(this.name);
        }
    }

    @Override
    public void AcceptFriendRequest() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void CancelFriendRequest(String user) {
        System.out.println("El usuario:"+user+"ha cancelado tu solictud de amistad");
    }
    
}
