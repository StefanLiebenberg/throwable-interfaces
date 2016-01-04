package org.slieb.throwables;

/**
 * Generated from java.util.function.DoubleToLongFunction
 *
 * @param <E> The extension
 */
@FunctionalInterface
public interface DoubleToLongFunctionWithThrowable<E extends Throwable> extends java.util.function.DoubleToLongFunction {
    /**
     * @param doubletolongfunctionwiththrowable object
     * @param <E> The extension
     * @return the cast interface
     */
    static <E extends Throwable> DoubleToLongFunctionWithThrowable<E> castDoubleToLongFunctionWithThrowable(DoubleToLongFunctionWithThrowable<E> doubletolongfunctionwiththrowable) {
        return doubletolongfunctionwiththrowable;
    }

    /** 
     * Overridden method of DoubleToLongFunctionWithThrowable that will call applyAsLongWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @return the value
     */
    @Override
    default long applyAsLong(double v1) {
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
    long applyAsLongWithThrowable(double v1) throws E;
}
