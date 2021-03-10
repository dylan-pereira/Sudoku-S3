package fr.umontpellier.iut.M3302.sudoku.generators;

import fr.umontpellier.iut.M3302.sudoku.Case;
import fr.umontpellier.iut.M3302.sudoku.Difficulty;
import fr.umontpellier.iut.M3302.sudoku.checkers.Checker;
import fr.umontpellier.iut.M3302.sudoku.solvers.Solver;

/**
 * Abstract representation of a generator.
 */
public abstract class Generator {
    private final int size;
    private final Difficulty difficulty;
    private final Checker checker;
    private final Solver solver;

    /**
     * Default constructor.
     * @param size of grid, biggest number.
     * @param difficulty of Sudoku.
     * @param checker is the set of rules.
     * @param solver is the solving algorithm.
     */
    public Generator(int size, Difficulty difficulty, Checker checker, Solver solver) {
        this.size = size;
        this.difficulty = difficulty;
        this.checker = checker;
        this.solver = solver;
    }

    /**
     * Generates a new Sudoku.
     * @return the initial game board of said Sudoku.
     */
    public abstract Case[][] generate();

    /**
     * getter for size.
     * @return size of grid/sudoku.
     */
    public int getSize() {
        return size;
    }

    /**
     * Getter for difficulty.
     * @return the difficulty of the Sudoku.
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }

    /**
     * Getter for checker (set of rules).
     * @return the checker.
     */
    public Checker getChecker() {
        return checker;
    }

    /**
     * Getter for solver
     * @return the solving algorithm.
     */
    public Solver getSolver() {
        return solver;
    }
}
