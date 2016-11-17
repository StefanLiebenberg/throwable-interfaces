package org.slieb.throwables;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;
import java.util.function.ObjLongConsumer;

/**
 * Generated from ObjLongConsumer
 * Extends java.util.function.ObjLongConsumer to allow for a checked exception.
 *
 * @param <T> some generic flag
 * @param <E> The extension
 */
@FunctionalInterface
@SuppressWarnings({"WeakerAccess"})
public interface ObjLongConsumerWithThrowable<T, E extends Throwable> extends ObjLongConsumer<T> {

    /**
     * Utility method to mark lambdas of type ObjLongConsumerWithThrowable
     *
     * @param objlongconsumerwiththrowable The interface instance
     * @param <T>                          Generic that corresponds to the same generic on ObjLongConsumer
     * @param <E>                          The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, E extends Throwable> ObjLongConsumerWithThrowable<T, E> castObjLongConsumerWithThrowable(
            final ObjLongConsumerWithThrowable<T, E> objlongconsumerwiththrowable) {
        return objlongconsumerwiththrowable;
    }

    /**
     * Utility method to convert ObjLongConsumerWithThrowable
     *
     * @param objlongconsumer The interface instance
     * @param <T>             Generic that corresponds to the same generic on ObjLongConsumer
     * @param <E>             The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, E extends Throwable> ObjLongConsumerWithThrowable<T, E> asObjLongConsumerWithThrowable(final ObjLongConsumer<T> objlongconsumer) {
        return objlongconsumer::accept;
    }

    /**
     * Overridden method of ObjLongConsumerWithThrowable that will call acceptWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @param v2 parameter to overridden method
     */
    @Override
    default void accept(final T v1, final long v2) {
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
    void acceptWithThrowable(final T v1, final long v2) throws E;

    /**
     * @return An interface that completely ignores exceptions. Consider using this method withLogging() as well.
     */
    default ObjLongConsumer<T> thatThrowsNothing() {
        return (final T v1, final long v2) -> {
            try {
                acceptWithThrowable(v1, v2);
            } catch (Throwable ignored) {}
        };
    }

    /**
     * @param logger  The logger to log exceptions on
     * @param message A message to use for logging exceptions
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default ObjLongConsumerWithThrowable<T, E> withLogging(final Logger logger, final String message) {
        return (final T v1, final long v2) -> {
            try {
                acceptWithThrowable(v1, v2);
            } catch (final Throwable throwable) {
                logger.error(message, v1, v2, throwable);
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
    default ObjLongConsumerWithThrowable<T, E> withLogging(final Logger logger) {
        return withLogging(logger, "Exception in ObjLongConsumerWithThrowable with the arguments [{}, {}]");
    }

    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     *
     * @return An interface that will log exceptions on global logger
     */
    default ObjLongConsumerWithThrowable<T, E> withLogging() {
        return withLogging(LoggerFactory.getLogger(getClass()));
    }

    /**
     * @param consumer An exception consumer.
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default ObjLongConsumerWithThrowable<T, E> onException(final Consumer<Throwable> consumer) {
        return (final T v1, final long v2) -> {
            try {
                acceptWithThrowable(v1, v2);
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
    default ObjLongConsumerWithThrowable<T, E> onException(final java.util.function.BiConsumer<Throwable, Object[]> consumer) {
        return (final T v1, final long v2) -> {
            try {
                acceptWithThrowable(v1, v2);
            } catch (final Throwable throwable) {
                consumer.accept(throwable, new Object[]{v1, v2});
                throw throwable;
            }
        };
    }
}
