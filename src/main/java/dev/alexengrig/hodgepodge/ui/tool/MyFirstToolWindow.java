package dev.alexengrig.hodgepodge.ui.tool;

import com.intellij.openapi.wm.ToolWindow;
import dev.alexengrig.hodgepodge.ui.dialog.MyFirstDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class MyFirstToolWindow {
    private JButton refreshToolWindowButton;
    private JButton hideToolWindowButton;
    private JLabel currentDate;
    private JLabel currentTime;
    private JLabel timeZone;
    private JPanel myToolWindowContent;
    private JButton showDialogButton;

    public MyFirstToolWindow(ToolWindow toolWindow) {
        this.hideToolWindowButton.addActionListener(e -> toolWindow.hide(null));
        this.refreshToolWindowButton.addActionListener(e -> this.currentDateTime());
        this.currentDateTime();
    }

    public void currentDateTime() {
        // Get current date and time
        Calendar instance = Calendar.getInstance();
        currentDate.setText(
                instance.get(Calendar.DAY_OF_MONTH) + "/"
                        + (instance.get(Calendar.MONTH) + 1) + "/"
                        + instance.get(Calendar.YEAR));
        int min = instance.get(Calendar.MINUTE);
        String strMin = min < 10 ? "0" + min : String.valueOf(min);
        currentTime.setText(instance.get(Calendar.HOUR_OF_DAY) + ":" + strMin);
        // Get time zone
        long gmt_Offset = instance.get(Calendar.ZONE_OFFSET); // offset from GMT in milliseconds
        String str_gmt_Offset = String.valueOf(gmt_Offset / 3600000);
        str_gmt_Offset = (gmt_Offset > 0) ? "GMT + " + str_gmt_Offset : "GMT - " + str_gmt_Offset;
        timeZone.setText(str_gmt_Offset);
        showDialogButton.addActionListener(event -> {
            if (new MyFirstDialog("Refresh?").showAndGet()) {
                currentDateTime();
            }
        });
    }

    public JPanel getContent() {
        return myToolWindowContent;
    }
}
