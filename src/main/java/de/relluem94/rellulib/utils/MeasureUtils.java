package de.relluem94.rellulib.utils;

public class MeasureUtils {

    private long startTime;
    private long endTime;
    private long completeTime;

    /**
     * Starts Timer
     */
    public void startTimer() {
        startTime = System.currentTimeMillis();
    }

    /*
	 * Stops Timer (can be called multiple Times)
     */
    public void stopTimer() {
        endTime = System.currentTimeMillis();
        completeTime = completeTime + (endTime - startTime);
    }

    /**
     *
     * @return Duration (long)
     */
    public long getDuration() {
        return (endTime - startTime);
    }

    /**
     *
     * @return Complete Duration (long)
     */
    public long getCompleteDuration() {
        return completeTime;
    }
}
