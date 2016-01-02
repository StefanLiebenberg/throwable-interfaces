package org.slieb.throwables;

import java.io.Serializable;
import java.util.function.BiFunction;

/**
 * Represents a BiFunction that also throws an Exception.
 *
 * @param <T> The type of first parameter
 * @param <U> The type of second parameter
 * @param <F> The return result type.
 * @param <E> The throwable type.
 */
@FunctionalInterface
public interface BiFunctionWithThrowable<T, U, F, E extends Throwable> extends BiFunction<T, U, F>, Serializable {

    static <A, B, C, E extends Throwable> BiFunctionWithThrowable<A, B, C, E> castBiFunctionWithThrowable(
            final BiFunctionWithThrowable<A, B, C, E> function) {
        return function;
    }

    @Override
    default F apply(final T first, final U second) {
        try {
            return applyWithThrowable(first, second);
        } catch (final RuntimeException | Error exception) {
            throw exception;
        } catch (final Throwable throwable) {
            throw new SuppressedException(throwable);
        }
    }

    F applyWithThrowable(final T first, final U second) throws E;
}
