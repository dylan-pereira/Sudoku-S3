package fr.umontpellier.iut.M3302.sudoku.checkers;

import fr.umontpellier.iut.M3302.sudoku.Case;
import fr.umontpellier.iut.M3302.sudoku.Difficulty;

/**
 * Abstract representation of a grid checker.
 */
public abstract class Checker {
    private final int size;
    private final Difficulty difficulty;

    /**
     * Default constructor.
     * @param size of grid.
     * @param difficulty of Sudoku.
     */
    public Checker(int size, Difficulty difficulty) {
        this.size = size;
        this.difficulty = difficulty;
    }

    /**
     * Check via array of cases & flag cases with errors.
     * @param cases array of cases
     * @return true when all good, false when error.
     */
    public abstract boolean checkGrid(Case[][] cases);

    /**
     * Check one case via array of cases & flag with errors.
     * @param cases array of cases
     * @param i row.
     * @param j column.
     * @return true when all good, false when error.
     */
    public abstract boolean checkCase(Case[][] cases, int i, int j);

    /**
     * Removes unwanted errors on number linked to case i,j.
     * @param cases array of cases.
     * @param i row.
     * @param j column.
     */
    public abstract void cleanError(Case[][] cases, int i, int j);

    /**
     * Getter for size.
     * @return size.
     */
    public int getSize() {
        return size;
    }

    /**
     * Getter for difficulty.
     * @return difficulty.
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }
}
