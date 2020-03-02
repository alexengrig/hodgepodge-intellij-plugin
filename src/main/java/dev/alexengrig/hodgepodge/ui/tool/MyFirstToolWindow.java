package dev.alexengrig.hodgepodge.ui.tool;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.ui.DialogBuilder;
import com.intellij.openapi.ui.MessageType;
import com.intellij.openapi.wm.ToolWindow;
import dev.alexengrig.hodgepodge.notification.MyInformationNotifier;
import dev.alexengrig.hodgepodge.ui.dialog.MyFirstDialog;

import javax.swing.*;
import java.util.Calendar;

import static com.intellij.notification.NotificationType.INFORMATION;

public class MyFirstToolWindow {
    private MyInformationNotifier myInformationNotifier;

    private JButton refreshToolWindowButton;
    private JButton hideToolWindowButton;
    private JLabel currentDate;
    private JLabel currentTime;
    private JLabel timeZone;
    private JPanel myToolWindowContent;
    private JButton showDialogButton;
    private JButton dialogNotifyButton;
    private JButton notifyButton;

    public MyFirstToolWindow(ToolWindow toolWindow) {
        this.myInformationNotifier = new MyInformationNotifier("My notification group");
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
        dialogNotifyButton.addActionListener(event -> {
            final DialogBuilder dialogBuilder = new DialogBuilder();
            dialogBuilder.setTitle("Refresh?");
            if (dialogBuilder.showAndGet()) {
                currentDateTime();
            }
        });
        notifyButton.addActionListener(event -> {
            myInformationNotifier.notify("Notification");
        });
    }

    public JPanel getContent() {
        return myToolWindowContent;
    }
}
