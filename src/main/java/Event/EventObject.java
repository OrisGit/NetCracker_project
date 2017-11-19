package Event;

/**
 * <p>Описывает объект который содержит в себе информацию о событии и передаётся
 * слушателю в момент наступления события.</p>
 * @param <T> - тип ресурсов которые передаются слушателю.
 * @see ActionListener
 * @see Event
 */
public interface EventObject<T> {
    /**
     * Возвращает тип произошедшего события
     * @return {@link Event Event}
     */
    Event getEventType();

    /**
     * Возвращает ресурсы которые соответствуют произошедшему событию
     * @return ресурсы;
     */
    T getEventSource();
}
