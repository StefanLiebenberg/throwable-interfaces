package org.slieb.throwables;

/**
 * Generated from java.util.function.UnaryOperator
 *
 * @param <T> some generic flag
 * @param <E> The extension
 */
@FunctionalInterface
public interface UnaryOperatorWithThrowable<T, E extends Throwable> extends java.util.function.UnaryOperator<T> {
    /**
     * @param unaryoperatorwiththrowable object
     * @param <T> some generic flag
     * @param <E> The extension
     * @return the cast interface
     */
    static <T, E extends Throwable> UnaryOperatorWithThrowable<T, E> castUnaryOperatorWithThrowable(UnaryOperatorWithThrowable<T, E> unaryoperatorwiththrowable) {
        return unaryoperatorwiththrowable;
    }

    /** 
     * Overridden method of UnaryOperatorWithThrowable that will call applyWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @return the value
     */
    @Override
    default T apply(T v1) {
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
    T applyWithThrowable(T v1) throws E;
}
