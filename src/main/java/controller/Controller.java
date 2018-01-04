package controller;

import event.UserRequestSelectListener;
import view.View;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Controller extends Remote, UserRequestSelectListener{
    public void registerClient(View view) throws RemoteException;
}
