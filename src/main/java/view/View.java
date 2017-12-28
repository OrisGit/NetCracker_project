package view;

import event.UserRequestSelectListener;
import model.entities.*;

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
     * <p>Передаёт представлению список цен для вывода</p>
     * @param prices - список цен
     * @see PriceEntity
     */
    void displayPrices(List<PriceEntity> prices);

    /**
     * <p>Передаёт представлению список фармакологических эффектов для вывода</p>
     * @param pharmachologicEffects - список фармакологических эффектов
     * @see PharmachologicEffectEntity
     */
    void displayPharmacologicEffects(List<PharmachologicEffectEntity> pharmachologicEffects);

    /**
     * <p>Передаёт представлению список терапевтических эффектов для вывода</p>
     * @param therapeuticEffects - список терапевтических эффектов
     * @see TherapeuticEffectEntity
     */
    void displayTherapeuticEffects(List<TherapeuticEffectEntity> therapeuticEffects);

    /**
     * <p>Передаёт представлению сообщение об ошибке для вывода</p>
     * @param message - сообщение об ошибке
     */
    void displayError(String message);

    void run();

}
