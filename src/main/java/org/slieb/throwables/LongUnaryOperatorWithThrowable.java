package org.slieb.throwables;

/**
 * Generated from java.util.function.LongUnaryOperator
 * Extends java.util.function.LongUnaryOperator to allow for a checked exception.
 *
 * @param <E> The extension
 */
@FunctionalInterface
public interface LongUnaryOperatorWithThrowable<E extends Throwable> extends java.util.function.LongUnaryOperator {
    /**
     * Utility method to mark lambdas of type LongUnaryOperatorWithThrowable
     * @param longunaryoperatorwiththrowable The interface instance
     * @param <E> The type this interface is allowed to throw
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


    /**
     * 
     */
    default LongUnaryOperatorWithThrowable<E> withLogging(java.util.logging.Logger logger, java.util.logging.Level level) {
        return (v1) -> {
            try {
                return applyAsLongWithThrowable(v1);
            } catch (final Throwable throwable) {
                logger.log(level, "exception in LongUnaryOperatorWithThrowable", throwable);
                throw throwable;
            }
        };
    }


    /**
     * 
     */
    default LongUnaryOperatorWithThrowable<E> withLogging(java.util.logging.Logger logger) {
  return withLogging(logger, java.util.logging.Level.WARNING);
}

    default LongUnaryOperatorWithThrowable<E> withLogging() {
  return withLogging(java.util.logging.Logger.getGlobal());
}

}
