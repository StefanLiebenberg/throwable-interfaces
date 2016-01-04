package org.slieb.throwables;

/**
 * Generated from java.util.function.LongToDoubleFunction
 *
 * @param <E> The extension
 */
@FunctionalInterface
public interface LongToDoubleFunctionWithThrowable<E extends Throwable> extends java.util.function.LongToDoubleFunction {
    /**
     * @param longtodoublefunctionwiththrowable object
     * @param <E> The extension
     * @return the cast interface
     */
    static <E extends Throwable> LongToDoubleFunctionWithThrowable<E> castLongToDoubleFunctionWithThrowable(LongToDoubleFunctionWithThrowable<E> longtodoublefunctionwiththrowable) {
        return longtodoublefunctionwiththrowable;
    }

    /** 
     * Overridden method of LongToDoubleFunctionWithThrowable that will call applyAsDoubleWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @return the value
     */
    @Override
    default double applyAsDouble(long v1) {
        try {
            return applyAsDoubleWithThrowable(v1);
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
    double applyAsDoubleWithThrowable(long v1) throws E;
}
