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

@State(name = "MyRestTimeServiceImpl", storages = {@Storage("restTime.xml")})
public class MyRestTimeServiceImpl implements MyRestTimeService, PersistentStateComponent<MyRestTimeServiceImpl.State> {
    private State myState = new State();

    @Override
    public void updateTimes(int workTime, int restTime) {
        myState.workTime = workTime;
        myState.restTime = restTime;
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
