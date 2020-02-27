package dev.alexengrig.intellij.plugins.hodgepodge.project.component;

import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.ui.Messages;
import dev.alexengrig.intellij.plugins.hodgepodge.application.service.LimitNumberOfOpenProjectsService;

public class ProjectCounterComponent implements ProjectComponent {
    @Override
    public void projectOpened() {
        final LimitNumberOfOpenProjectsService limitService = ServiceManager.getService(
                LimitNumberOfOpenProjectsService.class);
        if (!limitService.allowOpenNewProject()) {
            final int limit = limitService.limit();
            final String message = String.format("The maximum number of opened projects exceeds %d projects!", limit);
            Messages.showMessageDialog(message, "Error", Messages.getErrorIcon());
            final ProjectManager projectManager = ProjectManager.getInstance();
            final Project[] openProjects = projectManager.getOpenProjects();
            if (openProjects.length > 0) {
                final Project lastOpenProjects = openProjects[openProjects.length - 1];
                projectManager.closeProject(lastOpenProjects);
            }
        }
    }

    @Override
    public void projectClosed() {
        final LimitNumberOfOpenProjectsService limitService = ServiceManager.getService(
                LimitNumberOfOpenProjectsService.class);
        limitService.projectWillClosed();
    }
}
