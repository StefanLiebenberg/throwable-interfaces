package org.slieb.throwables;

/**
 * Generated from java.util.function.IntBinaryOperator
 * Extends java.util.function.IntBinaryOperator to allow for a checked exception.
 *
 * @param <E> The extension
 */
@FunctionalInterface
public interface IntBinaryOperatorWithThrowable<E extends Throwable> extends java.util.function.IntBinaryOperator {
    /**
     * Utility method to mark lambdas of type IntBinaryOperatorWithThrowable
     * @param intbinaryoperatorwiththrowable The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> IntBinaryOperatorWithThrowable<E> castIntBinaryOperatorWithThrowable(IntBinaryOperatorWithThrowable<E> intbinaryoperatorwiththrowable) {
        return intbinaryoperatorwiththrowable;
    }
    /**
     * Utility method to convert IntBinaryOperatorWithThrowable
     * @param intbinaryoperator The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> IntBinaryOperatorWithThrowable<E> asIntBinaryOperatorWithThrowable(java.util.function.IntBinaryOperator intbinaryoperator) {
        return intbinaryoperator::applyAsInt;
    }

    /** 
     * Overridden method of IntBinaryOperatorWithThrowable that will call applyAsIntWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @param v2 parameter to overridden method
     * @return the value
     */
    @Override
    default int applyAsInt(int v1, int v2) {
        try {
            return applyAsIntWithThrowable(v1, v2);
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
    int applyAsIntWithThrowable(int v1, int v2) throws E;


    /**
     * @param logger The logger to log exceptions on
     * @param level The log level to use when logging exceptions
     * @param message A message to use for logging exceptions
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default IntBinaryOperatorWithThrowable<E> withLogging(java.util.logging.Logger logger, java.util.logging.Level level, String message) {
        return (v1, v2) -> {
            try {
                return applyAsIntWithThrowable(v1, v2);
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
    default IntBinaryOperatorWithThrowable<E> withLogging(java.util.logging.Logger logger) {
        return withLogging(logger, java.util.logging.Level.WARNING, "Exception in IntBinaryOperatorWithThrowable");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default IntBinaryOperatorWithThrowable<E> withLogging() {
        return withLogging(java.util.logging.Logger.getGlobal());
    }

}
