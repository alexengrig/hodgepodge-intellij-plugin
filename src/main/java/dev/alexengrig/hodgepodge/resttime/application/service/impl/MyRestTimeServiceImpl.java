package dev.alexengrig.hodgepodge.resttime.application.service.impl;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.annotations.OptionTag;
import dev.alexengrig.hodgepodge.resttime.application.service.MyRestTimeService;
import dev.alexengrig.hodgepodge.resttime.util.LocalDateTimeConverter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

@State(name = "MyRestTimeServiceImpl", storages = {@Storage("restTime.xml")})
public class MyRestTimeServiceImpl implements MyRestTimeService, PersistentStateComponent<MyRestTimeServiceImpl.State> {
    private static final TimeUnit TIME_UNIT = SECONDS;

    private final ScheduledExecutorService executorService;

    private State myState;
    private ScheduledFuture<?> showFuture;
    private ScheduledFuture<?> hideFuture;

    public MyRestTimeServiceImpl() {
        executorService = Executors.newSingleThreadScheduledExecutor();
        myState = new State();
        processTasks();
    }

    private void processTasks() {
        if (hasChanges()) {
            cancelTasks();
        }
        runTasks();
    }

    private boolean hasChanges() {
        final int delay = myState.workTime + myState.restTime;
        return showFuture != null && hideFuture != null
                && (showFuture.getDelay(TIME_UNIT) != delay || hideFuture.getDelay(TIME_UNIT) != delay);
    }

    private void cancelTasks() {
        System.out.println("Cancel tasks at: " + LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
        if (showFuture != null) {
            showFuture.cancel(true);
            showFuture = null;
        }
        if (hideFuture != null) {
            hideFuture.cancel(true);
            hideFuture = null;
        }
    }

    private void runTasks() {
        System.out.println("Run tasks at: " + LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
        final int work = myState.workTime;
        final int rest = myState.restTime;
        final int delay = rest + work;
        final Runnable showRunnable = () -> {
            System.out.println("Show at: " + LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
        };
        final Runnable hideRunnable = () -> {
            System.out.println("Hide at: " + LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
        };
        showFuture = executorService.scheduleWithFixedDelay(showRunnable, work, delay, TIME_UNIT);
        hideFuture = executorService.scheduleWithFixedDelay(hideRunnable, work + rest, delay, TIME_UNIT);
    }

    @Override
    public void updateTimes(int workTime, int restTime) {
        myState.workTime = workTime;
        myState.restTime = restTime;
        processTasks();
    }

    @Override
    public int getWorkTime() {
        return myState.workTime;
    }

    @Override
    public int getRestTime() {
        return myState.restTime;
    }

    @Nullable
    @Override
    public State getState() {
        return myState;
    }

    @Override
    public void loadState(@NotNull State state) {
        myState = state;
    }

    static class State {
        public int workTime = 45;
        public int restTime = 5;
        public boolean isRest = false;
        @OptionTag(converter = LocalDateTimeConverter.class)
        public LocalDateTime start = LocalDateTime.now();
        @OptionTag(converter = LocalDateTimeConverter.class)
        public LocalDateTime end = LocalDateTime.now().plusMinutes(workTime);
    }
}
