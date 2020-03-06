package dev.alexengrig.hodgepodge.resttime.ui.tool;

import dev.alexengrig.hodgepodge.resttime.application.service.MyRestTimeService;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.event.ChangeListener;

public class MyRestTimeToolWindow {
    private JPanel content;
    private JButton saveButton;
    private JSpinner workTimeSpinner;
    private JSpinner restTimeSpinner;
    private JButton cancelButton;

    public MyRestTimeToolWindow() {
        initComponents();
    }

    private void initComponents() {
        saveButton.setEnabled(false);
        saveButton.addActionListener(e -> {
            int workTime = getWorkTime();
            int restTime = getRestTime();
            MyRestTimeService.getInstance().updateTimes(workTime, restTime);
            saveButton.setEnabled(false);
        });

        cancelButton.addActionListener(e -> {
            final MyRestTimeService myRestTimeService = MyRestTimeService.getInstance();
            workTimeSpinner.getModel().setValue(myRestTimeService.getWorkTime());
            restTimeSpinner.getModel().setValue(myRestTimeService.getRestTime());
        });

        workTimeSpinner.setModel(getWorkTimeSpinnerModel());
        workTimeSpinner.addChangeListener(getChangesListener());

        restTimeSpinner.setModel(getRestTimeSpinnerModel());
        restTimeSpinner.addChangeListener(getChangesListener());
    }

    private SpinnerNumberModel getWorkTimeSpinnerModel() {
        MyRestTimeService myRestTimeService = MyRestTimeService.getInstance();
        return new SpinnerNumberModel(
                myRestTimeService.getWorkTime(),
                myRestTimeService.getMinWorkTime(),
                myRestTimeService.getMaxWorkTime(),
                myRestTimeService.getTimeStepSize());
    }

    private SpinnerNumberModel getRestTimeSpinnerModel() {
        MyRestTimeService myRestTimeService = MyRestTimeService.getInstance();
        return new SpinnerNumberModel(
                myRestTimeService.getRestTime(),
                myRestTimeService.getMinRestTime(),
                myRestTimeService.getMaxRestTime(),
                myRestTimeService.getTimeStepSize());
    }

    @NotNull
    private ChangeListener getChangesListener() {
        return e -> {
            final MyRestTimeService myRestTimeService = MyRestTimeService.getInstance();
            if (myRestTimeService.getWorkTime() != getWorkTime() || myRestTimeService.getRestTime() != getRestTime()) {
                saveButton.setEnabled(true);
            } else {
                saveButton.setEnabled(false);
            }
        };
    }

    private int getWorkTime() {
        return (int) workTimeSpinner.getModel().getValue();
    }

    private int getRestTime() {
        return (int) restTimeSpinner.getModel().getValue();
    }

    public JPanel getContent() {
        return content;
    }
}
