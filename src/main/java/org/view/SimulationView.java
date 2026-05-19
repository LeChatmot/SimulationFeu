package org.view;

import org.simulation.Simulation;

import javax.swing.*;
import java.awt.*;

public class SimulationView extends JFrame {

    private final Simulation simulation;
    private final int cellSize;
    private GridPanel gridPanel;
    private Timer timer;
    private JLabel stepLabel;
    private final SetupView setupView; // référence vers le setup

    public SimulationView(Simulation simulation, int cellSize, SetupView setupView) {
        this.simulation = simulation;
        this.cellSize = cellSize;
        this.setupView = setupView;
        this.initUI();
    }

    private void initUI() {
        this.setTitle("Forest Fire Simulation");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);

        this.gridPanel = new GridPanel(this.simulation, this.cellSize);
        this.add(this.gridPanel, BorderLayout.CENTER);

        JPanel controls = new JPanel();

        JButton startBtn  = new JButton("Start");
        JButton pauseBtn  = new JButton("Pause");
        JButton stepBtn   = new JButton("Step");
        JButton resetBtn  = new JButton("Reset");
        JButton backBtn   = new JButton("← Retour");
        this.stepLabel = new JLabel("Étape : 0");

        controls.add(startBtn);
        controls.add(pauseBtn);
        controls.add(stepBtn);
        controls.add(resetBtn);
        controls.add(backBtn);
        controls.add(this.stepLabel);
        this.add(controls, BorderLayout.SOUTH);

        this.timer = new Timer(300, e -> {
            if (this.simulation.getGrid().hasBurningCells()) {
                this.simulation.nextStep();
                this.stepLabel.setText("Étape : " + this.simulation.getStep());
                this.gridPanel.repaint();
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
                this.stepLabel.setText("Étape : " + this.simulation.getStep());
                this.gridPanel.repaint();
            }
        });

        resetBtn.addActionListener(e -> {
            this.timer.stop();
            this.simulation.reset();
            this.stepLabel.setText("Étape : 0");
            this.gridPanel.repaint();
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
