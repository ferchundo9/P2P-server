/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2p;
import java.rmi.*;
/**
 *
 * @author Jairo
 */
public interface InterfazCliente extends Remote {
    public String getName() throws java.rmi.RemoteException;;
    public void SendMessageToMe(String message)throws java.rmi.RemoteException;;
    public void ReceiveFriendRequest(InterfazCliente friend)throws java.rmi.RemoteException;;
    public void AcceptFriendRequest()throws java.rmi.RemoteException;;
    public void CancelFriendRequest(String user)throws java.rmi.RemoteException;;
}
