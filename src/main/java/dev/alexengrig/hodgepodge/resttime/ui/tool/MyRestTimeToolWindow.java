package dev.alexengrig.hodgepodge.resttime.ui.tool;

import com.intellij.openapi.wm.ToolWindow;
import dev.alexengrig.hodgepodge.resttime.application.service.MyRestTimeService;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.text.ParseException;

public class MyRestTimeToolWindow {
    private JPanel content;
    private JButton saveButton;
    private JSpinner workTimeSpinner;
    private JSpinner restTimeSpinner;

    public MyRestTimeToolWindow(ToolWindow toolWindow) {
        saveButton.addActionListener(e -> {
            int workTime = getWorkTime();
            int restTime = getRestTime();
            MyRestTimeService.getInstance().updateTimes(workTime, restTime);
        });
        workTimeSpinner.addChangeListener(e -> {
            if (saveButton.isEnabled() && MyRestTimeService.getInstance().getWorkTime() != getWorkTime()) {
                saveButton.setEnabled(false);
            }
            if (!saveButton.isEnabled() && MyRestTimeService.getInstance().getWorkTime() != getWorkTime()) {
                saveButton.setEnabled(true);
            }
        });
    }

    private int getWorkTime() {
        try {
            workTimeSpinner.commitEdit();
        } catch (ParseException ignore) {
        }
        return (int) workTimeSpinner.getValue();
    }

    private int getRestTime() {
        try {
            restTimeSpinner.commitEdit();
        } catch (ParseException ignore) {
        }
        return (int) restTimeSpinner.getValue();
    }

    public JPanel getContent() {
        return content;
    }
}
