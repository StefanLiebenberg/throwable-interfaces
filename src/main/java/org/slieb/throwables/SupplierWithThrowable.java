package org.slieb.throwables;

/**
 * Generated from java.util.function.Supplier
 * Extends java.util.function.Supplier to allow for a checked exception.
 *
 * @param <T> some generic flag
 * @param <E> The extension
 */
@FunctionalInterface
public interface SupplierWithThrowable<T, E extends Throwable> extends java.util.function.Supplier<T> {
    /**
     * Utility method to mark lambdas of type SupplierWithThrowable
     * @param supplierwiththrowable The interface instance
     * @param <T> Generic that corresponds to the same generic on Supplier  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, E extends Throwable> SupplierWithThrowable<T, E> castSupplierWithThrowable(SupplierWithThrowable<T, E> supplierwiththrowable) {
        return supplierwiththrowable;
    }

    /** 
     * Overridden method of SupplierWithThrowable that will call getWithThrowable, but catching any exceptions.
     *
     * @return the value
     */
    @Override
    default T get() {
        try {
            return getWithThrowable();
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
    T getWithThrowable() throws E;
}
