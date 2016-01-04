package org.slieb.throwables;

/**
 * Generated from java.util.function.LongUnaryOperator
 *
 * @param <E> The extension
 */
@FunctionalInterface
public interface LongUnaryOperatorWithThrowable<E extends Throwable> extends java.util.function.LongUnaryOperator {
    /**
     * @param longunaryoperatorwiththrowable object
     * @param <E> The extension
     * @return the cast interface
     */
    static <E extends Throwable> LongUnaryOperatorWithThrowable<E> castLongUnaryOperatorWithThrowable(LongUnaryOperatorWithThrowable<E> longunaryoperatorwiththrowable) {
        return longunaryoperatorwiththrowable;
    }

    /** 
     * Overridden method of LongUnaryOperatorWithThrowable that will call applyAsLongWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @return the value
     */
    @Override
    default long applyAsLong(long v1) {
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
    long applyAsLongWithThrowable(long v1) throws E;
}
