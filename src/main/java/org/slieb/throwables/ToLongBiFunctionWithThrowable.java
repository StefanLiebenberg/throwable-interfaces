package org.slieb.throwables;

/**
 * Generated from java.util.function.ToLongBiFunction
 *
 * @param <T> some generic flag
 * @param <U> some generic flag
 * @param <E> The extension
 */
@FunctionalInterface
public interface ToLongBiFunctionWithThrowable<T, U, E extends Throwable> extends java.util.function.ToLongBiFunction<T, U> {
    /**
     * @param tolongbifunctionwiththrowable object
     * @param <T> some generic flag
     * @param <U> some generic flag
     * @param <E> The extension
     * @return the cast interface
     */
    static <T, U, E extends Throwable> ToLongBiFunctionWithThrowable<T, U, E> castToLongBiFunctionWithThrowable(ToLongBiFunctionWithThrowable<T, U, E> tolongbifunctionwiththrowable) {
        return tolongbifunctionwiththrowable;
    }

    /** 
     * Overridden method of ToLongBiFunctionWithThrowable that will call applyAsLongWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @param v2 parameter to overridden method
     * @return the value
     */
    @Override
    default long applyAsLong(T v1, U v2) {
        try {
            return applyAsLongWithThrowable(v1, v2);
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
    long applyAsLongWithThrowable(T v1, U v2) throws E;
}
