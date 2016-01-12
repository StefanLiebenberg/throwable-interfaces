package org.slieb.throwables;

/**
 * Generated from java.util.function.LongFunction
 * Extends java.util.function.LongFunction to allow for a checked exception.
 *
 * @param <R> some generic flag
 * @param <E> The extension
 */
@FunctionalInterface
public interface LongFunctionWithThrowable<R, E extends Throwable> extends java.util.function.LongFunction<R> {


    /**
     * Utility method to mark lambdas of type LongFunctionWithThrowable
     *
     * @param longfunctionwiththrowable The interface instance
     * @param <R> Generic that corresponds to the same generic on LongFunction  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <R, E extends Throwable> LongFunctionWithThrowable<R, E> castLongFunctionWithThrowable(LongFunctionWithThrowable<R, E> longfunctionwiththrowable) {
        return longfunctionwiththrowable;
    }
    /**
     * Utility method to convert LongFunctionWithThrowable
     * @param longfunction The interface instance
     * @param <R> Generic that corresponds to the same generic on LongFunction  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <R, E extends Throwable> LongFunctionWithThrowable<R, E> asLongFunctionWithThrowable(java.util.function.LongFunction<R> longfunction) {
        return longfunction::apply;
    }

    /** 
     * Overridden method of LongFunctionWithThrowable that will call applyWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @return the value
     */
    @Override
    default R apply(long v1) {
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
    R applyWithThrowable(long v1) throws E;


    /**
     * @return A interface that will wrap the result in an optional, and return an empty optional when an exception occurs.
     */
    default java.util.function.LongFunction<java.util.Optional<R>>    thatReturnsOptional() {
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
    default java.util.function.LongFunction<R> thatReturnsDefaultValue(R defaultReturnValue) {
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
    default LongFunctionWithThrowable<R, E> withLogging(org.slf4j.Logger logger, String message) {
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
    default LongFunctionWithThrowable<R, E> withLogging(org.slf4j.Logger logger) {
        return withLogging(logger, "Exception in LongFunctionWithThrowable");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default LongFunctionWithThrowable<R, E> withLogging() {
        return withLogging(org.slf4j.LoggerFactory.getLogger(getClass()));
    }

}
