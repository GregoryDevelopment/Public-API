package me.grimy.api.tasks;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

import me.grimy.api.tasks.interfaces.Callback;
import me.grimy.api.tasks.interfaces.ConcurrentTask;

public class BasicConcurrentTask<V> implements ConcurrentTask<V> {

    private Callable<V> callable;
    private Callback<V> callback;

    private ExecutorService executor;

    protected BasicConcurrentTask(ExecutorService executor) {
        this.executor = executor;
    }

    @Override
    public ConcurrentTask<V> submit(Callable<V> callable) {
        this.callable = callable;
        return this;
    }

    @Override
    public ConcurrentTask<V> onFinish(Callback<V> callback) {
        this.callback = callback;
        return this;
    }

    @Override
    public void begin() {
        executor.submit(() -> {
            try {
                V result = callable.call();

                if (callback != null) {
                    callback.onComplete(result, null);
                }

            } catch (Exception e) {
                if (callback != null) {
                    callback.onComplete(null, e);
                }
            }
        });
    }


}
