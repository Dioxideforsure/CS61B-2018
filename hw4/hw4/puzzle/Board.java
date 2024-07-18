package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

import java.util.Arrays;
import java.util.Objects;

public class Board implements WorldState {

    private int[][] tiles;
    private int N;

    public Board(int[][] tiles) {
        N = tiles.length;
        this.tiles = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                this.tiles[i][j] = tiles[i][j];
            }
        }
    }

    public int tileAt(int i, int j) {
        if (i < 0 || j < 0 || i >= size() || j >= size()) {
            throw new IndexOutOfBoundsException();
        }
        return tiles[i][j];
    }

    public int size() {
        return N;
    }


    public int hamming() {
        int hammingCount = -1;
        int expectedNumber = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tileAt(i, j) != expectedNumber) {
                    hammingCount++;
                }
                expectedNumber++;
            }
        }
        return hammingCount;
    }

    public int manhattan() {
        int manhattanCount = -1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tileAt(i, j) != i * N + j + 1) {
                    manhattanCount += Math.abs(i - (tileAt(i, j) - 1) / N) + Math.abs(j - ((tileAt(i, j) - 1) % N));
                }
            }
        }
        return manhattanCount;
    }


    public boolean equals(Object y) {
        if (this == y) {
            return true;
        }
        if (y == null) {
            return false;
        }
        if (y.getClass() != this.getClass()) {
            return false;
        }
        return Arrays.deepEquals(((Board) y).tiles, this.tiles);

    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

    @Override
    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int blankRow = -1;
        int blankColumn = -1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tileAt(i, j) == 0) {
                    blankRow = i;
                    blankColumn = j;
                }
            }
        }
        int[][] newTiles = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                newTiles[i][j] = tileAt(i, j);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (Math.abs(i - blankRow) + Math.abs(j - blankColumn) == 1) {
                    newTiles[blankRow][blankColumn] = tileAt(i, j);
                    newTiles[i][j] = 0;
                    Board b = new Board(newTiles);
                    neighbors.enqueue(b);
                    newTiles[blankRow][blankColumn] = 0;
                    newTiles[i][j] = tileAt(i, j);
                }
            }
        }
        return neighbors;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
