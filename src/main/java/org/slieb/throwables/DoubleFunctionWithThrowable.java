package org.slieb.throwables;

/**
 * Generated from java.util.function.DoubleFunction
 * Extends java.util.function.DoubleFunction to allow for a checked exception.
 *
 * @param <R> some generic flag
 * @param <E> The extension
 */
@FunctionalInterface
public interface DoubleFunctionWithThrowable<R, E extends Throwable> extends java.util.function.DoubleFunction<R> {


    /**
     * Utility method to mark lambdas of type DoubleFunctionWithThrowable
     *
     * @param doublefunctionwiththrowable The interface instance
     * @param <R> Generic that corresponds to the same generic on DoubleFunction  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <R, E extends Throwable> DoubleFunctionWithThrowable<R, E> castDoubleFunctionWithThrowable(DoubleFunctionWithThrowable<R, E> doublefunctionwiththrowable) {
        return doublefunctionwiththrowable;
    }
    /**
     * Utility method to convert DoubleFunctionWithThrowable
     * @param doublefunction The interface instance
     * @param <R> Generic that corresponds to the same generic on DoubleFunction  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <R, E extends Throwable> DoubleFunctionWithThrowable<R, E> asDoubleFunctionWithThrowable(java.util.function.DoubleFunction<R> doublefunction) {
        return doublefunction::apply;
    }

    /** 
     * Overridden method of DoubleFunctionWithThrowable that will call applyWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @return the value
     */
    @Override
    default R apply(double v1) {
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
    R applyWithThrowable(double v1) throws E;


    /**
     * @return A interface that will wrap the result in an optional, and return an empty optional when an exception occurs.
     */
    default java.util.function.DoubleFunction<java.util.Optional<R>>    thatReturnsOptional() {
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
    default java.util.function.DoubleFunction<R> thatReturnsDefaultValue(R defaultReturnValue) {
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
    default DoubleFunctionWithThrowable<R, E> withLogging(org.slf4j.Logger logger, String message) {
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
    default DoubleFunctionWithThrowable<R, E> withLogging(org.slf4j.Logger logger) {
        return withLogging(logger, "Exception in DoubleFunctionWithThrowable");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default DoubleFunctionWithThrowable<R, E> withLogging() {
        return withLogging(org.slf4j.LoggerFactory.getLogger(getClass()));
    }

}
