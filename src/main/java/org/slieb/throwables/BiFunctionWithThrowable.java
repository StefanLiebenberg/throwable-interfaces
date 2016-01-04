package org.slieb.throwables;

/**
 * Generated from java.util.function.BiFunction
 *
 * @param <T> some generic flag
 * @param <U> some generic flag
 * @param <R> some generic flag
 * @param <E> The extension
 */
@FunctionalInterface
public interface BiFunctionWithThrowable<T, U, R, E extends Throwable> extends java.util.function.BiFunction<T, U, R> {
    /**
     * @param bifunctionwiththrowable object
     * @param <T> some generic flag
     * @param <U> some generic flag
     * @param <R> some generic flag
     * @param <E> The extension
     * @return the cast interface
     */
    static <T, U, R, E extends Throwable> BiFunctionWithThrowable<T, U, R, E> castBiFunctionWithThrowable(BiFunctionWithThrowable<T, U, R, E> bifunctionwiththrowable) {
        return bifunctionwiththrowable;
    }

    /** 
     * Overridden method of BiFunctionWithThrowable that will call applyWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @param v2 parameter to overridden method
     * @return the value
     */
    @Override
    default R apply(T v1, U v2) {
        try {
            return applyWithThrowable(v1, v2);
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
     * @return the value
     * @throws E some exception
     */
    R applyWithThrowable(T v1, U v2) throws E;
}
