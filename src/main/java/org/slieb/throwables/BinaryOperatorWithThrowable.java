package org.slieb.throwables;

/**
 * Generated from java.util.function.BinaryOperator
 * Extends java.util.function.BinaryOperator to allow for a checked exception.
 *
 * @param <T> some generic flag
 * @param <E> The extension
 */
@FunctionalInterface
public interface BinaryOperatorWithThrowable<T, E extends Throwable> extends java.util.function.BinaryOperator<T> {
    /**
     * Utility method to mark lambdas of type BinaryOperatorWithThrowable
     * @param binaryoperatorwiththrowable The interface instance
     * @param <T> Generic that corresponds to the same generic on BinaryOperator  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, E extends Throwable> BinaryOperatorWithThrowable<T, E> castBinaryOperatorWithThrowable(BinaryOperatorWithThrowable<T, E> binaryoperatorwiththrowable) {
        return binaryoperatorwiththrowable;
    }
    /**
     * Utility method to convert BinaryOperatorWithThrowable
     * @param binaryoperator The interface instance
     * @param <T> Generic that corresponds to the same generic on BinaryOperator  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, E extends Throwable> BinaryOperatorWithThrowable<T, E> asBinaryOperatorWithThrowable(java.util.function.BinaryOperator<T> binaryoperator) {
        return binaryoperator::apply;
    }

    /** 
     * Overridden method of BinaryOperatorWithThrowable that will call applyWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @param v2 parameter to overridden method
     * @return the value
     */
    @Override
    default T apply(T v1, T v2) {
        try {
            return applyWithThrowable(v1, v2);
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
    T applyWithThrowable(T v1, T v2) throws E;


    /**
     * @return An interface that returns a default value if any exception occurs.
     */
    default java.util.function.BinaryOperator<T> thatReturnsDefaultValue(T defaultReturnValue) {
      return (v1, v2) -> {
        try {
          return applyWithThrowable(v1, v2);
        } catch(Throwable throwable) {
          return defaultReturnValue;
        }
      };
    }


    /**
     * @param logger The logger to log exceptions on
     * @param level The log level to use when logging exceptions
     * @param message A message to use for logging exceptions
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default BinaryOperatorWithThrowable<T, E> withLogging(java.util.logging.Logger logger, java.util.logging.Level level, String message) {
        return (v1, v2) -> {
            try {
                return applyWithThrowable(v1, v2);
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
    default BinaryOperatorWithThrowable<T, E> withLogging(java.util.logging.Logger logger) {
        return withLogging(logger, java.util.logging.Level.WARNING, "Exception in BinaryOperatorWithThrowable");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default BinaryOperatorWithThrowable<T, E> withLogging() {
        return withLogging(java.util.logging.Logger.getGlobal());
    }

}
