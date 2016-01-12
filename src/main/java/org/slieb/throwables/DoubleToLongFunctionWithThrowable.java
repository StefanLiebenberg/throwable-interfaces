package org.slieb.throwables;

/**
 * Generated from java.util.function.DoubleToLongFunction
 * Extends java.util.function.DoubleToLongFunction to allow for a checked exception.
 *
 * @param <E> The extension
 */
@FunctionalInterface
public interface DoubleToLongFunctionWithThrowable<E extends Throwable> extends java.util.function.DoubleToLongFunction {
    /**
     * Utility method to mark lambdas of type DoubleToLongFunctionWithThrowable
     * @param doubletolongfunctionwiththrowable The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> DoubleToLongFunctionWithThrowable<E> castDoubleToLongFunctionWithThrowable(DoubleToLongFunctionWithThrowable<E> doubletolongfunctionwiththrowable) {
        return doubletolongfunctionwiththrowable;
    }

    /** 
     * Overridden method of DoubleToLongFunctionWithThrowable that will call applyAsLongWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @return the value
     */
    @Override
    default long applyAsLong(double v1) {
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
    long applyAsLongWithThrowable(double v1) throws E;


    /**
     * 
     */
    default DoubleToLongFunctionWithThrowable<E> withLogging(java.util.logging.Logger logger, java.util.logging.Level level) {
        return (v1) -> {
            try {
                return applyAsLongWithThrowable(v1);
            } catch (final Throwable throwable) {
                logger.log(level, "exception in DoubleToLongFunctionWithThrowable", throwable);
                throw throwable;
            }
        };
    }


    /**
     * 
     */
    default DoubleToLongFunctionWithThrowable<E> withLogging(java.util.logging.Logger logger) {
  return withLogging(logger, java.util.logging.Level.WARNING);
}

    default DoubleToLongFunctionWithThrowable<E> withLogging() {
  return withLogging(java.util.logging.Logger.getGlobal());
}

}
