package csw.t1.csw.enums;

import java.util.Arrays;

public enum EventType {
    FESTA,
    SHOW,
    TEATRO;

    public static EventType findEventType(String type) {
        return Arrays.stream(EventType.values())
                .filter(eventType -> eventType.name()
                        .equalsIgnoreCase(type))
                .findFirst()
                .orElse(null);
    }
}