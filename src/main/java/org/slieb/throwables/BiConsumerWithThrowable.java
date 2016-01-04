package org.slieb.throwables;

/**
 * Generated from java.util.function.BiConsumer
 *
 * @param <T> some generic flag
 * @param <U> some generic flag
 * @param <E> The extension
 */
@FunctionalInterface
public interface BiConsumerWithThrowable<T, U, E extends Throwable> extends java.util.function.BiConsumer<T, U> {
    /**
     * @param biconsumerwiththrowable object
     * @param <T> some generic flag
     * @param <U> some generic flag
     * @param <E> The extension
     * @return the cast interface
     */
    static <T, U, E extends Throwable> BiConsumerWithThrowable<T, U, E> castBiConsumerWithThrowable(BiConsumerWithThrowable<T, U, E> biconsumerwiththrowable) {
        return biconsumerwiththrowable;
    }

    /** 
     * Overridden method of BiConsumerWithThrowable that will call acceptWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @param v2 parameter to overridden method
     */
    @Override
    default void accept(T v1, U v2) {
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
    void acceptWithThrowable(T v1, U v2) throws E;
}
