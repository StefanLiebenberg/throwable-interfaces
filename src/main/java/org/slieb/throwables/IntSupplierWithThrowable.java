package org.slieb.throwables;


import java.util.function.IntSupplier;

@FunctionalInterface
public interface IntSupplierWithThrowable<E extends Throwable> extends IntSupplier {

    @Override
    default int getAsInt() {
        try {
            return getAsIntWithThrowable();
        } catch (final RuntimeException | Error exception) {
            throw exception;
        } catch (final Throwable throwable) {
            throw new SuppressedException(throwable);
        }
    }

    int getAsIntWithThrowable() throws E;

    static <E extends Throwable> IntSupplierWithThrowable<E> castIntSupplierWithThrowable(final IntSupplierWithThrowable<E> predicate) {
        return predicate;
    }
}
