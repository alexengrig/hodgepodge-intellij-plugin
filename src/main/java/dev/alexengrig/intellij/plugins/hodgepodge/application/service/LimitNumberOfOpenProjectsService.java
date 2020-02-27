package dev.alexengrig.intellij.plugins.hodgepodge.application.service;

import com.intellij.openapi.components.ServiceManager;

public interface LimitNumberOfOpenProjectsService {
    static LimitNumberOfOpenProjectsService getInstance() {
        return ServiceManager.getService(LimitNumberOfOpenProjectsService.class);
    }

    int limit();

    boolean allowOpenNewProject();

    void projectWillClosed();
}
