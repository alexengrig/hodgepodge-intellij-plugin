package dev.alexengrig.hodgepodge.resttime.application.service.impl;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import dev.alexengrig.hodgepodge.resttime.application.service.MyRestTimeService;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;

@State(name = "MyRestTimeServiceImpl")
public class MyRestTimeServiceImpl implements MyRestTimeService, PersistentStateComponent<MyRestTimeServiceImpl.State> {
    private State myState;
    private int workTime;
    private int restTime;

    public MyRestTimeServiceImpl() {
        myState = new State();
        workTime = 45;
        restTime = 15;
    }

    @Override
    public void updateTimes(int workTime, int restTime) {
        this.workTime = workTime;
        this.restTime = restTime;
    }

    @Override
    public int getWorkTime() {
        return workTime;
    }

    @Override
    public int getRestTime() {
        return restTime;
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

    protected static class State {
        private LocalDateTime dateTime = LocalDateTime.now();
    }
}
