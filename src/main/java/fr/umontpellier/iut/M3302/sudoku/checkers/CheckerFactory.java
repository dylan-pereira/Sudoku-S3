package fr.umontpellier.iut.M3302.sudoku.checkers;

import fr.umontpellier.iut.M3302.sudoku.Difficulty;

public class CheckerFactory {

    public static Checker makeChecker(String type, int value, Difficulty difficultyValue) {
        if ("classic".equals(type))
            return new ClassicChecker(value, difficultyValue);
//        else if ("otherChecker".equals(type))
//            return other checker
        else
            return new ClassicChecker(value, difficultyValue);
    }
}
