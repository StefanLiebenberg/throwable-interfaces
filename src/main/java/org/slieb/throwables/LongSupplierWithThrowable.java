package org.slieb.throwables;

/**
 * Generated from java.util.function.LongSupplier
 *
 * @param <E> The extension
 */
@FunctionalInterface
public interface LongSupplierWithThrowable<E extends Throwable> extends java.util.function.LongSupplier {
    /**
     * @param longsupplierwiththrowable object
     * @param <E> The extension
     * @return the cast interface
     */
    static <E extends Throwable> LongSupplierWithThrowable<E> castLongSupplierWithThrowable(LongSupplierWithThrowable<E> longsupplierwiththrowable) {
        return longsupplierwiththrowable;
    }

    /** 
     * Overridden method of LongSupplierWithThrowable that will call getAsLongWithThrowable, but catching any exceptions.
     *
     * @return the value
     */
    @Override
    default long getAsLong() {
        try {
            return getAsLongWithThrowable();
        } catch (final RuntimeException | Error exception) {
            throw exception;
        } catch (final Throwable throwable) {
            throw new org.slieb.throwables.SuppressedException(throwable);
        }
    }

    /** 
     * Functional method that will throw exceptions.
     *
     * @return the value
     * @throws E some exception
     */
    long getAsLongWithThrowable() throws E;
}
