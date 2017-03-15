package tools.ui.components;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MatrixTypesComponent extends UIComponent {

    @Nullable
    private MatrixGeneratorClickListener generatorClickListener;

    public MatrixTypesComponent(
            @NotNull Component component,
            @NotNull JButton diagonal,
            @NotNull JButton random,
            @NotNull JButton randomSym,
            @NotNull JButton hilbert
    ) {
        super(component);

        diagonal.addActionListener(event -> {
            if (generatorClickListener != null) {
                generatorClickListener.onGenerateDiagonalPrevailingClicked();
            }
        });

        random.addActionListener(event -> {
            if (generatorClickListener != null) {
                generatorClickListener.onGenerateRandomClicked();
            }
        });

        randomSym.addActionListener(event -> {
            if (generatorClickListener != null) {
                generatorClickListener.onGenerateRandomSymClicked();
            }
        });

        hilbert.addActionListener(event -> {
            if (generatorClickListener != null) {
                generatorClickListener.onGenerateHilberClicked();
            }
        });
    }

    public void setGeneratorClickListener(@NotNull MatrixGeneratorClickListener clickListener) {
        this.generatorClickListener = clickListener;
    }

    @NotNull
    public static MatrixTypesComponent createMatrixTypesPanel() {
        JPanel panel = new JPanel();
        GridLayout gridLayout = new GridLayout(0, 4);
        panel.setLayout(gridLayout);

        ArrayList<JButton> buttons = new ArrayList<>();
        buttons.add(new JButton("Diagonal-Prevailing"));
        buttons.add(new JButton("Random"));
        buttons.add(new JButton("Random symmetric"));
        buttons.add(new JButton("Hilbert"));

        for (JButton button: buttons) {
            panel.add(button);
        }

        return new MatrixTypesComponent(panel, buttons.get(0), buttons.get(1), buttons.get(2), buttons.get(3));
    }

    public interface MatrixGeneratorClickListener {

        void onGenerateDiagonalPrevailingClicked();

        void onGenerateRandomClicked();

        void onGenerateRandomSymClicked();

        void onGenerateHilberClicked();
    }
}
