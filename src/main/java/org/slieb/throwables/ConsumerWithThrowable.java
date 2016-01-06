package org.slieb.throwables;

/**
 * Generated from java.util.function.Consumer
 * Extends java.util.function.Consumer to allow for a checked exception.
 *
 * @param <T> some generic flag
 * @param <E> The extension
 */
@FunctionalInterface
public interface ConsumerWithThrowable<T, E extends Throwable> extends java.util.function.Consumer<T> {
    /**
     * Utility method to mark lambdas of type ConsumerWithThrowable
     * @param consumerwiththrowable The interface instance
     * @param <T> Generic that corresponds to the same generic on Consumer  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, E extends Throwable> ConsumerWithThrowable<T, E> castConsumerWithThrowable(ConsumerWithThrowable<T, E> consumerwiththrowable) {
        return consumerwiththrowable;
    }

    /** 
     * Overridden method of ConsumerWithThrowable that will call acceptWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     */
    @Override
    default void accept(T v1) {
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
    void acceptWithThrowable(T v1) throws E;
}
