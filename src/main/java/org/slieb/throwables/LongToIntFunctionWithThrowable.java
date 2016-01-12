package org.slieb.throwables;

/**
 * Generated from java.util.function.LongToIntFunction
 * Extends java.util.function.LongToIntFunction to allow for a checked exception.
 *
 * @param <E> The extension
 */
@FunctionalInterface
public interface LongToIntFunctionWithThrowable<E extends Throwable> extends java.util.function.LongToIntFunction {
    /**
     * Utility method to mark lambdas of type LongToIntFunctionWithThrowable
     * @param longtointfunctionwiththrowable The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> LongToIntFunctionWithThrowable<E> castLongToIntFunctionWithThrowable(LongToIntFunctionWithThrowable<E> longtointfunctionwiththrowable) {
        return longtointfunctionwiththrowable;
    }

    /** 
     * Overridden method of LongToIntFunctionWithThrowable that will call applyAsIntWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @return the value
     */
    @Override
    default int applyAsInt(long v1) {
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
    int applyAsIntWithThrowable(long v1) throws E;


    /**
     * 
     */
    default LongToIntFunctionWithThrowable<E> withLogging(java.util.logging.Logger logger, java.util.logging.Level level) {
        return (v1) -> {
            try {
                return applyAsIntWithThrowable(v1);
            } catch (final Throwable throwable) {
                logger.log(level, "exception in LongToIntFunctionWithThrowable", throwable);
                throw throwable;
            }
        };
    }


    /**
     * 
     */
    default LongToIntFunctionWithThrowable<E> withLogging(java.util.logging.Logger logger) {
  return withLogging(logger, java.util.logging.Level.WARNING);
}

    default LongToIntFunctionWithThrowable<E> withLogging() {
  return withLogging(java.util.logging.Logger.getGlobal());
}

}
