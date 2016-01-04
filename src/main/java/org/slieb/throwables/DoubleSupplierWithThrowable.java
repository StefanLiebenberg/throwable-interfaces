package org.slieb.throwables;

/**
 * Generated from java.util.function.DoubleSupplier
 *
 * @param <E> The extension
 */
@FunctionalInterface
public interface DoubleSupplierWithThrowable<E extends Throwable> extends java.util.function.DoubleSupplier {
    /**
     * @param doublesupplierwiththrowable object
     * @param <E> The extension
     * @return the cast interface
     */
    static <E extends Throwable> DoubleSupplierWithThrowable<E> castDoubleSupplierWithThrowable(DoubleSupplierWithThrowable<E> doublesupplierwiththrowable) {
        return doublesupplierwiththrowable;
    }

    /** 
     * Overridden method of DoubleSupplierWithThrowable that will call getAsDoubleWithThrowable, but catching any exceptions.
     *
     * @return the value
     */
    @Override
    default double getAsDouble() {
        try {
            return getAsDoubleWithThrowable();
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
    double getAsDoubleWithThrowable() throws E;
}
