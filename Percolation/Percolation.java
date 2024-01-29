/* *****************************************************************************
 *  Name:              Kaveh Hashemi
 *  Coursera User ID:  123456
 *  Last modified:     27/1/2024
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int n;
    private boolean[] grid;
    private WeightedQuickUnionUF uf;
    private int openSites;


    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        else {
            this.n = n;
            grid = new boolean[n * n + 2];
            uf = new WeightedQuickUnionUF(n * n + 2);
            openSites = 0;

            for (int i = 0; i < n * n + 2; i++) {
                grid[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        
    }

    private int twoDToLinear(int row, int col) {
        return ((row - 1) * n + col);
    }


    public void open(int row, int col) {
        checkValidity(row, col);

        int currentSite = twoDToLinear(row, col);
        if (!grid[currentSite]) {
            int topNeighbor = -1;
            int bottomNeighbor = -1;
            int rightNeighbor = -1;
            int leftNeighbor = -1;
            if (row > 1) {
                topNeighbor = twoDToLinear(row - 1, col);
            }
            if (row < n) {
                bottomNeighbor = twoDToLinear(row + 1, col);
            }
            if (col > 1) {
                leftNeighbor = twoDToLinear(row, col - 1);
            }
            if (col < n) {
                rightNeighbor = twoDToLinear(row, col + 1);
            }

            grid[currentSite] = true;
            openSites++;

            if (topNeighbor != -1 && grid[topNeighbor]) {
                uf.union(currentSite, topNeighbor);
            }
            if (bottomNeighbor != -1 && grid[bottomNeighbor]) {
                uf.union(currentSite, bottomNeighbor);
            }
            if (rightNeighbor != -1 && grid[rightNeighbor]) {
                uf.union(currentSite, rightNeighbor);
            }
            if (leftNeighbor != -1 && grid[leftNeighbor]) {
                uf.union(currentSite, leftNeighbor);
            }
            if (topNeighbor == -1) {
                uf.union(currentSite, 0);
            }
            if (bottomNeighbor == -1) {
                uf.union(currentSite, n * n + 1);
            }

        }

    }

    public boolean isOpen(int row, int col) {
        checkValidity(row, col);
        return grid[twoDToLinear(row, col)];
    }

    public boolean isFull(int row, int col) {
        checkValidity(row, col);
        return uf.find(twoDToLinear(row, col)) == uf.find(0);
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        return uf.find(0) == uf.find(n * n + 1);
    }

    private void checkValidity(int row, int col) {
        if (row > n || col > n || row < 1 || col < 1) {
            throw new IllegalArgumentException();
        }
    }

}
