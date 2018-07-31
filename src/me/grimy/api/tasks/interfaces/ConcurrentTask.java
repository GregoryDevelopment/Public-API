package me.grimy.api.tasks.interfaces;

import java.util.concurrent.Callable;

/**
 *
 * @param <V> The values type
 */
public interface ConcurrentTask<V> {

    ConcurrentTask<V> submit(Callable<V> callable);

    ConcurrentTask<V> onFinish(Callback<V> callback);

    void begin();


}
