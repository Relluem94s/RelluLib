package de.relluem94.rellulib.threads;

import de.relluem94.rellulib.utils.LogUtils;

public class ThreadSleeper {

    public void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            LogUtils.error(e.getMessage());
        }
    }
}
