package org.slieb.throwables;

/**
 * Generated from java.util.function.ToLongFunction
 *
 * @param <T> some generic flag
 * @param <E> The extension
 */
@FunctionalInterface
public interface ToLongFunctionWithThrowable<T, E extends Throwable> extends java.util.function.ToLongFunction<T> {
    /**
     * @param tolongfunctionwiththrowable object
     * @param <T> some generic flag
     * @param <E> The extension
     * @return the cast interface
     */
    static <T, E extends Throwable> ToLongFunctionWithThrowable<T, E> castToLongFunctionWithThrowable(ToLongFunctionWithThrowable<T, E> tolongfunctionwiththrowable) {
        return tolongfunctionwiththrowable;
    }

    /** 
     * Overridden method of ToLongFunctionWithThrowable that will call applyAsLongWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @return the value
     */
    @Override
    default long applyAsLong(T v1) {
        try {
            return applyAsLongWithThrowable(v1);
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
     * @return the value
     * @throws E some exception
     */
    long applyAsLongWithThrowable(T v1) throws E;
}
