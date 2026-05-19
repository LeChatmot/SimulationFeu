package org.models;

import java.util.Arrays;

public class Grid {

    private final int width;
    private final int height;
    private Cell[][] cells;

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        this.cells = new Cell[height][width];
        this.initGrid();
    }

    private void initGrid() {
        for (int row = 0; row < this.height; row++) {
            for (int col = 0; col < this.width; col++) {
                this.cells[row][col] = new Cell(CellState.TREE);
            }
        }
    }

    public void resetGrid(){
        this.cells = new Cell[this.getHeight()][this.getWidth()];
        this.initGrid();
    }

    public Cell getCell(int row, int col){
        return this.cells[row][col];
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public boolean isInBounds(int row, int col){
        return (row >= 0 && row < this.height) && (col >= 0 && col < this.width);
    }

    public boolean hasBurningCells() {
        return Arrays.stream(this.cells)
                .flatMap(Arrays::stream)
                .anyMatch(Cell::isBurning);
    }
}
