package org.slieb.throwables;

/**
 * Generated from java.util.function.DoubleFunction
 *
 * @param <R> some generic flag
 * @param <E> The extension
 */
@FunctionalInterface
public interface DoubleFunctionWithThrowable<R, E extends Throwable> extends java.util.function.DoubleFunction<R> {
    /**
     * @param doublefunctionwiththrowable object
     * @param <R> some generic flag
     * @param <E> The extension
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
