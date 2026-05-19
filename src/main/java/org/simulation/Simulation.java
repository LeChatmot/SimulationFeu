package org.simulation;

import org.models.Cell;
import org.models.CellState;
import org.models.Grid;

import static org.models.CellState.ASH;
import static org.models.CellState.BURNING;

public class Simulation {

    private final Grid grid;
    private final double propagationProbability;
    private int step;

    private static final int[][] DIRECTIONS = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
    };

    public Simulation(Grid grid, double propagationProbability) {
        this.grid = grid;
        this.propagationProbability = propagationProbability;
        this.step = 0;
    }

    public void nextStep() {
        CellState[][] nextStates = computeNextStates();

        this.applyNextStates(nextStates);

        this.step++;
    }

    private CellState[][] computeNextStates() {
        int h = this.grid.getHeight();
        int w = this.grid.getWidth();
        CellState[][] nextStates = new CellState[h][w];

        for (int row = 0; row < h; row++) {
            for (int col = 0; col < w; col++) {
                nextStates[row][col] = this.computeNextState(row, col);
            }
        }
        return nextStates;
    }

    private CellState computeNextState(int row, int col) {
        Cell cell = this.grid.getCell(row, col);

        switch (cell.getState()) {
            case BURNING, ASH:
                return ASH;

            case TREE:
                return isIgnitedByNeighbor(row, col) ? BURNING : CellState.TREE;

            default:
                throw new IllegalStateException("État inconnu : " + cell.getState());
        }
    }

    private boolean isIgnitedByNeighbor(int row, int col) {
        for (int[] dir : DIRECTIONS) {
            int neighborRow = row + dir[0];
            int neighborCol = col + dir[1];

            if (this.grid.isInBounds(neighborRow, neighborCol)
                    && this.grid.getCell(neighborRow, neighborCol).isBurning()
                    && Math.random() < this.propagationProbability) {
                return true;
            }
        }
        return false;
    }

    private void applyNextStates(CellState[][] nextStates) {
        for (int row = 0; row < this.grid.getHeight(); row++) {
            for (int col = 0; col < this.grid.getWidth(); col++) {
                this.grid.getCell(row, col).setState(nextStates[row][col]);
            }
        }
    }

    public Grid getGrid() {
        return this.grid;
    }

    public void reset() {
        this.step = 0;
        this.grid.resetGrid();
    }

    public int getStep() {
        return this.step;
    }

}
