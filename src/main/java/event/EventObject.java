package event;

/**
 * <p>Описывает объект который содержит в себе информацию о событии и передаётся
 * слушателю в момент наступления события.</p>
 * @see UserRequestSelectListener
 * @see Event
 */
public interface EventObject<T> {
    /**
     * Возвращает тип произошедшего события
     * @return {@link Event event}
     */
    Event getEventType();

    /**
     * Возвращает ресурсы которые соответствуют произошедшему событию
     * @return параметры запроса;
     */
    T getEventSource();
}
