package org.slieb.throwables;

/**
 * Generated from java.util.function.ObjDoubleConsumer
 *
 * @param <T> some generic flag
 * @param <E> The extension
 */
@FunctionalInterface
public interface ObjDoubleConsumerWithThrowable<T, E extends Throwable> extends java.util.function.ObjDoubleConsumer<T> {
    /**
     * @param objdoubleconsumerwiththrowable object
     * @param <T> some generic flag
     * @param <E> The extension
     * @return the cast interface
     */
    static <T, E extends Throwable> ObjDoubleConsumerWithThrowable<T, E> castObjDoubleConsumerWithThrowable(ObjDoubleConsumerWithThrowable<T, E> objdoubleconsumerwiththrowable) {
        return objdoubleconsumerwiththrowable;
    }

    /** 
     * Overridden method of ObjDoubleConsumerWithThrowable that will call acceptWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @param v2 parameter to overridden method
     */
    @Override
    default void accept(T v1, double v2) {
        try {
            acceptWithThrowable(v1, v2);
        } catch (final RuntimeException | Error exception) {
            throw exception;
        } catch (final Throwable throwable) {
            throw new org.slieb.throwables.SuppressedException(throwable);
        }
    }

    /** 
     * Functional method that will throw exceptions.
     *
     * @param v1 parameter to overridden method
     * @param v2 parameter to overridden method
     * @throws E some exception
     */
    void acceptWithThrowable(T v1, double v2) throws E;
}
