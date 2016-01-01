package org.slieb.throwables;


import java.io.Serializable;
import java.util.function.Function;

@FunctionalInterface
public interface FunctionWithException<A, B, E extends Exception> extends Function<A, B>, Serializable {

    @Override
    default B apply(final A elem) {
        try {
            return applyWithException(elem);
        } catch (final RuntimeException e) {
            throw e;
        } catch (final Throwable e) {
            throw new RuntimeException(e);
        }
    }

    B applyWithException(A elem) throws E;

    static <A, B, E extends Exception> FunctionWithException<A, B, E> castFunctionWithException(
            final FunctionWithException<A, B, E> function) {
        return function;
    }
}
