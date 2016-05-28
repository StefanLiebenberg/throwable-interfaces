package org.slieb.throwables;

@FunctionalInterface
@Deprecated
public interface Closure extends Runnable {

    void call();

    default void run() {
        call();
    }
}
