package org.slieb.throwables;

import java.io.Serializable;
import java.util.function.Supplier;

@FunctionalInterface
public interface SupplierWithThrowable<T, E extends Throwable> extends Supplier<T>, Serializable {

    @Override
    default T get() {
        try {
            return getWithThrowable();
        } catch (final RuntimeException | Error e) {
            throw e;
        } catch (final Throwable e) {
            throw new SuppressedException(e);
        }
    }

    T getWithThrowable() throws E;

    static <A, E extends Throwable> SupplierWithThrowable<A, E> castSupplierWithThrowable(final SupplierWithThrowable<A, E> function) {
        return function;
    }
}
