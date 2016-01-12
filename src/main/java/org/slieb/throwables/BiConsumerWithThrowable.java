package org.slieb.throwables;

/**
 * Generated from java.util.function.BiConsumer
 * Extends java.util.function.BiConsumer to allow for a checked exception.
 *
 * @param <T> some generic flag
 * @param <U> some generic flag
 * @param <E> The extension
 */
@FunctionalInterface
public interface BiConsumerWithThrowable<T, U, E extends Throwable> extends java.util.function.BiConsumer<T, U> {


    /**
     * Utility method to mark lambdas of type BiConsumerWithThrowable
     *
     * @param biconsumerwiththrowable The interface instance
     * @param <T>                     Generic that corresponds to the same generic on BiConsumer
     * @param <U>                     Generic that corresponds to the same generic on BiConsumer
     * @param <E>                     The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, U, E extends Throwable> BiConsumerWithThrowable<T, U, E> castBiConsumerWithThrowable(BiConsumerWithThrowable<T, U, E> biconsumerwiththrowable) {
        return biconsumerwiththrowable;
    }

    /**
     * Utility method to convert BiConsumerWithThrowable
     *
     * @param biconsumer The interface instance
     * @param <T>        Generic that corresponds to the same generic on BiConsumer
     * @param <U>        Generic that corresponds to the same generic on BiConsumer
     * @param <E>        The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, U, E extends Throwable> BiConsumerWithThrowable<T, U, E> asBiConsumerWithThrowable(java.util.function.BiConsumer<T, U> biconsumer) {
        return biconsumer::accept;
    }

    /**
     * Overridden method of BiConsumerWithThrowable that will call acceptWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @param v2 parameter to overridden method
     */
    @Override
    default void accept(T v1, U v2) {
        try {
            acceptWithThrowable(v1, v2);
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
     * @throws E some exception
     */
    void acceptWithThrowable(T v1, U v2) throws E;


    /**
     * @return A interface that completely ignores exceptions. Consider using this method withLogging() as well.
     */
    default java.util.function.BiConsumer<T, U> thatDoesNothing() {
        return (v1, v2) -> {
            try {
                acceptWithThrowable(v1, v2);
            } catch (Throwable ignored) {
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
    default BiConsumerWithThrowable<T, U, E> withLogging(java.util.logging.Logger logger, java.util.logging.Level level, String message) {
        return (v1, v2) -> {
            try {
                acceptWithThrowable(v1, v2);
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
    default BiConsumerWithThrowable<T, U, E> withLogging(java.util.logging.Logger logger) {
        return withLogging(logger, java.util.logging.Level.WARNING, "Exception in BiConsumerWithThrowable");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     *
     * @return An interface that will log exceptions on global logger
     */
    default BiConsumerWithThrowable<T, U, E> withLogging() {
        return withLogging(java.util.logging.Logger.getGlobal());
    }

}
