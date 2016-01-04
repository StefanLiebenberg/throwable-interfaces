package org.slieb.throwables;

/**
 * Generated from java.util.function.DoubleConsumer
 *
 * @param <E> The extension
 */
@FunctionalInterface
public interface DoubleConsumerWithThrowable<E extends Throwable> extends java.util.function.DoubleConsumer {
    /**
     * @param doubleconsumerwiththrowable object
     * @param <E> The extension
     * @return the cast interface
     */
    static <E extends Throwable> DoubleConsumerWithThrowable<E> castDoubleConsumerWithThrowable(DoubleConsumerWithThrowable<E> doubleconsumerwiththrowable) {
        return doubleconsumerwiththrowable;
    }

    /** 
     * Overridden method of DoubleConsumerWithThrowable that will call acceptWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     */
    @Override
    default void accept(double v1) {
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
    void acceptWithThrowable(double v1) throws E;
}
