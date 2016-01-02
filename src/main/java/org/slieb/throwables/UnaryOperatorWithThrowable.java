package org.slieb.throwables;


import java.util.function.UnaryOperator;

@FunctionalInterface
public interface UnaryOperatorWithThrowable<T, E extends Throwable> extends UnaryOperator<T> {
    
    @Override
    default T apply(final T value) {
        try {
            return applyWithThrowable(value);
        } catch (final RuntimeException | Error e) {
            throw e;
        } catch (final Throwable e) {
            throw new SuppressedException(e);
        }
    }

    T applyWithThrowable(T value) throws E;

    static <A, E extends Throwable> UnaryOperatorWithThrowable<A, E> castUnaryOperatorWithThrowable(
            final UnaryOperatorWithThrowable<A, E> function) {
        return function;
    }

}
