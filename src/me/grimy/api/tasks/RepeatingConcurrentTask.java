package me.grimy.api.tasks;

import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import me.grimy.api.tasks.interfaces.Callback;
import me.grimy.api.tasks.interfaces.ConcurrentTask;

public class RepeatingConcurrentTask implements ConcurrentTask<Void> {

    private Callable<Void> callable;

    private ScheduledExecutorService service;
    private int initialDelay;
    private int delay;
    private TimeUnit unit;

    private ScheduledFuture<?> currentTask;

    protected RepeatingConcurrentTask(ScheduledExecutorService service, int initialDelay, int delay, TimeUnit unit) {
        this.service = service;
        this.initialDelay = initialDelay;
        this.delay = delay;
        this.unit = unit;
    }


    @Override
    public ConcurrentTask<Void> submit(Callable<Void> callable) {
        this.callable = callable;
        return this;
    }

    @Override
    public ConcurrentTask<Void> onFinish(Callback<Void> callback) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void begin() {
        this.currentTask = service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    callable.call();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, initialDelay, delay, unit);
    }

    public void cancel() {
        if (currentTask != null) {
            if (!currentTask.isCancelled()) {
                currentTask.cancel(false);
            }
        }
    }
}
