package de.relluem94.rellulib.threads;

import de.relluem94.rellulib.utils.LogUtils;

public class ThreadWorker extends ThreadSleeper implements Runnable {

    private Thread worker;

    public ThreadWorker(int... priority) {
        worker = new Thread(this);
        if (priority.length != 0) {
            worker.setPriority(priority[0]);
        } else {
            worker.setPriority(Thread.NORM_PRIORITY);
        }
    }

    @Override
    public void run() {
        LogUtils.warning("Please Override this Methode!");
    }

    public void start() {
        LogUtils.info("[" + worker.getName() + "] started with priority " + worker.getPriority());
        worker.start();
    }

    public Thread getThread() {
        return worker;
    }

}
