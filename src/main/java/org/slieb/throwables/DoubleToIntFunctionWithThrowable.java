package org.slieb.throwables;

/**
 * Generated from java.util.function.DoubleToIntFunction
 *
 * @param <E> The extension
 */
@FunctionalInterface
public interface DoubleToIntFunctionWithThrowable<E extends Throwable> extends java.util.function.DoubleToIntFunction {
    /**
     * @param doubletointfunctionwiththrowable object
     * @param <E> The extension
     * @return the cast interface
     */
    static <E extends Throwable> DoubleToIntFunctionWithThrowable<E> castDoubleToIntFunctionWithThrowable(DoubleToIntFunctionWithThrowable<E> doubletointfunctionwiththrowable) {
        return doubletointfunctionwiththrowable;
    }

    /** 
     * Overridden method of DoubleToIntFunctionWithThrowable that will call applyAsIntWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @return the value
     */
    @Override
    default int applyAsInt(double v1) {
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
    int applyAsIntWithThrowable(double v1) throws E;
}
