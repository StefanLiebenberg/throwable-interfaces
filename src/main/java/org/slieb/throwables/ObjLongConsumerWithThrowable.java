package org.slieb.throwables;

/**
 * Generated from java.util.function.ObjLongConsumer
 *
 * @param <T> some generic flag
 * @param <E> The extension
 */
@FunctionalInterface
public interface ObjLongConsumerWithThrowable<T, E extends Throwable> extends java.util.function.ObjLongConsumer<T> {
    /**
     * @param objlongconsumerwiththrowable object
     * @param <T> some generic flag
     * @param <E> The extension
     * @return the cast interface
     */
    static <T, E extends Throwable> ObjLongConsumerWithThrowable<T, E> castObjLongConsumerWithThrowable(ObjLongConsumerWithThrowable<T, E> objlongconsumerwiththrowable) {
        return objlongconsumerwiththrowable;
    }

    /** 
     * Overridden method of ObjLongConsumerWithThrowable that will call acceptWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @param v2 parameter to overridden method
     */
    @Override
    default void accept(T v1, long v2) {
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
    void acceptWithThrowable(T v1, long v2) throws E;
}
