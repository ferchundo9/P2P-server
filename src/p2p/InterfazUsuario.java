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
public interface InterfazUsuario {
    public String getName();
    public void SendMessageToMe(String message);
    public void ReceiveFriendRequest(InterfazUsuario friend);
    public void AcceptFriendRequest();
    public void CancelFriendRequest(String user);
}
