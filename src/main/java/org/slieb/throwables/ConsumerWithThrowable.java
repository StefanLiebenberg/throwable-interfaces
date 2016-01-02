package org.slieb.throwables;


import java.io.Serializable;
import java.util.function.Consumer;

@FunctionalInterface
public interface ConsumerWithThrowable<T, E extends Throwable> extends Consumer<T>, Serializable {

    @Override
    default void accept(final T value) {
        try {
            acceptWithThrowable(value);
        } catch (final RuntimeException | Error e) {
            throw e;
        } catch (final Throwable e) {
            throw new SuppressedException(e);
        }
    }

    void acceptWithThrowable(T value) throws E;

    static <A, E extends Throwable> ConsumerWithThrowable<A, E> castConsumerWithThrowable(final ConsumerWithThrowable<A, E> function) {
        return function;
    }

}
