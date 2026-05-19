package org.view;

import org.simulation.Simulation;

import javax.swing.*;
import java.awt.*;

public class SimulationView extends JFrame {

    private final Simulation simulation;
    private final int cellSize;
    private Timer timer;
    private final SetupView setupView;

    public SimulationView(Simulation simulation, int cellSize, SetupView setupView) {
        this.simulation = simulation;
        this.cellSize = cellSize;
        this.setupView = setupView;
        this.initUI();
    }

    private void initUI() {
        this.setTitle("Forest Fire Simulation");
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setResizable(false);

        GridPanel gridPanel = new GridPanel(this.simulation, this.cellSize);
        this.add(gridPanel, BorderLayout.CENTER);

        JPanel controls = new JPanel();

        JButton startBtn  = new JButton("Start");
        JButton pauseBtn  = new JButton("Pause");
        JButton stepBtn   = new JButton("Step");
        JButton resetBtn  = new JButton("Reset");
        JButton backBtn   = new JButton("<- Retour");
        JLabel stepLabel = new JLabel("Etape : 0");

        controls.add(startBtn);
        controls.add(pauseBtn);
        controls.add(stepBtn);
        controls.add(resetBtn);
        controls.add(backBtn);
        controls.add(stepLabel);
        this.add(controls, BorderLayout.SOUTH);

        this.timer = new Timer(300, e -> {
            if (this.simulation.getGrid().hasBurningCells()) {
                this.simulation.nextStep();
                stepLabel.setText("Étape : " + this.simulation.getStep());
                gridPanel.repaint();
            } else {
                this.timer.stop();
            }
        });

        startBtn.addActionListener(e -> this.timer.start());

        pauseBtn.addActionListener(e -> this.timer.stop());

        stepBtn.addActionListener(e -> {
            this.timer.stop();
            if (this.simulation.getGrid().hasBurningCells()) {
                this.simulation.nextStep();
                stepLabel.setText("Étape : " + this.simulation.getStep());
                gridPanel.repaint();
            }
        });

        resetBtn.addActionListener(e -> {
            this.timer.stop();
            this.simulation.reset();
            stepLabel.setText("Étape : 0");
            gridPanel.repaint();
        });

        backBtn.addActionListener(e -> {
            this.timer.stop();
            this.dispose();
            this.setupView.setVisible(true);
        });

        this.pack();
        this.setLocationRelativeTo(null);
    }


}
