package dev.alexengrig.hodgepodge.resttime.util;

import java.time.LocalDateTime;
import java.time.ZoneId;

public final class DateTimeUtil {
    private DateTimeUtil() {
    }

    public static long toLong(LocalDateTime dateTime) {
        return toLong(dateTime, ZoneId.systemDefault());
    }

    public static long toLong(LocalDateTime dateTime, ZoneId zoneId) {
        return dateTime.atZone(zoneId).toInstant().toEpochMilli();
    }
}
