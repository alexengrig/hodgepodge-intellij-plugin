package dev.alexengrig.hodgepodge.resttime.application.service.impl;

import dev.alexengrig.hodgepodge.resttime.application.service.MyRestTimeService;

public class MyRestTimeServiceImpl implements MyRestTimeService {
    private int workTime;
    private int restTime;

    public MyRestTimeServiceImpl() {
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
}
