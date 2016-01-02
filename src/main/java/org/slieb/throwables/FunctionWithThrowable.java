package org.slieb.throwables;


import java.io.Serializable;
import java.util.function.Function;

@FunctionalInterface
public interface FunctionWithThrowable<A, B, E extends Throwable> extends Function<A, B>, Serializable {

    @Override
    default B apply(final A value) {
        try {
            return applyWithThrowable(value);
        } catch (final RuntimeException | Error e) {
            throw e;
        } catch (final Throwable e) {
            throw new SuppressedException(e);
        }
    }

    B applyWithThrowable(A value) throws E;

    static <A, B, E extends Throwable> FunctionWithThrowable<A, B, E> castFunctionWithThrowable(
            final FunctionWithThrowable<A, B, E> function) {
        return function;
    }
}
