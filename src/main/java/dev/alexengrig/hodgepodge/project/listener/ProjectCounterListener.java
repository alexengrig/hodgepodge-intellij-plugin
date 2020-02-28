package dev.alexengrig.hodgepodge.project.listener;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.project.ProjectManagerListener;
import com.intellij.openapi.ui.Messages;
import dev.alexengrig.hodgepodge.application.service.LimitNumberOfOpenProjectsService;
import org.jetbrains.annotations.NotNull;

public class ProjectCounterListener implements ProjectManagerListener {
    @Override
    public void projectOpened(@NotNull Project project) {
        LimitNumberOfOpenProjectsService limitService = LimitNumberOfOpenProjectsService.getInstance();
        if (!limitService.openedNewProject()) {
            final int limit = limitService.limit();
            final String message = String.format("The maximum number of open projects exceeds %d projects!", limit);
            Messages.showMessageDialog(message, "Error", Messages.getErrorIcon());
            ProjectManager projectManager = ProjectManager.getInstance();
            projectManager.closeProject(project);
        }
    }

    @Override
    public void projectClosed(@NotNull Project project) {
        LimitNumberOfOpenProjectsService limitService = LimitNumberOfOpenProjectsService.getInstance();
        limitService.closedProject();
    }
}
