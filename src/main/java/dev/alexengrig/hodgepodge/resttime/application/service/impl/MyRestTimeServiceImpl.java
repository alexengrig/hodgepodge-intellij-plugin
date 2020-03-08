package dev.alexengrig.hodgepodge.resttime.application.service.impl;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import dev.alexengrig.hodgepodge.resttime.application.service.MyRestTimeService;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.Date;

@State(name = "MyRestTimeServiceImpl", storages = @Storage("restTime.xml"))
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
        System.out.println("HERE");
    }

    static class State implements Serializable {
        public int workTime = 45; //TODO: set 45
        public int restTime = 5; //TODO: set 5
        public boolean isRest = false;
        public long start = new Date().getTime();
        public long end = 0;
    }
}
