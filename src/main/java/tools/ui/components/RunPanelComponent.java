package tools.ui.components;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import tools.logic.Result;

import javax.swing.*;
import java.awt.*;

import static java.lang.String.format;

public class RunPanelComponent extends UIComponent {

    @NotNull
    private final JButton yakobiButton;

    @NotNull
    private final JTextArea textArea;

    @Nullable
    private SolveClickListener solveClickListener;

    public RunPanelComponent(
            @NotNull JPanel panel,
            @NotNull JButton yakobiButton,
            @NotNull JTextArea textArea
    ) {
        super(panel);
        this.yakobiButton = yakobiButton;
        this.textArea = textArea;

        this.yakobiButton.addActionListener(event -> {
            if (solveClickListener != null) {
                solveClickListener.onYakobiClicked();
            }
        });
    }

    public void addResultInfo(@NotNull Result result) {
        StringBuilder answer = new StringBuilder();
        answer.append("Result = {");
        for (double x : result.getResult()) {
            answer.append(" ").append(x).append(",");
        }
        answer.deleteCharAt(answer.length() - 1);
        answer.append(" }\n");

        textArea.append(answer.toString());

        textArea.append(format("Iterations: %d\n", result.getIterations()));
        textArea.append(format("Time: %d ms\n", result.getTime()));
        textArea.append(result.isSuccess() ? "Success\n" : "Fail\n");
        textArea.append("\n");
    }

    public void setSolveClickListener(@NotNull SolveClickListener solveClickListener) {
        this.solveClickListener = solveClickListener;
    }

    @NotNull
    public static RunPanelComponent createRunPanel() {
        JPanel panel = new JPanel();

        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);

        JButton runButton = new JButton("Yakobi");
        runButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextArea textArea = new JTextArea();
        textArea.setPreferredSize(new Dimension(600, 300));
        textArea.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(runButton);
        panel.add(textArea);

        return new RunPanelComponent(panel, runButton, textArea);
    }

    public interface SolveClickListener {

        void onYakobiClicked();
    }
}
