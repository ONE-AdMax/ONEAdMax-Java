package com.oneadmax.global.sample.java.common;

public final class Stopwatch {
    private enum State { READY, RUNNING, STOP }
    private State state = State.READY;
    private long startTime;
    private long stopTime;

    private Stopwatch() { }

    public static Stopwatch createStarted() {
        Stopwatch stopwatch = new Stopwatch();
        stopwatch.start();
        return stopwatch;
    }

    public static Stopwatch createNotStarted() {
        return new Stopwatch();
    }

    public void start() {
        state = State.RUNNING;
        startTime = System.currentTimeMillis();
        stopTime = 0;
    }

    public void stop() {
        if (isRunning()) {
            state = State.STOP;
            stopTime = System.currentTimeMillis();
        }
    }

    public boolean isRunning() {
        return state == State.RUNNING;
    }

    public long elapsed() {
        switch (state) {
            case RUNNING:
                return System.currentTimeMillis() - startTime;
            case STOP:
                return stopTime - startTime;
            default:
                return 0;
        }
    }

    @Override
    public String toString() {
        return String.valueOf(elapsed());
    }
}
