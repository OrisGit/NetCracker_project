package view;

import event.UserRequestSelectListener;
import model.entities.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Описывает поведение представления
 */
public interface View extends Remote{
    /**
     * <p>Подписывает объект-слушатель на события происходящие или вызываемые пользователем внутри представления.</p>
     * @param userRequestSelectListener Объект слушатель, реализующий интерфейс {@link UserRequestSelectListener UserRequestSelectListener}, в который
     *                       передаётся сообщение о произошедшем событии.
     * @see event.Event
     * @see event.EventObject
     */
    void setUserRequestSelectListener(UserRequestSelectListener userRequestSelectListener) throws RemoteException;

    /**
     * <p>Передаёт представлению список препаратов для вывода</p>
     * @param drugs - список препаратов
     * @see DrugEntity
     */
    void displayDrugs(List<DrugEntity> drugs) throws RemoteException;

    /**
     * <p>Передаёт представлению список аптек для вывода</p>
     * @param drugstores - список аптек
     * @see DrugstoreEntity
     */
    void displayDrugstores(List<DrugstoreEntity> drugstores) throws RemoteException;

    /**
     * <p>Передаёт представлению список цен для вывода</p>
     * @param prices - список цен
     * @see PriceEntity
     */
    void displayPrices(List<PriceEntity> prices) throws RemoteException;

    /**
     * <p>Передаёт представлению список фармакологических эффектов для вывода</p>
     * @param pharmachologicEffects - список фармакологических эффектов
     * @see PharmachologicEffectEntity
     */
    void displayPharmacologicEffects(List<PharmachologicEffectEntity> pharmachologicEffects) throws RemoteException;

    /**
     * <p>Передаёт представлению список терапевтических эффектов для вывода</p>
     * @param therapeuticEffects - список терапевтических эффектов
     * @see TherapeuticEffectEntity
     */
    void displayTherapeuticEffects(List<TherapeuticEffectEntity> therapeuticEffects) throws RemoteException;

    /**
     * <p>Передаёт представлению сообщение об ошибке для вывода</p>
     * @param message - сообщение об ошибке
     */
    void displayError(String message) throws RemoteException;

    void export(String data, String path) throws RemoteException;

    public void setStub(Remote stub) throws RemoteException;

    void run() throws RemoteException;

}
