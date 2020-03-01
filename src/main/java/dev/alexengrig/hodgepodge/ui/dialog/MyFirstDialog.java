package dev.alexengrig.hodgepodge.ui.dialog;

import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class MyFirstDialog extends DialogWrapper {
    private final String message;

    public MyFirstDialog(String message) {
        super(true);
        this.message = message;
        init();
        setTitle("My FirstDialog");
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        JPanel dialogPanel = new JPanel(new BorderLayout());

        JLabel label = new JLabel(message);
        label.setPreferredSize(new Dimension(100, 100));
        dialogPanel.add(label, BorderLayout.CENTER);

        return dialogPanel;
    }
}
