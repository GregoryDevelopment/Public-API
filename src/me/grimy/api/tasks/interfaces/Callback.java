package me.grimy.api.tasks.interfaces;

/**
 * @param <T> The type
 */
public interface Callback<T> {

    void onComplete(T result, Throwable throwable);

}
