package org.slieb.throwables;

import java.io.Serializable;
import java.util.function.Supplier;

@FunctionalInterface
public interface SupplierWithException<T, E extends Exception> extends Supplier<T>, Serializable {

    @Override
    default T get() {
        try {
            return getWithException();
        } catch (final RuntimeException e) {
            throw e;
        } catch (final Throwable e) {
            throw new RuntimeException(e);
        }
    }

    T getWithException() throws E;

    static <A, E extends Exception> SupplierWithException<A, E> castSupplierWithException(final SupplierWithException<A, E> function) {
        return function;
    }
}
