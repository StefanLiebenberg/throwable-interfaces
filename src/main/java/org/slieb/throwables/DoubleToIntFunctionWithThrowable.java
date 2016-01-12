package org.slieb.throwables;

/**
 * Generated from java.util.function.DoubleToIntFunction
 * Extends java.util.function.DoubleToIntFunction to allow for a checked exception.
 *
 * @param <E> The extension
 */
@FunctionalInterface
public interface DoubleToIntFunctionWithThrowable<E extends Throwable> extends java.util.function.DoubleToIntFunction {


    /**
     * Utility method to mark lambdas of type DoubleToIntFunctionWithThrowable
     *
     * @param doubletointfunctionwiththrowable The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> DoubleToIntFunctionWithThrowable<E> castDoubleToIntFunctionWithThrowable(DoubleToIntFunctionWithThrowable<E> doubletointfunctionwiththrowable) {
        return doubletointfunctionwiththrowable;
    }
    /**
     * Utility method to convert DoubleToIntFunctionWithThrowable
     * @param doubletointfunction The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> DoubleToIntFunctionWithThrowable<E> asDoubleToIntFunctionWithThrowable(java.util.function.DoubleToIntFunction doubletointfunction) {
        return doubletointfunction::applyAsInt;
    }

    /** 
     * Overridden method of DoubleToIntFunctionWithThrowable that will call applyAsIntWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @return the value
     */
    @Override
    default int applyAsInt(double v1) {
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
    int applyAsIntWithThrowable(double v1) throws E;


    /**
     * @param logger The logger to log exceptions on
     * @param message A message to use for logging exceptions
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default DoubleToIntFunctionWithThrowable<E> withLogging(org.slf4j.Logger logger, String message) {
        return (v1) -> {
            try {
                return applyAsIntWithThrowable(v1);
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
    default DoubleToIntFunctionWithThrowable<E> withLogging(org.slf4j.Logger logger) {
        return withLogging(logger, "Exception in DoubleToIntFunctionWithThrowable");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default DoubleToIntFunctionWithThrowable<E> withLogging() {
        return withLogging(org.slf4j.LoggerFactory.getLogger(getClass()));
    }

}
