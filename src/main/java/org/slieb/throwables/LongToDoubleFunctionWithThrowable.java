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
     *
     * @param longtodoublefunctionwiththrowable The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> LongToDoubleFunctionWithThrowable<E> castLongToDoubleFunctionWithThrowable(LongToDoubleFunctionWithThrowable<E> longtodoublefunctionwiththrowable) {
        return longtodoublefunctionwiththrowable;
    }
    /**
     * Utility method to convert LongToDoubleFunctionWithThrowable
     * @param longtodoublefunction The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> LongToDoubleFunctionWithThrowable<E> asLongToDoubleFunctionWithThrowable(java.util.function.LongToDoubleFunction longtodoublefunction) {
        return longtodoublefunction::applyAsDouble;
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
     * @param logger The logger to log exceptions on
     * @param message A message to use for logging exceptions
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default LongToDoubleFunctionWithThrowable<E> withLogging(org.slf4j.Logger logger, String message) {
        return (v1) -> {
            try {
                return applyAsDoubleWithThrowable(v1);
            } catch (final Throwable throwable) {
                logger.error(message, throwable);
                throw throwable;
            }
        };
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @param logger The logger instance to log exceptions on
     * @return An interface that will log exceptions on given logger
     */
    default LongToDoubleFunctionWithThrowable<E> withLogging(org.slf4j.Logger logger) {
        return withLogging(logger, "Exception in LongToDoubleFunctionWithThrowable");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default LongToDoubleFunctionWithThrowable<E> withLogging() {
        return withLogging(org.slf4j.LoggerFactory.getLogger(getClass()));
    }

}
