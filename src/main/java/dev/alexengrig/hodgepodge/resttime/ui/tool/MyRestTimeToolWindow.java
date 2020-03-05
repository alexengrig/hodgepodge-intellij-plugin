package dev.alexengrig.hodgepodge.resttime.ui.tool;

import com.intellij.openapi.wm.ToolWindow;

import javax.swing.*;

public class MyRestTimeToolWindow {
    private JPanel content;
    private JButton saveButton;
    private JSpinner workTimeSpinner;
    private JSpinner restTimeSpinner;
    private JLabel workTimeLabel;
    private JLabel restTimeLabel;

    public MyRestTimeToolWindow(ToolWindow toolWindow) {
    }

    public JPanel getContent() {
        return content;
    }
}
