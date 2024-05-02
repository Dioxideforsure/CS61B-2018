package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] blockSite;  // if false, the site is blocked. vice versa.
    private final int length;
    private WeightedQuickUnionUF site1;
    private int count;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        blockSite = new boolean[N][N];
        length = N;
        site1 = new WeightedQuickUnionUF(N * N);
        for (int i = 0; i < length; i++) {
            site1.union(0, i);
        } // Union the whole top row, which will make the top row full.
        for (int i = toOneDimension(length - 1, 0); i < toOneDimension(length - 1, length); i++) {
            site1.union(i, toOneDimension(length - 1, length) - 1);
        }  // Union the whole bottom row, which will make the bottom row full.
        count = 0;
    }// create N-by-N grid, with all sites initially blocked

    public void open(int row, int col) {
        wrongArgument(row, col);
        if (!isOpen(row, col)) {
            blockSite[row][col] = true;
            if (row == 0) {
                if (isOpen(row + 1, col)) {
                    site1.union(toOneDimension(row, col), toOneDimension(row + 1, col));
                }
            } else if (row == length - 1) {
                if (isOpen(row - 1, col)) {
                    site1.union(toOneDimension(row, col), toOneDimension(row - 1, col));
                }
            } else {
                if (isOpen(row - 1, col)) {
                    site1.union(toOneDimension(row, col), toOneDimension(row - 1, col));
                }
                if (isOpen(row + 1, col)) {
                    site1.union(toOneDimension(row, col), toOneDimension(row + 1, col));
                }
            }// Inspect whether should union with neighbor
            // The condition has 3 types:
            // 1. The first row (1st row), whether union with the top row (0th row).
            // 2. The last row (n-2th row), whether union with the bottom row (n-1th row).
            // 3. The middle row, whether union with neighbor.
            if (col == 0) {
                if (isOpen(row, col + 1)) {
                    site1.union(toOneDimension(row, col), toOneDimension(row, col + 1));
                }
            } else if (col == length - 1) {
                if (isOpen(row, col - 1)) {
                    site1.union(toOneDimension(row, col), toOneDimension(row, col - 1));
                }
            } else {
                if (isOpen(row, col - 1))
                    site1.union(toOneDimension(row, col), toOneDimension(row, col - 1));
                if (isOpen(row, col + 1))
                    site1.union(toOneDimension(row, col), toOneDimension(row, col + 1));
            }// Inspect whether should union with neighbor
            // The condition has 3 types:
            // 1. The first column (1st column), whether union with the leftmost column (0th column).
            // 2. The last column (n-2th row), whether union with the rightmost column (n-1th column).
            // 3. The middle column, whether union with neighbor.
            count++;
        }
    }   // open the blockSite (row, col) if it is not open already

    public boolean isOpen(int row, int col) {
        wrongArgument(row, col);
        return blockSite[row][col];
    }// is the blockSite (row, col) open?

    public boolean isFull(int row, int col) {
        wrongArgument(row, col);
        if (!isOpen(row, col)){
            return false;
        }
        return site1.find(toOneDimension(row, col)) == 0;
    }// is the blockSite (row, col) full?

    public int numberOfOpenSites() {
        return count;
    }           // number of open sites

    public boolean percolates() {
        return site1.connected(0, length * length - 1);
    }              // does the system percolate?

    private int toOneDimension(int row, int col) {
        return row * length + col;
    }

    private void wrongArgument(int row, int col) {
        if (row < 0 || col < 0 || row >= length || col >= length) {
            throw new IndexOutOfBoundsException();
        }
    }


    public static void main(String[] args) {
        Percolation percolation = new Percolation(3);
        System.out.println(percolation.isOpen(0, 0));
        System.out.println(percolation.isFull(0, 0));
        System.out.println(percolation.percolates());
        percolation.open(0, 0);
        System.out.println(percolation.isOpen(0, 0));
        System.out.println(percolation.percolates());
        percolation.open(1, 0);
        percolation.open(2, 0);
        System.out.println(percolation.isFull(0, 0));
        System.out.println(percolation.numberOfOpenSites());
        System.out.println(percolation.percolates());
    }
    // use for unit testing (not required)
}
