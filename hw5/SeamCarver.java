import edu.princeton.cs.algs4.Picture;

import java.util.*;

public class SeamCarver {
    private Picture pic;
    public SeamCarver(Picture picture) {
        pic = picture;
    }

    public Picture picture() {
        return pic;
    }                       // current picture

    public int width() {
        return pic.width();
    }                         // width of current picture

    public int height() {
        return pic.height();
    }                        // height of current picture

    public double energy(int x, int y) {
        int x1 = x - 1;
        int x2 = x + 1;
        int y1 = y - 1;
        int y2 = y + 1;
        if (x1 < 0) {
            x1 = width() - 1;
        }
        if (x2 > width() - 1) {
            x2 = 0;
        }
        if (y1 < 0) {
            y1 = height() - 1;
        }
        if (y2 > height() - 1) {
            y2 = 0;
        }
        double energyX = Math.pow((picture().get(x1, y).getRed() - picture().get(x2, y).getRed()), 2) +
                Math.pow((picture().get(x1, y).getBlue() - picture().get(x2, y).getBlue()), 2) +
                Math.pow((picture().get(x1, y).getGreen() - picture().get(x2, y).getGreen()), 2);
        double energyY = Math.pow((picture().get(x, y1).getRed() - picture().get(x, y2).getRed()), 2) +
                Math.pow((picture().get(x, y1).getBlue() - picture().get(x, y2).getBlue()), 2) +
                Math.pow((picture().get(x, y1).getGreen() - picture().get(x, y2).getGreen()), 2);
        return energyX + energyY;
    }            // energy of pixel at column x and row y



    public int[] findHorizontalSeam() {

    }            // sequence of indices for horizontal seam



    public int[] findVerticalSeam() {
        double[] minCost = new double[width()];
        ArrayList<Integer>[] paths = new ArrayList[width()];
        for (int i = 0; i < width(); i++) {
            minCost[i] = energy(0, i);
        }
        for (int i = 0; i < width(); i++) {
            int[] initPoint = new int[]{0, i};
            while (initPoint[0] < height() - 1) {
                double minEnergy = Double.MAX_VALUE;
                Integer[] minPoint = new Integer[2];
                for (Integer[] point : nextMinimumPoints(initPoint[0], initPoint[1])) {
                    double energy = energy(point[0], point[1]);
                    if (energy < minEnergy) {
                        minEnergy = energy;
                        minPoint[0] = point[0];
                        minPoint[1] = point[1];
                    }
                }
                paths[i].add(minPoint[1]);
                initPoint[0] = minPoint[0];
                initPoint[1] = minPoint[1];
                minCost[i] += minEnergy;
            }
        }
        int[] leastEnergyWay = new int[height()];
        leastEnergyWay[0] = minIndex(minCost);
        for (int i = 1; i < height(); i++) {
            leastEnergyWay[i] = paths[leastEnergyWay[0]].get(i);
        }
        return leastEnergyWay;
    }              // sequence of indices for vertical seam

    private int minIndex(double[] minCost) {
        double min = Double.MAX_VALUE;
        int index = 0;
        for (int i = 0; i < minCost.length; i++) {
            if (minCost[i] < min) {
                min = minCost[i];
                index = i;
            }
        }
        return index;
    }

    private Iterable<Integer[]> nextMinimumPoints(int x, int y) {
        List<Integer[]> points = new ArrayList<>();
        if (y == 0) {
            points.add(new Integer[]{x + 1, y});
            points.add(new Integer[]{x + 1, y + 1});
        } else if (y == width() - 1) {
            points.add(new Integer[]{x + 1, y});
            points.add(new Integer[]{x + 1, y - 1});
        } else {
            points.add(new Integer[]{x + 1, y - 1});
            points.add(new Integer[]{x + 1, y});
            points.add(new Integer[]{x + 1, y + 1});
        }
        return points;
    }

    public void removeHorizontalSeam(int[] seam) {

    }   // remove horizontal seam from picture

    public void removeVerticalSeam(int[] seam) {

    }     // remove vertical seam from picture
}
