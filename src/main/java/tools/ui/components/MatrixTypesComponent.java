package tools.ui.components;

import com.sun.istack.internal.NotNull;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MatrixTypesComponent extends UIComponent {

    @NotNull
    private final ArrayList<JButton> buttons;

    public MatrixTypesComponent(@NotNull Component component, @NotNull ArrayList<JButton> buttons) {
        super(component);
        this.buttons = buttons;
    }

    @NotNull
    public static MatrixTypesComponent createMatrixTypesPanel() {
        JPanel panel = new JPanel();
        GridLayout gridLayout = new GridLayout(0, 2);
        panel.setLayout(gridLayout);

        ArrayList<JButton> buttons = new ArrayList<>();
        buttons.add(new JButton("Random"));
        buttons.add(new JButton("Hilbert"));

        for (JButton button: buttons) {
            panel.add(button);
        }

        return new MatrixTypesComponent(panel, buttons);
    }
}
