package dev.alexengrig.hodgepodge.resttime.ui.tool;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

public class MyRestTimeToolWindowFactory implements ToolWindowFactory {
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        final MyRestTimeToolWindow myToolWindow = new MyRestTimeToolWindow();
        final ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        final Content myContent = contentFactory.createContent(myToolWindow.getContent(), "", false);
        toolWindow.getContentManager().addContent(myContent);
    }
}
