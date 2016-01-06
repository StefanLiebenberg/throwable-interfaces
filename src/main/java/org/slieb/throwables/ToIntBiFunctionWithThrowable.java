package org.slieb.throwables;

/**
 * Generated from java.util.function.ToIntBiFunction
 * Extends java.util.function.ToIntBiFunction to allow for a checked exception.
 *
 * @param <T> some generic flag
 * @param <U> some generic flag
 * @param <E> The extension
 */
@FunctionalInterface
public interface ToIntBiFunctionWithThrowable<T, U, E extends Throwable> extends java.util.function.ToIntBiFunction<T, U> {
    /**
     * Utility method to mark lambdas of type ToIntBiFunctionWithThrowable
     * @param tointbifunctionwiththrowable The interface instance
     * @param <T> Generic that corresponds to the same generic on ToIntBiFunction  
     * @param <U> Generic that corresponds to the same generic on ToIntBiFunction  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, U, E extends Throwable> ToIntBiFunctionWithThrowable<T, U, E> castToIntBiFunctionWithThrowable(ToIntBiFunctionWithThrowable<T, U, E> tointbifunctionwiththrowable) {
        return tointbifunctionwiththrowable;
    }

    /** 
     * Overridden method of ToIntBiFunctionWithThrowable that will call applyAsIntWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @param v2 parameter to overridden method
     * @return the value
     */
    @Override
    default int applyAsInt(T v1, U v2) {
        try {
            return applyAsIntWithThrowable(v1, v2);
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
    int applyAsIntWithThrowable(T v1, U v2) throws E;
}
