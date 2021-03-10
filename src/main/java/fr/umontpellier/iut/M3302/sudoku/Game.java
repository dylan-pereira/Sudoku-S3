package fr.umontpellier.iut.M3302.sudoku;

import fr.umontpellier.iut.M3302.sudoku.checkers.Checker;
import fr.umontpellier.iut.M3302.sudoku.generators.Generator;
import fr.umontpellier.iut.M3302.sudoku.solvers.Solver;
import fr.umontpellier.iut.M3302.sudoku.solvers.SolverHelper;

public class Game {
    private final int size;
    private final Generator generator;
    private final Checker checker;
    private final Solver solver;
    private final StopWatch stopWatch;
    private Case[][] cases;
    private boolean solved = false;
    private boolean validated = false;

    public Game(int size, Generator generator, Checker checker, Solver solver) {
        this.size = size;
        this.generator = generator;
        this.checker = checker;
        this.solver = solver;
        cases = this.generator.generate();
        stopWatch = new StopWatch();
        stopWatch.start();
    }

    public Case getCase(int i, int j) {
        return cases[i][j];
    }


    public void setValue(int value, int i, int j) {
        checker.cleanError(cases, i, j);
        cases[i][j].setError(false);
        if (cases[i][j].getValue() * 10 < size && cases[i][j].getValue() != 0) {
            int tempNb = cases[i][j].getValue() * 10 + value;
            if (tempNb <= size) {
                cases[i][j].setValue(tempNb);
                checker.checkCase(cases, i, j);
            } else {
                if (value > 0 && value <= size) {
                    cases[i][j].setValue(value);
                    checker.checkCase(cases, i, j);
                } else {
                    cases[i][j].setValue(0);
                    cases[i][j].setError(false);
                }
            }
        } else {
            if (value > 0 && value <= size) {
                cases[i][j].setValue(value);
                checker.checkCase(cases, i, j);
            } else {
                cases[i][j].setValue(0);
                cases[i][j].setError(false);
            }
        }
    }

    public void eraseValue(int i, int j) {
        checker.cleanError(cases, i, j);
        cases[i][j].setValue(0);
        cases[i][j].setError(false);
    }

    public boolean hasValue(int i, int j) {
        return cases[i][j].getValue() != 0;
    }

    public void newGame() {
        validated = false;
        solved = false;
        cases = this.generator.generate();
        stopWatch.reset();
    }

    public void restart() {
        validated = false;
        solved = false;
        for (Case[] c : cases) {
            for (Case c1 : c) {
                if (!c1.isInitial())
                    c1.setValue(0);
                c1.setError(false);
                c1.setHint(false);
                c1.setAlgoSolved(false);
            }
        }
    }

    public void solve() {
        cases = solver.solve(cases);
        solved = true;
    }

    public void addIndice(int i, int j) throws Throwable {
        if (checker.getDifficulty() == Difficulty.EASY) {
            int[] coord = SolverHelper.crossSimplestRowColumn(cases);
            cases[coord[0]][coord[1]].setValue(solver.addHint(cases, coord[0], coord[1]));
            cases[coord[0]][coord[1]].setHint(true);
        } else if (checker.getDifficulty() == Difficulty.NORMAL) {
            cases[i][j].setValue(solver.addHint(cases, i, j));
            cases[i][j].setHint(true);
        } else {
            cases[i][j].clearNotes();
            for (int k = 1; k <= getSize(); k++) {
                cases[i][j].setValue(k);
                if (checker.checkCase(cases, i, j))
                    cases[i][j].toggleNote(k);
            }
            cases[i][j].setValue(0);
        }
    }

    public int getSize() {
        return size;
    }

    public boolean isNotSolved() {
        return !solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public boolean validate() {
        validated = SolverHelper.isFilled(cases) && checker.checkGrid(cases);
        return validated;
    }

    public void toggleNote(int value, int i, int j) throws Exception {
        int tmpValue = 10 + value;
        if (cases[i][j].getNotes()[0] && tmpValue <= size)
            cases[i][j].toggleNote(tmpValue);
        else
            cases[i][j].toggleNote(value);
    }

    public void play() {
        stopWatch.start();
    }

    public void resume() {
        stopWatch.resume();
    }

    public void pause() {
        stopWatch.pause();
    }

    public void stop() {
        stopWatch.stop();
    }

    public StopWatch getStopWatch() {
        return stopWatch;
    }

    public boolean isPaused() {
        return !stopWatch.isRunning();
    }
}
