package dev.alexengrig.hodgepodge.resttime.util;

import com.intellij.util.xmlb.Converter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;

public class LocalDateTimeConverter extends Converter<LocalDateTime> {
    @Nullable
    @Override
    public LocalDateTime fromString(@NotNull String value) {
        return DateTimeUtil.toLocalDateTime(Long.parseLong(value));
    }

    @Nullable
    @Override
    public String toString(@NotNull LocalDateTime value) {
        return Long.toString(DateTimeUtil.toLong(value));
    }
}
