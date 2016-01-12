package org.slieb.throwables;

/**
 * Generated from java.util.function.DoubleBinaryOperator
 * Extends java.util.function.DoubleBinaryOperator to allow for a checked exception.
 *
 * @param <E> The extension
 */
@FunctionalInterface
public interface DoubleBinaryOperatorWithThrowable<E extends Throwable> extends java.util.function.DoubleBinaryOperator {
    /**
     * Utility method to mark lambdas of type DoubleBinaryOperatorWithThrowable
     * @param doublebinaryoperatorwiththrowable The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> DoubleBinaryOperatorWithThrowable<E> castDoubleBinaryOperatorWithThrowable(DoubleBinaryOperatorWithThrowable<E> doublebinaryoperatorwiththrowable) {
        return doublebinaryoperatorwiththrowable;
    }
    /**
     * Utility method to convert DoubleBinaryOperatorWithThrowable
     * @param doublebinaryoperator The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> DoubleBinaryOperatorWithThrowable<E> asDoubleBinaryOperatorWithThrowable(java.util.function.DoubleBinaryOperator doublebinaryoperator) {
        return doublebinaryoperator::applyAsDouble;
    }

    /** 
     * Overridden method of DoubleBinaryOperatorWithThrowable that will call applyAsDoubleWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @param v2 parameter to overridden method
     * @return the value
     */
    @Override
    default double applyAsDouble(double v1, double v2) {
        try {
            return applyAsDoubleWithThrowable(v1, v2);
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
    double applyAsDoubleWithThrowable(double v1, double v2) throws E;


    /**
     * @param logger The logger to log exceptions on
     * @param level The log level to use when logging exceptions
     * @param message A message to use for logging exceptions
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default DoubleBinaryOperatorWithThrowable<E> withLogging(java.util.logging.Logger logger, java.util.logging.Level level, String message) {
        return (v1, v2) -> {
            try {
                return applyAsDoubleWithThrowable(v1, v2);
            } catch (final Throwable throwable) {
                logger.log(level, message, throwable);
                throw throwable;
            }
        };
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @param logger The logger instance to log exceptions on
     * @return An interface that will log exceptions on given logger
     */
    default DoubleBinaryOperatorWithThrowable<E> withLogging(java.util.logging.Logger logger) {
        return withLogging(logger, java.util.logging.Level.WARNING, "Exception in DoubleBinaryOperatorWithThrowable");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default DoubleBinaryOperatorWithThrowable<E> withLogging() {
        return withLogging(java.util.logging.Logger.getGlobal());
    }

}
