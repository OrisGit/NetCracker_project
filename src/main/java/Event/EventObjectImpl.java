package Event;

import Prameters.Parameters;

public class EventObjectImpl implements EventObject {
    private Parameters parameters;
    private Event event;

    public EventObjectImpl(Parameters parameters, Event event) {
        this.parameters = parameters;
        this.event = event;
    }

    @Override
    public Event getEventType() {
        return event;
    }

    @Override
    public Parameters getEventSource() {
        return parameters;
    }
}
