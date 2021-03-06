package dev.alexengrig.hodgepodge.application.service;

import com.intellij.openapi.components.ServiceManager;

public interface LimitNumberOfOpenProjectsService {
    static LimitNumberOfOpenProjectsService getInstance() {
        return ServiceManager.getService(LimitNumberOfOpenProjectsService.class);
    }

    int limit();

    boolean openedNewProject();

    void closedProject();
}
