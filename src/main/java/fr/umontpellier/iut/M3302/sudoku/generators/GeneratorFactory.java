package fr.umontpellier.iut.M3302.sudoku.generators;

import fr.umontpellier.iut.M3302.sudoku.Difficulty;
import fr.umontpellier.iut.M3302.sudoku.checkers.Checker;
import fr.umontpellier.iut.M3302.sudoku.solvers.Solver;

public class GeneratorFactory {
    public static Generator makeGenerator(String type, int value, Difficulty difficulty, Checker checker, Solver solver) {
        if ("rndNonUniGen".equals(type))
            return new RndNonUniGen(value, difficulty, checker, solver);
//        else if ("otherGen".equals(type))
//            return other gen
        else
            return new RndNonUniGen(value, difficulty, checker, solver);
    }
}
