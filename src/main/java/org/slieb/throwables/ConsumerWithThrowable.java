package org.slieb.throwables;

import java.lang.FunctionalInterface;
import java.lang.SuppressWarnings;
import java.lang.Throwable;
import java.util.function.Consumer;
import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Generated from Consumer
 * Extends java.util.function.Consumer to allow for a checked exception.
 *
 * @param <T> some generic flag
 * @param <E> The extension
 */
@FunctionalInterface
@SuppressWarnings({"WeakerAccess"})
public interface ConsumerWithThrowable<T, E extends Throwable> extends Consumer<T> {

    /**
     * Utility method to mark lambdas of type ConsumerWithThrowable
     *
     * @param consumerwiththrowable The interface instance
     * @param <T> Generic that corresponds to the same generic on Consumer  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, E extends Throwable> ConsumerWithThrowable<T, E> castConsumerWithThrowable(final ConsumerWithThrowable<T, E> consumerwiththrowable) {
        return consumerwiththrowable;
    }

    /**
     * Utility method to convert ConsumerWithThrowable
     * @param consumer The interface instance
     * @param <T> Generic that corresponds to the same generic on Consumer  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, E extends Throwable> ConsumerWithThrowable<T, E> asConsumerWithThrowable(final Consumer<T> consumer) {
        return consumer::accept;
    }

    /** 
     * Overridden method of ConsumerWithThrowable that will call acceptWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     */
    @Override
    default void accept(final T v1) {
        try {
            acceptWithThrowable(v1);
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
     * @throws E some exception
     */
    void acceptWithThrowable(final T v1) throws E;


    /**
     * @return An interface that completely ignores exceptions. Consider using this method withLogging() as well.
     */
    default Consumer<T> thatThrowsNothing() {
        return (final T v1) -> {
            try {
                acceptWithThrowable(v1);
            } catch(Throwable ignored) {}
        };
    }


    /**
     * @param logger The logger to log exceptions on
     * @param message A message to use for logging exceptions
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default ConsumerWithThrowable<T, E> withLogging(final Logger logger, final String message) {
        return (final T v1) -> {
            try {
                acceptWithThrowable(v1);
            } catch (final Throwable throwable) {
                logger.error(message, v1, throwable);
                throw throwable;
            }
        };
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @param logger The logger instance to log exceptions on
     * @return An interface that will log exceptions on given logger
     */
    default ConsumerWithThrowable<T, E> withLogging(final Logger logger) {
        return withLogging(logger, "Exception in ConsumerWithThrowable with the argument [{}]");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default ConsumerWithThrowable<T, E> withLogging() {
        return withLogging(LoggerFactory.getLogger(getClass()));
    }



    /**
     * @param consumer An exception consumer.
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default ConsumerWithThrowable<T, E> onException(final Consumer<Throwable> consumer) {
        return (final T v1) -> {
            try {
                acceptWithThrowable(v1);
            } catch (final Throwable throwable) {
                consumer.accept(throwable);
                throw throwable;
            }
        };
    }


    /**
     * @param consumer An exception consumer.
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default ConsumerWithThrowable<T, E> onException(final java.util.function.BiConsumer<Throwable, Object[]> consumer) {
        return (final T v1) -> {
            try {
                acceptWithThrowable(v1);
            } catch (final Throwable throwable) {
                consumer.accept(throwable, new Object[]{v1});
                throw throwable;
            }
        };
    }
}
