package tools.ui;

import com.sun.istack.internal.NotNull;
import tools.logic.LogicComponentsProvider;
import tools.ui.components.MatrixComponent;
import tools.ui.components.MatrixTypesComponent;
import tools.ui.components.RunPanelComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;
import static tools.ui.components.MatrixComponent.createMatrix;
import static tools.ui.components.MatrixTypesComponent.createMatrixTypesPanel;
import static tools.ui.components.RunPanelComponent.createRunPanel;

public class MainScreen {

    @NotNull
    private final MainScreenDelegate delegate;

    @NotNull
    private JFrame mainFrame;

    @NotNull
    private MatrixTypesComponent matrixTypesComponent;

    @NotNull
    private MatrixComponent matrixComponent;

    @NotNull
    private RunPanelComponent runPanelComponent;


    public MainScreen(@NotNull LogicComponentsProvider logicComponentsProvider) {
        this.delegate = new MainScreenDelegate(logicComponentsProvider);
        prepareGUI();
    }



    public void show(){
        mainFrame.setVisible(true);
    }

    private void prepareGUI(){
        mainFrame = new JFrame("SLE Solver");
        mainFrame.setSize(800,800);
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(@NotNull WindowEvent windowEvent){
                System.exit(0);
            }
        });

        matrixTypesComponent = createMatrixTypesPanel();
        matrixComponent = createMatrix();
        runPanelComponent = createRunPanel();

        addWithDefaultPaddings(matrixTypesComponent.component(), NORTH);
        mainFrame.add(matrixComponent.component(), CENTER);
        mainFrame.add(runPanelComponent.component(), SOUTH);
    }

    private void addWithDefaultPaddings(@NotNull Component component, @NotNull String position) {
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        controlPanel.add(component);

        mainFrame.add(controlPanel, position);
    }
}
