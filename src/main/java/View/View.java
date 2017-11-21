package View;

import Event.ActionListener;
import Model.Entities.DrugEntity;
import Model.Entities.DrugstoreEntity;

import java.util.List;

/**
 * Описывает поведение представления
 */
public interface View {
    /**
     * <p>Подписывает объект-слушатель на события происходящие или вызываемые пользователем внутри представления.</p>
     * @param actionListener Объект слушатель, реализующий интерфейс {@link ActionListener ActionListener}, в который
     *                       передаётся сообщение о произошедшем событии.
     * @see Event.Event
     * @see Event.EventObject
     */
    void setActionListener(ActionListener actionListener);

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
    void displayPharmacies(List<DrugstoreEntity> drugstores);

    /**
     * <p>Передаёт представлению сообщение об ошибке для вывода</p>
     * @param message - сообщение об ошибке
     */
    void displayError(String message);

    void run();

}
