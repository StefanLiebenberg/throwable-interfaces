package org.slieb.throwables;

/**
 * Generated from java.util.function.ToIntFunction
 * Extends java.util.function.ToIntFunction to allow for a checked exception.
 *
 * @param <T> some generic flag
 * @param <E> The extension
 */
@FunctionalInterface
public interface ToIntFunctionWithThrowable<T, E extends Throwable> extends java.util.function.ToIntFunction<T> {
    /**
     * Utility method to mark lambdas of type ToIntFunctionWithThrowable
     * @param tointfunctionwiththrowable The interface instance
     * @param <T> Generic that corresponds to the same generic on ToIntFunction  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, E extends Throwable> ToIntFunctionWithThrowable<T, E> castToIntFunctionWithThrowable(ToIntFunctionWithThrowable<T, E> tointfunctionwiththrowable) {
        return tointfunctionwiththrowable;
    }

    /** 
     * Overridden method of ToIntFunctionWithThrowable that will call applyAsIntWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @return the value
     */
    @Override
    default int applyAsInt(T v1) {
        try {
            return applyAsIntWithThrowable(v1);
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
    int applyAsIntWithThrowable(T v1) throws E;
}
