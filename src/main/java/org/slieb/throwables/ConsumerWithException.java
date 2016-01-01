package org.slieb.throwables;


import java.io.Serializable;
import java.util.function.Consumer;

@FunctionalInterface
public interface ConsumerWithException<T, E extends Exception> extends Consumer<T>, Serializable {

    @Override
    default void accept(final T elem) {
        try {
            acceptWithException(elem);
        } catch (final RuntimeException e) {
            throw e;
        } catch (final Throwable e) {
            throw new RuntimeException(e);
        }
    }

    void acceptWithException(T elem) throws E;

    static <A, E extends Exception> ConsumerWithException<A, E> castConsumerWithException(final ConsumerWithException<A, E> function) {
        return function;
    }

}
