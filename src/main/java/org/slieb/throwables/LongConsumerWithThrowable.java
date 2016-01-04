package org.slieb.throwables;

/**
 * Generated from java.util.function.LongConsumer
 *
 * @param <E> The extension
 */
@FunctionalInterface
public interface LongConsumerWithThrowable<E extends Throwable> extends java.util.function.LongConsumer {
    /**
     * @param longconsumerwiththrowable object
     * @param <E> The extension
     * @return the cast interface
     */
    static <E extends Throwable> LongConsumerWithThrowable<E> castLongConsumerWithThrowable(LongConsumerWithThrowable<E> longconsumerwiththrowable) {
        return longconsumerwiththrowable;
    }

    /** 
     * Overridden method of LongConsumerWithThrowable that will call acceptWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     */
    @Override
    default void accept(long v1) {
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
    void acceptWithThrowable(long v1) throws E;
}
