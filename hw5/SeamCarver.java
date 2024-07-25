import edu.princeton.cs.algs4.Picture;

import java.util.*;

public class SeamCarver {
    private Picture pic;
    private int width;
    private int height;
    private boolean isVertical;

    public SeamCarver(Picture picture) {
        pic = picture;
        width = picture.width();
        height = picture.height();
        isVertical = true;
    }

    public Picture picture() {
        return pic;
    }                       // current picture

    public int width() {
        return width;
    }                         // width of current picture

    public int height() {
        return height;
    }                        // height of current picture

    public double energy(int x, int y) {
        int x1 = x + 1;
        int x2 = x - 1; // x is column
        int y1 = y + 1;
        int y2 = y - 1; // y is row

        if (x1 > width() - 1) {
            x1 = 0;
        }
        if (x2 < 0) {
            x2 = width() - 1;
        }
        if (y1 > height() - 1) {
            y1 = 0;
        }
        if (y2 < 0) {
            y2 = height() - 1;
        }

        int rx = pic.get(x, y1).getRed() - pic.get(x, y2).getRed();
        int ry = pic.get(x1, y).getRed() - pic.get(x2, y).getRed();
        int gx = pic.get(x, y1).getGreen() - pic.get(x, y2).getGreen();
        int gy = pic.get(x1, y).getGreen() - pic.get(x2, y).getGreen();
        int bx = pic.get(x, y1).getBlue() - pic.get(x, y2).getBlue();
        int by = pic.get(x1, y).getBlue() - pic.get(x2, y).getBlue();

        return Math.pow(rx, 2) + Math.pow(ry, 2) + Math.pow(gx, 2) + Math.pow(gy, 2) + Math.pow(bx, 2) + Math.pow(by, 2);
    }                       // energy of pixel at column x and row y


    public int[] findHorizontalSeam() {
        isVertical = false;
        int[][][] minimumPointPosition = new int[height()][width()][2];
        double[] multiMinEnergy = new double[height()];
        for (int i = 0; i < height(); i++) {
            multiMinEnergy[i] = energy(0, i);
            minimumPointPosition[i][0][0] = 0;
            minimumPointPosition[i][0][1] = i;
        }

        for (int i = 0; i < height(); i++) {
            for (int j = 1; j < width() ; j++) {
                minimumPointPosition[i][j] = minimumPixel(minimumPointPosition[i][j - 1][0], minimumPointPosition[i][j - 1][1]);
                multiMinEnergy[i] += energy(minimumPointPosition[i][j][0], minimumPointPosition[i][j][1]);
            }
        }

        double min = Double.MAX_VALUE;
        int index = 0;
        for (int i = 0; i < height(); i++) {
            if (min > multiMinEnergy[i]) {
                min = multiMinEnergy[i];
                index = i;
            }
        }

        int[] minimumWay = new int[width()];
        for (int i = 0; i < width(); i++) {
            minimumWay[i] = minimumPointPosition[index][i][1];
        }

        return minimumWay;
    }            // sequence of indices for horizontal seam



    public int[] findVerticalSeam() {
        isVertical = true;
        int[][][] minimumPointPosition = new int[width()][height()][2];
        double[] multiMinEnergy = new double[width()];
        for (int i = 0; i < width(); i++) {
            multiMinEnergy[i] = energy(i, 0);
            minimumPointPosition[i][0][0] = i;
            minimumPointPosition[i][0][1] = 0;
        }

        for (int i = 0; i < width(); i++) {
            for (int j = 1; j < height() ; j++) {
                minimumPointPosition[i][j] = minimumPixel(minimumPointPosition[i][j - 1][0], minimumPointPosition[i][j - 1][1]);
                multiMinEnergy[i] += energy(minimumPointPosition[i][j][0], minimumPointPosition[i][j][1]);
            }
        }

        double min = Double.MAX_VALUE;
        int index = 0;
        for (int i = 0; i < width(); i++) {
            if (min > multiMinEnergy[i]) {
                min = multiMinEnergy[i];
                index = i;
            }
        }

        int[] minimumWay = new int[height()];
        for (int i = 0; i < height(); i++) {
            minimumWay[i] = minimumPointPosition[index][i][0];
        }

        return minimumWay;
    }              // sequence of indices for vertical seam

    private int[] minimumPixel(int initX, int initY) {
        int[] minimumPixelPosition = new int[2];
        if (isVertical) {
            double min = Double.MAX_VALUE;
            for (Integer[] point : nextMinimumPointsVertical(initX, initY)) {
                double e = energy(point[0], point[1]);
                if (e < min) {
                    min = e;
                    minimumPixelPosition[0] = point[0];
                    minimumPixelPosition[1] = point[1];
                }
            }
        } else {
            double min = Double.MAX_VALUE;
            for (Integer[] point : nextMinimumPointsHorizontal(initX, initY)) {
                double e = energy(point[0], point[1]);
                if (e < min) {
                    min = e;
                    minimumPixelPosition[0] = point[0];
                    minimumPixelPosition[1] = point[1];
                }
            }
        }
        return minimumPixelPosition;
    }

    private Iterable<Integer[]> nextMinimumPointsVertical(int x, int y) {
        List<Integer[]> points = new ArrayList<>();
        if (y < height() - 1){
            if (x == 0) {
                points.add(new Integer[]{x + 1, y + 1});
                points.add(new Integer[]{x, y + 1});
            } else if (x == width() - 1) {
                points.add(new Integer[]{x - 1, y + 1});
                points.add(new Integer[]{x, y + 1});
            } else {
                points.add(new Integer[]{x - 1, y + 1});
                points.add(new Integer[]{x, y + 1});
                points.add(new Integer[]{x + 1, y + 1});
            }
        }
        return points;
    }

    private Iterable<Integer[]> nextMinimumPointsHorizontal(int x, int y) {
        List<Integer[]> points = new ArrayList<>();
        if (x < width() - 1){
            if (y == 0) {
                points.add(new Integer[]{x + 1, y});
                points.add(new Integer[]{x + 1, y + 1});
            } else if (y == height() - 1) {
                points.add(new Integer[]{x + 1, y});
                points.add(new Integer[]{x + 1, y - 1});
            } else {
                points.add(new Integer[]{x + 1, y - 1});
                points.add(new Integer[]{x + 1, y});
                points.add(new Integer[]{x + 1, y + 1});
            }
        }
        return points;
    }

    public void removeHorizontalSeam(int[] seam) {
        if (seam.length != width() || !isValidSeam(seam)) {
            throw new IllegalArgumentException();
        }
        SeamRemover.removeHorizontalSeam(pic, seam);
    }   // remove horizontal seam from picture

    public void removeVerticalSeam(int[] seam) {
        if (seam.length != height() || !isValidSeam(seam)) {
            throw new IllegalArgumentException();
        }
        SeamRemover.removeHorizontalSeam(pic, seam);
    }     // remove vertical seam from picture

    private boolean isValidSeam(int[] seam) {
        for (int i = 0, j = 1; j < seam.length; i++, j++) {
            if (Math.abs(seam[i] - seam[j]) > 1) {
                return false;
            }
        }
        return true;
    }
}
