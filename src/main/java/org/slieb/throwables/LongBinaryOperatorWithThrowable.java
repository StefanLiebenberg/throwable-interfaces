package org.slieb.throwables;

/**
 * Generated from java.util.function.LongBinaryOperator
 *
 * @param <E> The extension
 */
@FunctionalInterface
public interface LongBinaryOperatorWithThrowable<E extends Throwable> extends java.util.function.LongBinaryOperator {
    /**
     * @param longbinaryoperatorwiththrowable object
     * @param <E> The extension
     * @return the cast interface
     */
    static <E extends Throwable> LongBinaryOperatorWithThrowable<E> castLongBinaryOperatorWithThrowable(LongBinaryOperatorWithThrowable<E> longbinaryoperatorwiththrowable) {
        return longbinaryoperatorwiththrowable;
    }

    /** 
     * Overridden method of LongBinaryOperatorWithThrowable that will call applyAsLongWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @param v2 parameter to overridden method
     * @return the value
     */
    @Override
    default long applyAsLong(long v1, long v2) {
        try {
            return applyAsLongWithThrowable(v1, v2);
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
    long applyAsLongWithThrowable(long v1, long v2) throws E;
}
