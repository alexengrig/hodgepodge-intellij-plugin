package dev.alexengrig.hodgepodge.resttime.util;

import java.time.Instant;
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

    public static LocalDateTime toLocalDateTime(long epochMilli) {
        return toLocalDateTime(epochMilli, ZoneId.systemDefault());
    }

    public static LocalDateTime toLocalDateTime(long epochMilli, ZoneId zoneId) {
        return Instant.ofEpochMilli(epochMilli).atZone(zoneId).toLocalDateTime();
    }
}
