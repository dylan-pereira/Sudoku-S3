package fr.umontpellier.iut.M3302.sudoku.checkers;

import fr.umontpellier.iut.M3302.sudoku.Case;
import fr.umontpellier.iut.M3302.sudoku.Difficulty;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Checker representing the basic set of rules of a Sudoku.
 */
class ClassicChecker extends Checker {
    /**
     * Default constructor.
     *
     * @param size of grid.
     */
    public ClassicChecker(int size, Difficulty difficulty) {
        super(size, difficulty);
    }

    @Override
    public boolean checkGrid(Case[][] cases) {
        boolean correct = true;
        for (int i = 0; i < getSize() && correct; i++) {
            for (int j = 0; j < getSize() && correct; j++)
                correct = verifAlreadyInRow(cases, i, j) && verifAlreadyInColumn(cases, i, j) &&
                        verifAlreadyInBlock(cases, i, j);
        }
        return correct;
    }

    @Override
    public boolean checkCase(Case[][] cases, int i, int j) {
        boolean correctRow = verifAlreadyInRow(cases, i, j);
        boolean correctColumn = verifAlreadyInColumn(cases, i, j);
        boolean correctBlock = verifAlreadyInBlock(cases, i, j);
        boolean correct = correctBlock && correctColumn && correctRow;
        if (getDifficulty() != Difficulty.HARD)
            cases[i][j].setError(!correct);
        return correct;
    }

    @Override
    public void cleanError(Case[][] cases, int i, int j) {
        for (int k = 0; k < getSize(); k++) {
            if (cases[k][j].getValue() == cases[i][j].getValue())
                cases[k][j].setError(false);
        }
        for (int k = 0; k < getSize(); k++) {
            if (cases[i][k].getValue() == cases[i][j].getValue())
                cases[i][k].setError(false);
        }
        for (Case c : getCaseInBlock(cases, i, j)) {
            if (c.getValue() == cases[i][j].getValue())
                c.setError(false);
        }

    }

    /**
     * Checks if value in given case is present in other cases in row.
     * @param cases is the grid of cases.
     * @param i row.
     * @param j column.
     * @return true if not present, false if found 1 or more.
     */
    private boolean verifAlreadyInRow(Case[][] cases, int i, int j) {
        boolean correct = true;
        for (int k = 0; k < getSize(); k++) {
            if (k != j) {
                if (cases[i][k].getValue() == cases[i][j].getValue()) {
                    if (getDifficulty() == Difficulty.EASY)
                        cases[i][k].setError(true);
                    correct = false;
                }
            }
        }
        return correct;
    }

    /**
     * Checks if value in given case is present in other cases in column.
     * @param cases is the grid of cases.
     * @param i row.
     * @param j column.
     * @return true if not present, false if found 1 or more.
     */
    private boolean verifAlreadyInColumn(Case[][] cases, int i, int j) {
        boolean correct = true;
        for (int k = 0; k < getSize(); k++) {
            if (k != i) {
                if (cases[k][j].getValue() == cases[i][j].getValue()) {
                    if (getDifficulty() == Difficulty.EASY)
                        cases[k][j].setError(true);
                    correct = false;
                }
            }
        }
        return correct;
    }

    /**
     * Checks if value in given case is present in other cases in block.
     * @param cases is the grid of cases.
     * @param i row.
     * @param j column.
     * @return true if not present, false if found 1 or more.
     */
    private boolean verifAlreadyInBlock(Case[][] cases, int i, int j) {
        ArrayList<Case> list = getCaseInBlock(cases, i, j);
        boolean correct = true;
        for (Case c : list)
            if (c != cases[i][j]) {
                if (c.getValue() == cases[i][j].getValue()) {
                    if (getDifficulty() == Difficulty.EASY)
                        c.setError(true);
                    correct = false;
                }
            }
        return correct;
    }

    /**
     * Finds all the cases in the same block of given case.
     * @param cases grid of cases.
     * @param i row.
     * @param j column.
     * @return an ArrayList of cases.
     */
    private ArrayList<Case> getCaseInBlock(Case[][] cases, int i, int j) {
        ArrayList<Case> liste = new ArrayList<>();
        int sqrtTaille = (int) Math.sqrt(getSize());
        int lig = (i / sqrtTaille) * sqrtTaille;
        int col = (j / sqrtTaille) * sqrtTaille;

        for (int k = lig; k < lig + sqrtTaille; k++) {
            liste.addAll(Arrays.asList(cases[k]).subList(col, col + sqrtTaille));
        }
        return liste;
    }
}