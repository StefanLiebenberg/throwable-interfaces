package org.slieb.throwables;

/**
 * Generated from java.util.function.DoubleFunction
 * Extends java.util.function.DoubleFunction to allow for a checked exception.
 *
 * @param <R> some generic flag
 * @param <E> The extension
 */
@FunctionalInterface
public interface DoubleFunctionWithThrowable<R, E extends Throwable> extends java.util.function.DoubleFunction<R> {
    /**
     * Utility method to mark lambdas of type DoubleFunctionWithThrowable
     * @param doublefunctionwiththrowable The interface instance
     * @param <R> Generic that corresponds to the same generic on DoubleFunction  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <R, E extends Throwable> DoubleFunctionWithThrowable<R, E> castDoubleFunctionWithThrowable(DoubleFunctionWithThrowable<R, E> doublefunctionwiththrowable) {
        return doublefunctionwiththrowable;
    }

    /** 
     * Overridden method of DoubleFunctionWithThrowable that will call applyWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @return the value
     */
    @Override
    default R apply(double v1) {
        try {
            return applyWithThrowable(v1);
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
    R applyWithThrowable(double v1) throws E;
}
