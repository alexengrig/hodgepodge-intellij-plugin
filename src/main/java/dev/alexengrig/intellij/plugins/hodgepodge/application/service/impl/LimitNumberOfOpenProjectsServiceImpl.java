package dev.alexengrig.intellij.plugins.hodgepodge.application.service.impl;

import dev.alexengrig.intellij.plugins.hodgepodge.application.service.LimitNumberOfOpenProjectsService;

public class LimitNumberOfOpenProjectsServiceImpl implements LimitNumberOfOpenProjectsService {
    private int count = 0;
    private int limit = 2;

    @Override
    public int limit() {
        return limit;
    }

    @Override
    public boolean allowOpenNewProject() {
        if (count + 1 <= limit) {
            ++count;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void projectWillClosed() {
        if (--count < 0) {
            count = 0;
        }
    }
}
