package tools.ui.components;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import tools.logic.Result;

import javax.swing.*;
import java.awt.*;

import static java.lang.String.format;

public class RunPanelComponent extends UIComponent {

    @NotNull
    private final JButton gaussButton;

    @NotNull
    private final JButton yakobiButton;

    @NotNull
    private final JButton relaxButton;

    @NotNull
    private final JButton descentButton;

    @NotNull
    private final JButton condButton;

    @NotNull
    private final JTextArea textArea;

    @Nullable
    private SolveClickListener solveClickListener;

    public RunPanelComponent(
            @NotNull JPanel panel,
            @NotNull JButton gaussButton,
            @NotNull JButton yakobiButton,
            @NotNull JButton relaxButton,
            @NotNull JButton descentButton,
            @NotNull JButton condButton,
            @NotNull JTextArea textArea
    ) {
        super(panel);
        this.gaussButton = gaussButton;
        this.yakobiButton = yakobiButton;
        this.relaxButton = relaxButton;
        this.descentButton = descentButton;
        this.condButton = condButton;
        this.textArea = textArea;
        this.textArea.setEditable(false);

        this.gaussButton.addActionListener(event -> {
            if (solveClickListener != null) {
                solveClickListener.onGaussMethodClicked();
            }
        });

        this.yakobiButton.addActionListener(event -> {
            if (solveClickListener != null) {
                solveClickListener.onYakobiMethodClicked();
            }
        });

        this.relaxButton.addActionListener(event -> {
            if (solveClickListener != null) {
                solveClickListener.onRelaxationMethodClicked();
            }
        });

        this.descentButton.addActionListener(event -> {
            if (solveClickListener != null) {
                solveClickListener.onDescentMethodClicked();
            }
        });

        this.condButton.addActionListener(event -> {
            if (solveClickListener != null) {
                solveClickListener.onCondClicked();
            }
        });
    }

    public void writeCond(double cond) {
        textArea.append("cond = " + cond + "\n\n");
    }

    public void addResultInfo(@NotNull Result result) {
        StringBuilder answer = new StringBuilder();
        answer.append("Result = {");
        for (double x : result.getResult()) {
            String s = String.format("%.03f", x);
            s = s.replace(",", "."); // todo
            answer.append(" ").append(s).append(",");
        }
        answer.deleteCharAt(answer.length() - 1);
        answer.append(" }\n");

        textArea.append(result.getDesc() + "\n");
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

        JPanel buttonsPanel = new JPanel();
        GridLayout buttonsLayout = new GridLayout(0, 5);
        buttonsPanel.setLayout(buttonsLayout);

        JButton gaussButton = new JButton("Gauss");
        JButton yakobiButton = new JButton("Yakobi");
        JButton relaxButton = new JButton("Relaxation");
        JButton descentButton = new JButton("Descent");
        JButton condButton = new JButton("Calc cond");

        buttonsPanel.add(gaussButton);
        buttonsPanel.add(yakobiButton);
        buttonsPanel.add(relaxButton);
        buttonsPanel.add(descentButton);
        buttonsPanel.add(condButton);
        panel.add(buttonsPanel);

        JTextArea textArea = new JTextArea();
        textArea.setMargin(new Insets(10, 10, 10, 10));
        JScrollPane areaScrollPane = new JScrollPane(textArea);

        areaScrollPane.setPreferredSize(new Dimension(600, 300));
        areaScrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(areaScrollPane);

        return new RunPanelComponent(
                panel,
                gaussButton,
                yakobiButton,
                relaxButton,
                descentButton,
                condButton,
                textArea
        );
    }

    public interface SolveClickListener {

        void onGaussMethodClicked();

        void onYakobiMethodClicked();

        void onRelaxationMethodClicked();

        void onDescentMethodClicked();

        void onCondClicked();
    }
}
