package fr.umontpellier.iut.M3302.sudoku;

public class StopWatch {
    private long startTime;
    private long stopTime;
    private boolean running;
    private long elapsedTimeMillis;

    public StopWatch() {
        startTime = 0;
        this.stopTime = 0;
        running = false;
        elapsedTimeMillis = 0;
    }

    public void start() {
        startTime = System.nanoTime();
        running = true;
    }

    public void stop() {
        stopTime = System.nanoTime();
        elapsedTimeMillis = (stopTime - startTime)/1000000;
        running = false;
    }

    public void pause() {
        stop();
    }

    public void resume() {
        startTime = System.nanoTime() - elapsedTimeMillis*1000000;
        running = true;
    }

    public long getElapsedTimeMillis() {
        return running? elapsedTimeMillis = (System.nanoTime() - startTime)/1000000 : elapsedTimeMillis;
    }

    public long getElapsedTimeSecs() {
        return getElapsedTimeMillis()/1000;
    }

    @Override
    public String toString() {
        return (int)getElapsedTimeSecs()/3600 + " h " + (int)(getElapsedTimeSecs()%3600)/60 + " m " + (int)(getElapsedTimeSecs()%3600)%60 + " s";
    }

    public void reset() {
        start();
    }

    public boolean isRunning() {
        return running;
    }
}
