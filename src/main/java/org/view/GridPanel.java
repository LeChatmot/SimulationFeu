package org.view;

import org.models.Cell;
import org.models.CellState;
import org.models.Grid;
import org.simulation.Simulation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GridPanel extends JPanel {

    private Simulation simulation;
    private int cellSize;

    public GridPanel(Simulation simulation, int cellSize) {
        this.simulation = simulation;
        this.cellSize = cellSize;
        Grid grid = simulation.getGrid();
        setPreferredSize(new Dimension(
                grid.getWidth() * cellSize,
                grid.getHeight() * cellSize
        ));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int col = e.getX() / cellSize;
                int row = e.getY() / cellSize;
                Grid grid = simulation.getGrid();

                if (!grid.isInBounds(row, col)) return;

                Cell cell = grid.getCell(row, col);
                CellState next = switch (cell.getState()) {
                    case TREE    -> CellState.BURNING;
                    case BURNING -> CellState.ASH;
                    case ASH     -> CellState.TREE;
                };
                cell.setState(next);
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Grid grid = this.simulation.getGrid();

        for (int row = 0; row < grid.getHeight(); row++) {
            for (int col = 0; col < grid.getWidth(); col++) {
                g.setColor(getColor(grid.getCell(row, col).getState()));
                g.fillRect(col * this.cellSize, row * this.cellSize, this.cellSize, this.cellSize);
                g.setColor(Color.DARK_GRAY);
                g.drawRect(col * this.cellSize, row * this.cellSize, this.cellSize, this.cellSize);
            }
        }
    }

    private Color getColor(CellState state) {
        return switch (state) {
            case TREE    -> new Color(34, 139, 34);
            case BURNING -> new Color(255, 69, 0);
            case ASH     -> new Color(105, 105, 105);
        };
    }
}