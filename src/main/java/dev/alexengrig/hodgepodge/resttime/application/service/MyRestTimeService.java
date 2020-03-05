package dev.alexengrig.hodgepodge.resttime.application.service;

import com.intellij.openapi.components.ServiceManager;

public interface MyRestTimeService {
    static MyRestTimeService getInstance() {
        return ServiceManager.getService(MyRestTimeService.class);
    }

    void updateTimes(int workTime, int restTime);

    int getWorkTime();

    int getRestTime();
}
