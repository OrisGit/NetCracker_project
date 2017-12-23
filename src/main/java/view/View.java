package view;

import event.UserRequestSelectListener;
import model.entities.DrugEntity;
import model.entities.DrugstoreEntity;

import java.util.List;

/**
 * Описывает поведение представления
 */
public interface View {
    /**
     * <p>Подписывает объект-слушатель на события происходящие или вызываемые пользователем внутри представления.</p>
     * @param userRequestSelectListener Объект слушатель, реализующий интерфейс {@link UserRequestSelectListener UserRequestSelectListener}, в который
     *                       передаётся сообщение о произошедшем событии.
     * @see event.Event
     * @see event.EventObject
     */
    void setUserRequestSelectListener(UserRequestSelectListener userRequestSelectListener);

    /**
     * <p>Передаёт представлению список препаратов для вывода</p>
     * @param drugs - список препаратов
     * @see DrugEntity
     */
    void displayDrugs(List<DrugEntity> drugs);

    /**
     * <p>Передаёт представлению список аптек для вывода</p>
     * @param drugstores - список аптек
     * @see DrugstoreEntity
     */
    void displayDrugstores(List<DrugstoreEntity> drugstores);

    /**
     * <p>Передаёт представлению сообщение об ошибке для вывода</p>
     * @param message - сообщение об ошибке
     */
    void displayError(String message);

    void run();

}
