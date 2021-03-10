package fr.umontpellier.iut.M3302.sudoku.solvers;

import fr.umontpellier.iut.M3302.sudoku.Case;
import fr.umontpellier.iut.M3302.sudoku.checkers.Checker;

/**
 * Back tracking solving algorithm.
 */
class BackTrackingSolver extends Solver {
    private Case[][] solution;

    /**
     * Default constructor.
     * @param checker is a set of rules.
     */
    public BackTrackingSolver(Checker checker) {
        super(checker);
    }

    @Override
    public Case[][] solve(Case[][] cases) {
        solution = new Case[cases.length][cases.length];
        for (int i = 0; i < cases.length; i++) {
            for (int j = 0; j < cases[i].length; j++) {
                solution[i][j] = new Case(cases[i][j]);
            }
        }
        solve(SolverHelper.simplestRow(cases), 0);
        for (int i = 0; i < getChecker().getSize(); i++) {
            for (int j = 0; j < getChecker().getSize(); j++) {
                solution[i][j].setError(false);
            }
        }
        return solution;
    }

    @Override
    public int addHint(Case[][] cases, int i, int j) {
        return solve(cases)[i][j].getValue();
    }

    private boolean solve(int l, int c) {
        if (SolverHelper.isFilled(solution)) {
            return true;
        }
        if (c == solution.length) {
            return solve(SolverHelper.simplestRow(solution), 0);
        }
        if (solution[l][c].isInitial())
            return solve(l, c + 1);

        for (int i = 1; i <= solution.length; i++) {
//            System.out.println(Arrays.deepToString(solution));
            solution[l][c].setValue(i);
            if (getChecker().checkCase(solution, l, c)) {
                if (solve(l, c + 1)) {
                    return true;
                }
            } else {
                solution[l][c].setValue(0);
            }
            solution[l][c].setValue(0);
        }
        return false;
    }
}
