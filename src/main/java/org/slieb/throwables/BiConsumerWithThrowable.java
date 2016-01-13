package org.slieb.throwables;

import org.slf4j.Logger;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
/**
 * Generated from BiConsumer
 * Extends java.util.function.BiConsumer to allow for a checked exception.
 *
 * @param <T> some generic flag
 * @param <U> some generic flag
 * @param <E> The extension
 */
@FunctionalInterface
public interface BiConsumerWithThrowable<T, U, E extends Throwable> extends BiConsumer<T, U> {


    /**
     * Utility method to mark lambdas of type BiConsumerWithThrowable
     *
     * @param biconsumerwiththrowable The interface instance
     * @param <T> Generic that corresponds to the same generic on BiConsumer  
     * @param <U> Generic that corresponds to the same generic on BiConsumer  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, U, E extends Throwable> BiConsumerWithThrowable<T, U, E> castBiConsumerWithThrowable(final BiConsumerWithThrowable<T, U, E> biconsumerwiththrowable) {
        return biconsumerwiththrowable;
    }
    /**
     * Utility method to convert BiConsumerWithThrowable
     * @param biconsumer The interface instance
     * @param <T> Generic that corresponds to the same generic on BiConsumer  
     * @param <U> Generic that corresponds to the same generic on BiConsumer  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, U, E extends Throwable> BiConsumerWithThrowable<T, U, E> asBiConsumerWithThrowable(final BiConsumer<T, U> biconsumer) {
        return biconsumer::accept;
    }

    /** 
     * Overridden method of BiConsumerWithThrowable that will call acceptWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @param v2 parameter to overridden method
     */
    @Override
    default void accept(final T v1, final U v2) {
        try {
            acceptWithThrowable(v1, v2);
        } catch (final RuntimeException | Error exception) {
            throw exception;
        } catch (final Throwable throwable) {
            throw new SuppressedException(throwable);
        }
    }

    /** 
     * Functional method that will throw exceptions.
     *
     * @param v1 parameter to overridden method
     * @param v2 parameter to overridden method
     * @throws E some exception
     */
    void acceptWithThrowable(final T v1, final U v2) throws E;


    /**
     * @return An interface that completely ignores exceptions. Consider using this method withLogging() as well.
     */
    default BiConsumer<T, U> thatIgnoresExceptions() {
        return (final T v1, final U v2) -> {
            try {
                acceptWithThrowable(v1, v2);
            } catch(Throwable ignored) {}
        };
    }


    /**
     * @param logger The logger to log exceptions on
     * @param message A message to use for logging exceptions
     * @return An interface that will log all exceptions to given logger
     */
    default BiConsumerWithThrowable<T, U, E> withLogging(Logger logger, String message) {
        return (final T v1, final U v2) -> {
            try {
                acceptWithThrowable(v1, v2);
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
    default BiConsumerWithThrowable<T, U, E> withLogging(org.slf4j.Logger logger) {
        return withLogging(logger, "Exception in BiConsumerWithThrowable with arguments {} {}");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default BiConsumerWithThrowable<T, U, E> withLogging() {
        return withLogging(org.slf4j.LoggerFactory.getLogger(getClass()));
    }



    /**
     * @param consumer An exception consumer.
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default BiConsumerWithThrowable<T, U, E> onException(Consumer<Throwable> consumer) {
        return (final T v1, final U v2) -> {
            try {
                acceptWithThrowable(v1, v2);
            } catch (final Throwable throwable) {
                consumer.accept(throwable);
                throw throwable;
            }
        };
    }
}
