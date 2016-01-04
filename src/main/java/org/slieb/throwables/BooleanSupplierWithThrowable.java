package org.slieb.throwables;

/**
 * Generated from java.util.function.BooleanSupplier
 *
 * @param <E> The extension
 */
@FunctionalInterface
public interface BooleanSupplierWithThrowable<E extends Throwable> extends java.util.function.BooleanSupplier {
    /**
     * @param booleansupplierwiththrowable object
     * @param <E> The extension
     * @return the cast interface
     */
    static <E extends Throwable> BooleanSupplierWithThrowable<E> castBooleanSupplierWithThrowable(BooleanSupplierWithThrowable<E> booleansupplierwiththrowable) {
        return booleansupplierwiththrowable;
    }

    /** 
     * Overridden method of BooleanSupplierWithThrowable that will call getAsBooleanWithThrowable, but catching any exceptions.
     *
     * @return the value
     */
    @Override
    default boolean getAsBoolean() {
        try {
            return getAsBooleanWithThrowable();
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
    boolean getAsBooleanWithThrowable() throws E;
}
