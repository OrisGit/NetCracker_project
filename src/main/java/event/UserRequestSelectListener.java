package event;

import view.View;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * <p>Описывает слушателя который подписывается на событие и выполняет некоторые действия в качестве
 * реакции на эти события</p>
 */
public interface UserRequestSelectListener extends Remote {
    /**
     * <p>Реакция на события. Метод вызывается объектом на который подписан слушатель в
     * момент наступления события.</p>
     * @param eo {@link EventObject Объект} который содержит в себе информацию о произошедшем событии.
     * @see Event
     */
    void actionPerfomed(EventObject eo, View stub) throws RemoteException;
}
