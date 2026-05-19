package org.view;

import org.models.Grid;
import org.simulation.Simulation;

import javax.swing.*;
import java.awt.*;

public class SetupView extends JFrame {

    public SetupView() {
        this.initUI();
    }

    private void initUI() {
        this.setTitle("Configuration");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel widthLabel  = new JLabel("Largeur :");
        JLabel heightLabel = new JLabel("Hauteur :");
        JTextField widthField  = new JTextField("20", 5);
        JTextField heightField = new JTextField("20", 5);

        JLabel probLabel     = new JLabel("Probabilité de propagation :");
        JSlider probSlider   = new JSlider(0, 100, 50);
        JLabel probValue     = new JLabel("0.50");

        probSlider.setMajorTickSpacing(25);
        probSlider.setMinorTickSpacing(5);
        probSlider.setPaintTicks(true);
        probSlider.setPaintLabels(true);

        probSlider.addChangeListener(e ->
                probValue.setText(String.format("%.2f", probSlider.getValue() / 100.0))
        );

        JButton startBtn = new JButton("Lancer la simulation");

        gbc.gridx = 0; gbc.gridy = 0; add(widthLabel, gbc);
        gbc.gridx = 1;                add(widthField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; add(heightLabel, gbc);
        gbc.gridx = 1;                add(heightField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2; add(probLabel, gbc);
        gbc.gridy = 3;                                     add(probSlider, gbc);
        gbc.gridy = 4; gbc.gridwidth = 1; gbc.gridx = 1;  add(probValue, gbc);

        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;  add(startBtn, gbc);

        startBtn.addActionListener(e -> {
            try {
                int width  = Integer.parseInt(widthField.getText().trim());
                int height = Integer.parseInt(heightField.getText().trim());

                if (width < 2 || height < 2) {
                    JOptionPane.showMessageDialog(this, "Taille minimale : 2x2");
                    return;
                }

                double probability = probSlider.getValue() / 100.0;
                Grid grid = new Grid(width, height);
                Simulation simulation = new Simulation(grid, probability);

                SimulationView view = new SimulationView(simulation, 20, this);
                view.setVisible(true);
                this.setVisible(false);

            } catch (NumberFormatException _) {
                JOptionPane.showMessageDialog(this, "Veuillez entrer des nombres valides.");
            }
        });

        this.pack();
        this.setLocationRelativeTo(null);
    }
}
