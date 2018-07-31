package me.grimy.api.tasks;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import me.grimy.api.tasks.interfaces.ConcurrentTask;

public class Concurrency {

    private static ScheduledExecutorService service = Executors.newScheduledThreadPool(100);
    private static Concurrency instance;
    static { instance = new Concurrency(); }

    public static <V> ConcurrentTask<V> newTask() {
        return instance.createNewTask();
    }

    public static ConcurrentTask<Void> newRepeatingTask(int beginDelay, int period, TimeUnit unit) {
        return instance.createNewRepeatingTask(beginDelay, period, unit);
    }

    private <V> ConcurrentTask<V> createNewTask() {
        return new BasicConcurrentTask<>(service);
    }

    private ConcurrentTask<Void> createNewRepeatingTask(int beginDelay, int period, TimeUnit unit) {
        return new RepeatingConcurrentTask(service, beginDelay, period, unit);
    }

}
