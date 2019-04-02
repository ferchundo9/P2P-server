/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2p;

import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;

/**
 *
 * @author fer
 */
public class ChatServer extends UnicastRemoteObject implements ChatServerInterface  {
    private LinkedList<UserInterface> usuarios;
    private DAWDataBase db;
    public ChatServer(){
        this.usuarios=new LinkedList<>();
        this.db=new DAWDataBase();
    }
    @Override
    public void Register(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void DeRegister(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LinkedList List() {
        return new LinkedList<>(usuarios);
    }

    @Override
    public void NotifyNewUser(UserInterface user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void RegisterNewFriendShip(String name1, String name2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
