package de.relluem94.rellulib.test;

import de.relluem94.rellulib.threads.ThreadMaster;
import de.relluem94.rellulib.threads.ThreadWorker;
import de.relluem94.rellulib.utils.LogUtils;

public class TestClass4 {

    public static void main(String[] args) {
        threadTest();
    }

    public static void threadTest() {
        ThreadMaster tm = new ThreadMaster();
        tm.startTread(tm.addThread(new ThreadWorker() {
            @Override
            public void run() {
                sleep(2000);
                LogUtils.info("Test22");
            }
        }));
        tm.startTread(tm.addThread(new ThreadWorker() {
            @Override
            public void run() {
                sleep(3000);
                LogUtils.info("Test44");
            }
        }));
        tm.startTread(tm.addThread(new ThreadWorker() {
            @Override
            public void run() {
                sleep(4000);
                LogUtils.info("Test66");
            }
        }));

        tm.startTread( // USE ID FROM addThread Method
                tm.addThread( // USE CLASS ThreadWorker
                        new ThreadWorker() { // Override internal Method run
                    @Override
                    public void run() {
                        sleep(5000); // ThreadWorker extends ThreadSleeper Class for easy sleep handling 
                        LogUtils.info("Test88"); // Actual Code that is going to be executed 
                    }
                }
                )
        );
    }

}
