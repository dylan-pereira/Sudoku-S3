package fr.umontpellier.iut.M3302.sudoku.solvers;

import fr.umontpellier.iut.M3302.sudoku.Case;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Extra methods used mostly by solver to find:
 * - simplest row to solve (least amount of blank).
 * - simplest column to solve (least amount of blank).
 * - simplest block to solve (least amount of blank).
 * - cross between simplest row and column (used for hints).
 * - if the grid is filled with non null/0 values.
 */
public class SolverHelper {

    /**
     * Finds the block with the least amount of blank cases.
     * @param cases grid of cases/gameBoard.
     * @return the number of the block counting from top left.
     */
    public static int simplestBlock(Case[][] cases) {
        int min = 1000;
        int fin = -1;
        int sqrtTaille = (int) Math.sqrt(cases.length);
        int[][] sumPerBlock = new int[sqrtTaille][sqrtTaille];

        for (int l = 0; l < cases.length; l++) {
            for (int c = 0; c < cases.length; c++) {
                if (cases[l][c].getValue() == 0)
                    sumPerBlock[l / sqrtTaille][c / sqrtTaille]++;
            }
        }
        for (int i = 0; i < sqrtTaille; i++) {
            for (int j = 0; j < sqrtTaille; j++) {
                if (min > sumPerBlock[i][j]) {
                    min = sumPerBlock[i][j];
                    fin = i * 3 + j;
                }
            }
        }

        return fin;
    }

    /**
     * Finds the coordinates of the value in the simplest row & column.
     * @param cases grid of cases/gameBoard.
     * @return and int[2] with the coordinates as int[0]==i & int[1]==j.
     * @throws Throwable when grid is full.
     */
    public static int[] crossSimplestRowColumn(Case[][] cases) throws Throwable {
        if (!SolverHelper.isFilled(cases)) {
            int[] coord = new int[2];
            int l = simplestRow(cases, new int[cases.length]);
            ArrayList<Integer> integers = new ArrayList<>();
            for (int i = 0; i < cases.length; i++) {
                if (cases[l][i].getValue() == 0)
                    integers.add(i);
            }
            int[] ints = new int[integers.size()];
            for (int i = 0; i < integers.size(); i++) {
                ints[i] = integers.get(i);
            }
            coord[0] = l;
            coord[1] = simplestColumn(cases, ints);
            return coord;
        } else {
            throw new Throwable("Already filled");
        }
    }

    /**
     * Check if the grid as any blank space left.
     * @param cases grid of cases.
     * @return true if grid as No empty cases, otherwise false.
     */
    public static boolean isFilled(Case[][] cases) {
        boolean bool = true;
        for (int i = 0; i < cases.length && bool; i++) {
            for (int j = 0; j < cases.length && bool; j++) {
                bool = cases[i][j].getValue() != 0;
            }
        }
        return bool;
    }

    /**
     * Finds the simplest row, meaning the row with the least amount of blank.
     * @param cases grid of case/gameBoard.
     * @param rowIndices the indices of the rows to check. Empty will result in all rows being checked.
     * @return the indice of row.
     */
    public static int simplestRow(Case[][] cases, int[] rowIndices) {
        int end = -1;
        int min = 1000;

        if (Arrays.equals(rowIndices, new int[rowIndices.length])) {
            rowIndices = new int[rowIndices.length];
            for (int i = 0; i < rowIndices.length; i++) {
                rowIndices[i] = i;
            }

        }

        for (int l : rowIndices) {
            int temp = 0;
            for (int c = 0; c < cases.length; c++) {
                if (cases[l][c].getValue() == 0)
                    temp++;
            }
            if (temp != 0)
                if (temp < min) {
                    min = temp;
                    end = l;
                }
        }
        return end;
    }


    /**
     * Finds the simplest column, meaning the column with the least amount of blank.
     * @param cases grid of case/gameBoard.
     * @param colIndices the indices of the columns to check. Empty will result in all columns being checked.
     * @return the indice of column.
     */
    public static int simplestColumn(Case[][] cases, int[] colIndices) {
        int fin = -1;
        int min = 1000;
        for (int c : colIndices) {
            int temp = 0;
            for (Case[] aCase : cases) {
                if (aCase[c].getValue() == 0)
                    temp++;
            }
            if (temp != 0)
                if (temp < min) {
                    min = temp;
                    fin = c;
                }
        }
        return fin;
    }

    /**
     * Finds the simplest row, meaning the row with the least amount of blank. (checks every rows of grid)
     * @param cases grid of case / gameBoard
     * @return the indice of row.
     */
    protected static int simplestRow(Case[][] cases) {
        int end = -1;
        int min = 1000;

        for (int l = 0; l < cases.length; l++) {
            int temp = 0;
            for (int c = 0; c < cases.length; c++) {
                if (cases[l][c].getValue() == 0)
                    temp++;
            }
            if (temp != 0)
                if (temp < min) {
                    min = temp;
                    end = l;
                }
        }
        return end;
    }
}
