package tools.ui;

import com.sun.istack.internal.NotNull;
import tools.logic.Result;
import tools.ui.components.MatrixComponent;
import tools.ui.components.MatrixTypesComponent;
import tools.ui.components.MatrixTypesComponent.MatrixGeneratorClickListener;
import tools.ui.components.RunPanelComponent;
import tools.ui.components.RunPanelComponent.SolveClickListener;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static java.awt.BorderLayout.*;
import static tools.ui.components.MatrixComponent.createMatrix;
import static tools.ui.components.MatrixTypesComponent.createMatrixTypesPanel;
import static tools.ui.components.RunPanelComponent.createRunPanel;

public class MainScreen implements MatrixGeneratorClickListener, SolveClickListener {

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


    public MainScreen() {
        this.delegate = new MainScreenDelegate();
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
        matrixTypesComponent.setGeneratorClickListener(this);
        matrixComponent = createMatrix();
        runPanelComponent = createRunPanel();
        runPanelComponent.setSolveClickListener(this);

        mainFrame.add(matrixTypesComponent.component(), NORTH);
        mainFrame.add(matrixComponent.component(), CENTER);
        mainFrame.add(runPanelComponent.component(), SOUTH);
    }

    @Override
    public void onGaussMethodClicked() {
        Result result = delegate.solveWithGauss(matrixComponent.getEquation());
        runPanelComponent.addResultInfo(result);
    }

    @Override
    public void onYakobiMethodClicked() {
        Result result = delegate.solveWithYakobi(matrixComponent.getEquation());
        runPanelComponent.addResultInfo(result);
    }

    @Override
    public void onRelaxationMethodClicked() {
        Result result = delegate.solveWithZeidel(matrixComponent.getEquation());
        runPanelComponent.addResultInfo(result);
    }

    @Override
    public void onDescentMethodClicked() {
        Result result = delegate.solveWithDescent(matrixComponent.getEquation());
        runPanelComponent.addResultInfo(result);
    }

    @Override
    public void onGenerateDiagonalPrevailingClicked() {
        matrixComponent.setMatrix(delegate.generateDiagonalPrevailingMatrix());
    }

    @Override
    public void onGenerateRandomClicked() {
        matrixComponent.setMatrix(delegate.generateRandomMatrix());
    }

    @Override
    public void onGenerateHilberClicked() {
        matrixComponent.setMatrix(delegate.generateHilbertMatrix());
    }
}
