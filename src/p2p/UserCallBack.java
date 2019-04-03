/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2p;

/**
 *
 * @author fer
 */
public interface UserCallBack {
    public void SendMessageToMe(String message)throws java.rmi.RemoteException;
    public void ReceiveFriendRequest(InterfazUsuario friend)throws java.rmi.RemoteException;
}
