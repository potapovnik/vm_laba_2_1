import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.Double.NaN;

public class Rang {
    double[][] matrix;

    public Rang(double[][] matrix) {
        this.matrix = matrix;
    }

    public Rang() {
        String path = "D:\\VM\\laba2_1\\src\\input";
        {
            Scanner scan;
            try {
                scan = new Scanner(new File(path)).useDelimiter("\n");
                int i = 0;
                int N;
                while (scan.hasNext()) {
                    String s = scan.next();
                    if (i == 0) {
                        Scanner sc = new Scanner(s).useDelimiter(" ");
                        while (sc.hasNext()) {
                            N = (int) (Double.parseDouble(sc.next()));
                            matrix=new double[N][N];
                        }
                        i++;
                    } else {
                        Scanner sc = new Scanner(s).useDelimiter(" ");
                        int j = 0;
                        while (sc.hasNext()) {
                            matrix[i - 1][j] = (Double.parseDouble(sc.next()));
                            j++;
                        }
                        i++;
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
                e.printStackTrace();
            }
        }
    }


    public int[] searchMax(int position, int N) {
        int keepI = position;
        int keepJ = position;
        for (int i = position; i < N; i++) {
            for (int j = position; j < N; j++) {
                if (Math.abs(matrix[i][j]) > Math.abs(matrix[keepI][keepJ])) {
                    keepI = i;
                    keepJ = j;
                }
            }
        }
        int[] pos = new int[2];
        pos[0] = keepI;
        pos[1] = keepJ;
        return pos;
    }

    public void swapRow(int first, int second, int N) {
        double[] keep = new double[N];
        for (int i = 0; i < N; i++) {
            keep[i] = matrix[first][i];
            matrix[first][i] = matrix[second][i];
            matrix[second][i] = keep[i];
        }
    }

    public void swapColumns(int first, int second, int N) {
        double[] keep = new double[N];
        for (int i = 0; i < N; i++) {
            keep[i] = matrix[i][first];
            matrix[i][first] = matrix[i][second];
            matrix[i][second] = keep[i];
        }
    }

    public int nulling(int position, int N) {
        double max = matrix[position][position];
        // if (max == 0) {
        //   return 1;
        //} else {
        double[] keepCurrentRow = new double[N];
        double[] keepCurrentRowForUpdate = new double[N];
        for (int i = position; i < N; i++) {
            keepCurrentRow[i] = matrix[position][i];
            keepCurrentRowForUpdate[i] = keepCurrentRow[i];
        }
        for (int i = position + 1; i < N; i++) {
            double del = matrix[i][position] / max;
            for (int k = 0; k < N; k++) {
                keepCurrentRowForUpdate[k] = keepCurrentRow[k] * del;
            }
            for (int j = position; j < N; j++) {

                matrix[i][j] -= keepCurrentRowForUpdate[j];
            }
        }
        //}
        return 0;
    }

    public int calculateMatrixTriangular() {
        int N = matrix.length;
        for (int i = 0; i < N - 1; i++) {
            int[] keepMax = searchMax(i, N);
            swapRow(keepMax[0], i, N);
            swapColumns(keepMax[1], i, N);
            int result = nulling(i, N);
            if (result == 1) {
                return 1;
            }
        }
        return 0;
    }

    public void showMatrix() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int calculateRang() {
        int N = matrix.length;
        for (int i = 0; i < matrix.length; i++) {
            boolean flag = false;
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] != 0&&!Double.isNaN(matrix[i][j])) {
                    flag = true;
                }
            }
            if (flag == false) {
                N--;
            }
        }
        return N;
    }
}
