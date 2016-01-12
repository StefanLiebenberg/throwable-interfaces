package org.slieb.throwables;

/**
 * Generated from java.util.function.LongToDoubleFunction
 * Extends java.util.function.LongToDoubleFunction to allow for a checked exception.
 *
 * @param <E> The extension
 */
@FunctionalInterface
public interface LongToDoubleFunctionWithThrowable<E extends Throwable> extends java.util.function.LongToDoubleFunction {
    /**
     * Utility method to mark lambdas of type LongToDoubleFunctionWithThrowable
     * @param longtodoublefunctionwiththrowable The interface instance
     * @param <E> The type this interface is allowed to throw
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


    /**
     * 
     */
    default LongToDoubleFunctionWithThrowable<E> withLogging(java.util.logging.Logger logger, java.util.logging.Level level) {
        return (v1) -> {
            try {
                return applyAsDoubleWithThrowable(v1);
            } catch (final Throwable throwable) {
                logger.log(level, "exception in LongToDoubleFunctionWithThrowable", throwable);
                throw throwable;
            }
        };
    }


    /**
     * 
     */
    default LongToDoubleFunctionWithThrowable<E> withLogging(java.util.logging.Logger logger) {
  return withLogging(logger, java.util.logging.Level.WARNING);
}

    default LongToDoubleFunctionWithThrowable<E> withLogging() {
  return withLogging(java.util.logging.Logger.getGlobal());
}

}
