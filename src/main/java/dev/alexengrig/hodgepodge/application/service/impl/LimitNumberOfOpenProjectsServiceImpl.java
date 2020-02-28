package dev.alexengrig.hodgepodge.application.service.impl;

import dev.alexengrig.hodgepodge.application.service.LimitNumberOfOpenProjectsService;

public class LimitNumberOfOpenProjectsServiceImpl implements LimitNumberOfOpenProjectsService {
    private int count = 0;
    private final int limit = 2;

    @Override
    public int limit() {
        return limit;
    }

    @Override
    public boolean openedNewProject() {
        if (count + 1 <= limit) {
            ++count;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void closedProject() {
        if (--count < 0) {
            count = 0;
        }
    }
}
