package org.slieb.throwables;

/**
 * Generated from java.util.function.Function
 * Extends java.util.function.Function to allow for a checked exception.
 *
 * @param <T> some generic flag
 * @param <R> some generic flag
 * @param <E> The extension
 */
@FunctionalInterface
public interface FunctionWithThrowable<T, R, E extends Throwable> extends java.util.function.Function<T, R> {


    /**
     * Utility method to mark lambdas of type FunctionWithThrowable
     *
     * @param functionwiththrowable The interface instance
     * @param <T> Generic that corresponds to the same generic on Function  
     * @param <R> Generic that corresponds to the same generic on Function  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, R, E extends Throwable> FunctionWithThrowable<T, R, E> castFunctionWithThrowable(FunctionWithThrowable<T, R, E> functionwiththrowable) {
        return functionwiththrowable;
    }
    /**
     * Utility method to convert FunctionWithThrowable
     * @param function The interface instance
     * @param <T> Generic that corresponds to the same generic on Function  
     * @param <R> Generic that corresponds to the same generic on Function  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, R, E extends Throwable> FunctionWithThrowable<T, R, E> asFunctionWithThrowable(java.util.function.Function<T, R> function) {
        return function::apply;
    }

    /** 
     * Overridden method of FunctionWithThrowable that will call applyWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @return the value
     */
    @Override
    default R apply(T v1) {
        try {
            return applyWithThrowable(v1);
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
    R applyWithThrowable(T v1) throws E;


    /**
     * @return A interface that will wrap the result in an optional, and return an empty optional when an exception occurs.
     */
    default java.util.function.Function<T, java.util.Optional<R>>    thatReturnsOptional() {
      return (v1)     -> {
        try {
          return java.util.Optional.of(applyWithThrowable(v1));
        } catch(Throwable throwable) {
          return java.util.Optional.empty();
        }
      };
    }


    /**
     * @return An interface that returns a default value if any exception occurs.
     */
    default java.util.function.Function<T, R> thatReturnsDefaultValue(R defaultReturnValue) {
      return (v1) -> {
        try {
          return applyWithThrowable(v1);
        } catch(Throwable throwable) {
          return defaultReturnValue;
        }
      };
    }


    /**
     * @param logger The logger to log exceptions on
     * @param message A message to use for logging exceptions
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default FunctionWithThrowable<T, R, E> withLogging(org.slf4j.Logger logger, String message) {
        return (v1) -> {
            try {
                return applyWithThrowable(v1);
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
    default FunctionWithThrowable<T, R, E> withLogging(org.slf4j.Logger logger) {
        return withLogging(logger, "Exception in FunctionWithThrowable");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default FunctionWithThrowable<T, R, E> withLogging() {
        return withLogging(org.slf4j.LoggerFactory.getLogger(getClass()));
    }

}
