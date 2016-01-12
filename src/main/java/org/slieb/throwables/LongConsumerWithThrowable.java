package org.slieb.throwables;

/**
 * Generated from java.util.function.LongConsumer
 * Extends java.util.function.LongConsumer to allow for a checked exception.
 *
 * @param <E> The extension
 */
@FunctionalInterface
public interface LongConsumerWithThrowable<E extends Throwable> extends java.util.function.LongConsumer {
    /**
     * Utility method to mark lambdas of type LongConsumerWithThrowable
     * @param longconsumerwiththrowable The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> LongConsumerWithThrowable<E> castLongConsumerWithThrowable(LongConsumerWithThrowable<E> longconsumerwiththrowable) {
        return longconsumerwiththrowable;
    }
    /**
     * Utility method to convert LongConsumerWithThrowable
     * @param longconsumer The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> LongConsumerWithThrowable<E> asLongConsumerWithThrowable(java.util.function.LongConsumer longconsumer) {
        return longconsumer::accept;
    }

    /** 
     * Overridden method of LongConsumerWithThrowable that will call acceptWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     */
    @Override
    default void accept(long v1) {
        try {
            acceptWithThrowable(v1);
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
     * @throws E some exception
     */
    void acceptWithThrowable(long v1) throws E;


    /**
     * @return A interface that completely ignores exceptions. Consider using this method withLogging() as well.
     */
    default java.util.function.LongConsumer thatDoesNothing() {
        return (v1) -> {
            try {
                acceptWithThrowable(v1);
            } catch(Throwable ignored) {}
        };
    }


    /**
     * @param logger The logger to log exceptions on
     * @param level The log level to use when logging exceptions
     * @param message A message to use for logging exceptions
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default LongConsumerWithThrowable<E> withLogging(java.util.logging.Logger logger, java.util.logging.Level level, String message) {
        return (v1) -> {
            try {
                acceptWithThrowable(v1);
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
    default LongConsumerWithThrowable<E> withLogging(java.util.logging.Logger logger) {
        return withLogging(logger, java.util.logging.Level.WARNING, "Exception in LongConsumerWithThrowable");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default LongConsumerWithThrowable<E> withLogging() {
        return withLogging(java.util.logging.Logger.getGlobal());
    }

}
