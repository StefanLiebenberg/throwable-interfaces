package org.slieb.throwables;

/**
 * Generated from java.util.function.IntConsumer
 *
 * @param <E> The extension
 */
@FunctionalInterface
public interface IntConsumerWithThrowable<E extends Throwable> extends java.util.function.IntConsumer {
    /**
     * @param intconsumerwiththrowable object
     * @param <E> The extension
     * @return the cast interface
     */
    static <E extends Throwable> IntConsumerWithThrowable<E> castIntConsumerWithThrowable(IntConsumerWithThrowable<E> intconsumerwiththrowable) {
        return intconsumerwiththrowable;
    }

    /** 
     * Overridden method of IntConsumerWithThrowable that will call acceptWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     */
    @Override
    default void accept(int v1) {
        try {
            acceptWithThrowable(v1);
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
     * @throws E some exception
     */
    void acceptWithThrowable(int v1) throws E;
}
