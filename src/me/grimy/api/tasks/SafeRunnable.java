package me.grimy.api.tasks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class SafeRunnable implements Runnable {
    private Lock lock = new ReentrantLock();

    public SafeRunnable() {
    }


    public abstract void safeRun();

    @Override
    public void run() {
        lock.lock();

        try {
            this.safeRun();
        } finally {
            lock.unlock();
        }
    }
}