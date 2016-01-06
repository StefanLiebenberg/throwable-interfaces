package org.slieb.throwables;

/**
 * Generated from java.util.function.DoubleBinaryOperator
 * Extends java.util.function.DoubleBinaryOperator to allow for a checked exception.
 *
 * @param <E> The extension
 */
@FunctionalInterface
public interface DoubleBinaryOperatorWithThrowable<E extends Throwable> extends java.util.function.DoubleBinaryOperator {
    /**
     * Utility method to mark lambdas of type DoubleBinaryOperatorWithThrowable
     * @param doublebinaryoperatorwiththrowable The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> DoubleBinaryOperatorWithThrowable<E> castDoubleBinaryOperatorWithThrowable(DoubleBinaryOperatorWithThrowable<E> doublebinaryoperatorwiththrowable) {
        return doublebinaryoperatorwiththrowable;
    }

    /** 
     * Overridden method of DoubleBinaryOperatorWithThrowable that will call applyAsDoubleWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @param v2 parameter to overridden method
     * @return the value
     */
    @Override
    default double applyAsDouble(double v1, double v2) {
        try {
            return applyAsDoubleWithThrowable(v1, v2);
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
    double applyAsDoubleWithThrowable(double v1, double v2) throws E;
}
