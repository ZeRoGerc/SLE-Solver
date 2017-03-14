package tools.ui.components;

import com.sun.istack.internal.NotNull;

import javax.swing.*;
import java.awt.*;

public class RunPanelComponent extends UIComponent {

    @NotNull
    private final JButton runButton;

    @NotNull
    private final JTextArea textArea;

    public RunPanelComponent(
            @NotNull JPanel panel,
            @NotNull JButton runButton,
            @NotNull JTextArea textArea
    ) {
        super(panel);
        this.runButton = runButton;
        this.textArea = textArea;

        this.runButton.addActionListener(event -> runClicked());
    }

    public void addText(@NotNull String text) {
        textArea.append(text);
    }

    private void runClicked() {
        System.out.println("Running");
    }

    @NotNull
    public static RunPanelComponent createRunPanel() {
        JPanel panel = new JPanel();

        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);

        JButton runButton = new JButton("Run");
        runButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextArea textArea = new JTextArea();
        textArea.setPreferredSize(new Dimension(600, 300));
        textArea.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(runButton);
        panel.add(textArea);

        return new RunPanelComponent(panel, runButton, textArea);
    }
}
