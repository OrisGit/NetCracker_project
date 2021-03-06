package event;

/**
 * <p>Описывает слушателя который подписывается на события обновления данных и выполняет некоторые действия в качестве
 * реакции на эти события</p>
 */
public interface UserRequestUpdateListener {
    /**
     * <p>Реакция на события обновления данных. Метод вызывается объектом на который подписан слушатель в
     * момент наступления события.</p>
     * @param eo {@link EventObject Объект} который содержит в себе информацию о произошедшем событии.
     * @see Event
     */
    void actionPerfomed(EventObject eo);
}
