package dev.alexengrig.hodgepodge.notification;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.project.Project;

public class MyInformationNotifier {
  private final NotificationGroup myNotificationGroup;

  public MyInformationNotifier(String displayId) {
    this.myNotificationGroup = getOrCreateNotificationGroup(displayId);
  }

  private static NotificationGroup getOrCreateNotificationGroup(String displayId) {
    final NotificationGroup notificationGroup = NotificationGroup.findRegisteredGroup(displayId);
    if (notificationGroup != null) {
      return notificationGroup;
    } else {
      return NotificationGroup.balloonGroup(displayId);
    }
  }

  public Notification notify(String content) {
    return notify(null, content);
  }

  public Notification notify(Project project, String content) {
    final Notification notification = myNotificationGroup.createNotification(content, NotificationType.INFORMATION);
    notification.notify(project);
    return notification;
  }
}
