package dev.alexengrig.hodgepodge.resttime.application.service;

import com.intellij.openapi.components.ServiceManager;

public interface MyRestTimeService {
    static MyRestTimeService getInstance() {
        return ServiceManager.getService(MyRestTimeService.class);
    }

    int getWorkTime();

    int getRestTime();

    void updateTimes(int workTime, int restTime);

    default int getTimeStepSize() {
        return 5;
    }

    default int getMinWorkTime() {
        return 20;
    }

    default int getMaxWorkTime() {
        return 200;
    }

    default int getMinRestTime() {
        return 5;
    }

    default int getMaxRestTime() {
        return 50;
    }
}
