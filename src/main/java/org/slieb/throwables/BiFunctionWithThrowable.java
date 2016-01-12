package org.slieb.throwables;

/**
 * Generated from java.util.function.BiFunction
 * Extends java.util.function.BiFunction to allow for a checked exception.
 *
 * @param <T> some generic flag
 * @param <U> some generic flag
 * @param <R> some generic flag
 * @param <E> The extension
 */
@FunctionalInterface
public interface BiFunctionWithThrowable<T, U, R, E extends Throwable> extends java.util.function.BiFunction<T, U, R> {

    
    /**
     * Utility method to mark lambdas of type BiFunctionWithThrowable
     *
     * @param bifunctionwiththrowable The interface instance
     * @param <T>                     Generic that corresponds to the same generic on BiFunction
     * @param <U>                     Generic that corresponds to the same generic on BiFunction
     * @param <R>                     Generic that corresponds to the same generic on BiFunction
     * @param <E>                     The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, U, R, E extends Throwable> BiFunctionWithThrowable<T, U, R, E> castBiFunctionWithThrowable(BiFunctionWithThrowable<T, U, R, E> bifunctionwiththrowable) {
        return bifunctionwiththrowable;
    }

    /**
     * Utility method to convert BiFunctionWithThrowable
     *
     * @param bifunction The interface instance
     * @param <T>        Generic that corresponds to the same generic on BiFunction
     * @param <U>        Generic that corresponds to the same generic on BiFunction
     * @param <R>        Generic that corresponds to the same generic on BiFunction
     * @param <E>        The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, U, R, E extends Throwable> BiFunctionWithThrowable<T, U, R, E> asBiFunctionWithThrowable(java.util.function.BiFunction<T, U, R> bifunction) {
        return bifunction::apply;
    }

    /**
     * Overridden method of BiFunctionWithThrowable that will call applyWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @param v2 parameter to overridden method
     * @return the value
     */
    @Override
    default R apply(T v1, U v2) {
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
    R applyWithThrowable(T v1, U v2) throws E;


    /**
     * @return A interface that will wrap the result in an optional, and return an empty optional when an exception occurs.
     */
    default java.util.function.BiFunction<T, U, java.util.Optional<R>> thatReturnsOptional() {
        return (v1, v2) -> {
            try {
                return java.util.Optional.of(applyWithThrowable(v1, v2));
            } catch (Throwable throwable) {
                return java.util.Optional.empty();
            }
        };
    }


    /**
     * @return An interface that returns a default value if any exception occurs.
     */
    default java.util.function.BiFunction<T, U, R> thatReturnsDefaultValue(R defaultReturnValue) {
        return (v1, v2) -> {
            try {
                return applyWithThrowable(v1, v2);
            } catch (Throwable throwable) {
                return defaultReturnValue;
            }
        };
    }


    /**
     * @param logger  The logger to log exceptions on
     * @param level   The log level to use when logging exceptions
     * @param message A message to use for logging exceptions
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default BiFunctionWithThrowable<T, U, R, E> withLogging(java.util.logging.Logger logger, java.util.logging.Level level, String message) {
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
     *
     * @param logger The logger instance to log exceptions on
     * @return An interface that will log exceptions on given logger
     */
    default BiFunctionWithThrowable<T, U, R, E> withLogging(java.util.logging.Logger logger) {
        return withLogging(logger, java.util.logging.Level.WARNING, "Exception in BiFunctionWithThrowable");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     *
     * @return An interface that will log exceptions on global logger
     */
    default BiFunctionWithThrowable<T, U, R, E> withLogging() {
        return withLogging(java.util.logging.Logger.getGlobal());
    }

}
