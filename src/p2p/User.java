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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fer
 */
public class User extends UnicastRemoteObject implements InterfazUsuario{
    private String name;
    private HashMap<String,InterfazUsuario> amigos;
    private InterfazServidor interfaz;
    private UserCallBack cb;
    public User(String name,HashMap<String,InterfazUsuario> amigos,UserCallBack cb) throws RemoteException{
        super();
        this.name=name;
        this.amigos=amigos;
        this.cb=cb;
    }
    public User(String name,InterfazServidor i,UserCallBack cb)throws RemoteException{
        super();
        this.name=name;
        this.amigos=new HashMap<>();
        this.interfaz=i;
        this.cb=cb;
    }
    @Override
    public String getName(){
        return name;
    }
    

    @Override
    public void AcceptFriendRequest(InterfazUsuario friend) throws RemoteException {
        interfaz.nuevaAmistad(name,friend.getName());
    }

    @Override
    public void CancelFriendRequest(InterfazUsuario friend) throws RemoteException {
        interfaz.desAmistad(name,friend.getName());
    }

    @Override
    public UserCallBack getCallBack() throws RemoteException {
        return cb;
    }

    @Override
    public HashMap<String,InterfazUsuario> getAmigos() throws RemoteException {
        return amigos;
    }
    
    
}
