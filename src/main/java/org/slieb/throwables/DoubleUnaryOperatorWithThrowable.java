package org.slieb.throwables;

/**
 * Generated from java.util.function.DoubleUnaryOperator
 * Extends java.util.function.DoubleUnaryOperator to allow for a checked exception.
 *
 * @param <E> The extension
 */
@FunctionalInterface
public interface DoubleUnaryOperatorWithThrowable<E extends Throwable> extends java.util.function.DoubleUnaryOperator {
    /**
     * Utility method to mark lambdas of type DoubleUnaryOperatorWithThrowable
     * @param doubleunaryoperatorwiththrowable The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> DoubleUnaryOperatorWithThrowable<E> castDoubleUnaryOperatorWithThrowable(DoubleUnaryOperatorWithThrowable<E> doubleunaryoperatorwiththrowable) {
        return doubleunaryoperatorwiththrowable;
    }

    /** 
     * Overridden method of DoubleUnaryOperatorWithThrowable that will call applyAsDoubleWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @return the value
     */
    @Override
    default double applyAsDouble(double v1) {
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
    double applyAsDoubleWithThrowable(double v1) throws E;


    /**
     * 
     */
    default DoubleUnaryOperatorWithThrowable<E> withLogging(java.util.logging.Logger logger, java.util.logging.Level level) {
        return (v1) -> {
            try {
                return applyAsDoubleWithThrowable(v1);
            } catch (final Throwable throwable) {
                logger.log(level, "exception in DoubleUnaryOperatorWithThrowable", throwable);
                throw throwable;
            }
        };
    }


    /**
     * 
     */
    default DoubleUnaryOperatorWithThrowable<E> withLogging(java.util.logging.Logger logger) {
  return withLogging(logger, java.util.logging.Level.WARNING);
}

    default DoubleUnaryOperatorWithThrowable<E> withLogging() {
  return withLogging(java.util.logging.Logger.getGlobal());
}

}
