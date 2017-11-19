package Event;

import Prameters.Parameters;

/**
 * <p>Описывает объект который содержит в себе информацию о событии и передаётся
 * слушателю в момент наступления события.</p>
 * @see ActionListener
 * @see Event
 */
public interface EventObject {
    /**
     * Возвращает тип произошедшего события
     * @return {@link Event Event}
     */
    Event getEventType();

    /**
     * Возвращает ресурсы которые соответствуют произошедшему событию
     * @return параметры запроса;
     */
    Parameters getEventSource();
}
