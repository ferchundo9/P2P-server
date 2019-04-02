/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2p;

import java.rmi.Remote;
import java.util.LinkedList;

/**
 *
 * @author fer
 */
public interface ChatServerInterface extends Remote{
    public void Register(String name);
    public void DeRegister(String name);
    public LinkedList List();
    public void NotifyNewUser(UserInterface user);
    public void RegisterNewFriendShip(String name1,String name2);
}
