package fr.umontpellier.iut.M3302.sudoku.solvers;

import fr.umontpellier.iut.M3302.sudoku.checkers.Checker;

public class SolverFactory {
    public static Solver makeSolver(String type, Checker checker) {
        if ("backTackingSolver".equals(type))
            return new BackTrackingSolver(checker);
//        else if ("otherSolver".equals(type))
//            return other solver
        else
            return new BackTrackingSolver(checker);
    }
}
