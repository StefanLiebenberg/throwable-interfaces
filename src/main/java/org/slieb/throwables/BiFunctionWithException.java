package org.slieb.throwables;

import java.io.Serializable;
import java.util.function.BiFunction;

@FunctionalInterface
public interface BiFunctionWithException<T, U, F, E extends Exception> extends BiFunction<T, U, F>, Serializable {

    @Override
    default F apply(final T elem, U element) {
        try {
            return applyWithException(elem, element);
        } catch (final RuntimeException e) {
            throw e;
        } catch (final Throwable e) {
            throw new RuntimeException(e);
        }
    }

    F applyWithException(T elem, U element) throws E;

    static <A, B, C, E extends Exception> BiFunctionWithException<A, B, C, E> castBiFunctionWithException(
            final BiFunctionWithException<A, B, C, E> function) {
        return function;
    }
}
