/***************************************************************************************************
 * The Snake Game - Jörmungandr
 * File: {@code GridPiece.java}
 * Members: Michal Spano, Malte Bengtsson, Simone Graziosi, Feride Hansson, Anna Mäkinen, Katinka Romanus
 * For DIT094 Mini Project: Team Programming; SEM@GU.
 ***************************************************************************************************/

package com.example.snakegame.snake;

/**
 * This is the objects which represent a single piece of the grid.
 */
public class GridPiece {
    private int xCoordinate;
    private int yCoordinate;

    /**
     * Instantiates a new Grid piece.
     *
     * @param x the x
     * @param y the y
     */
    public GridPiece(int x, int y) {
        xCoordinate = x;
        yCoordinate = y;
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public int getX() { return this.xCoordinate; }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() { return this.yCoordinate; }

    /**
     * Sets x.
     *
     * @param x the x
     */
    public void setX(int x) { this.xCoordinate = x; }

    /**
     * Sets y.
     *
     * @param y the y
     */
    public void setY(int y) { this.yCoordinate = y; }

    @Override
    public String toString() {
        return "SnakePiece{" + "x=" + xCoordinate + ", y=" + yCoordinate + '}';
    }
}