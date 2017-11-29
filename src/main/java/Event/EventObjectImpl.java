package Event;

import Prameters.Parameters;

public class EventObjectImpl<T> implements EventObject<T> {
    private T object;
    private Event event;

    public EventObjectImpl(T object, Event event) {
        this.object = object;
        this.event = event;
    }

    @Override
    public Event getEventType() {
        return event;
    }

    @Override
    public T getEventSource() {
        return object;
    }
}
