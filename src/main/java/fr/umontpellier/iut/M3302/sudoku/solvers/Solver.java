package fr.umontpellier.iut.M3302.sudoku.solvers;

import fr.umontpellier.iut.M3302.sudoku.Case;
import fr.umontpellier.iut.M3302.sudoku.checkers.Checker;

public abstract class Solver {
    private final Checker checker;

    /**
     * Default constructor.
     * @param checker is the set of rules of the sudoku.
     */
    public Solver(Checker checker) {
        this.checker = checker;
    }

    /**
     * Solve the Sudoku.
     * @param cases is the gameBoard, initial or not.
     * @return the solved Sudoku.
     */
    public abstract Case[][] solve(Case[][] cases);

    /**
     * Adds a hint to the grid/game board on given case.
     * @param cases is the grid/game board.
     * @param i row.
     * @param j column.
     * @return value of int.
     */
    public abstract int addHint(Case[][] cases, int i, int j);

    /**
     * Getter for the checker.
     * @return the checker.
     */
    public Checker getChecker() {
        return checker;
    }
}
