package org.slieb.throwables;

import java.lang.FunctionalInterface;
import java.lang.SuppressWarnings;
import java.lang.Throwable;
import java.util.function.Consumer;
import java.util.function.LongConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Generated from LongConsumer
 * Extends java.util.function.LongConsumer to allow for a checked exception.
 *
 * @param <E> The extension
 */
@FunctionalInterface
@SuppressWarnings({"WeakerAccess"})
public interface LongConsumerWithThrowable<E extends Throwable> extends LongConsumer {

    /**
     * Utility method to mark lambdas of type LongConsumerWithThrowable
     *
     * @param longconsumerwiththrowable The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> LongConsumerWithThrowable<E> castLongConsumerWithThrowable(final LongConsumerWithThrowable<E> longconsumerwiththrowable) {
        return longconsumerwiththrowable;
    }

    /**
     * Utility method to unwrap lambdas of type LongConsumer and withUncheckedThrowable any Exception
     *
     * @param longconsumerwiththrowable The interface instance
     * @param <E> The type this interface is allowed to throw
     * @throws E the original Exception from longconsumerwiththrowable
     * @return the cast interface
     */
    static <E extends Throwable> LongConsumer aLongConsumerThatUnsafelyThrowsUnchecked(final LongConsumerWithThrowable<E> longconsumerwiththrowable) throws E {
        return longconsumerwiththrowable.thatUnsafelyThrowsUnchecked();
    }

    /**
     * Utility method to convert LongConsumerWithThrowable
     * @param longconsumer The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> LongConsumerWithThrowable<E> asLongConsumerWithThrowable(final LongConsumer longconsumer) {
        return longconsumer::accept;
    }

    /**
     * Overridden method of LongConsumerWithThrowable that will call acceptWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     */
    @Override
    default void accept(final long v1) {
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
    void acceptWithThrowable(final long v1) throws E;


    /**
     * @return An interface that completely ignores exceptions. Consider using this method withLogging() as well.
     */
    default LongConsumer thatThrowsNothing() {
        return (final long v1) -> {
            try {
                acceptWithThrowable(v1);
            } catch(Throwable ignored) {}
        };
    }


    /**
     * @throws E if an exception E has been thrown, it is rethrown by this method
     * @return An interface that is only returned if no exception has been thrown.
     */
    default LongConsumer thatUnsafelyThrowsUnchecked() throws E {
        return (final long v1) -> {
            try {
                acceptWithThrowable(v1);
            } catch(final Throwable throwable) {
                SuppressedException.throwUnsafelyAsUnchecked(throwable);
            }
        };
    }


    /**
     * @param logger The logger to log exceptions on
     * @param message A message to use for logging exceptions
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default LongConsumerWithThrowable<E> withLogging(final Logger logger, final String message) {
        return (final long v1) -> {
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
    default LongConsumerWithThrowable<E> withLogging(final Logger logger) {
        return withLogging(logger, "Exception in LongConsumerWithThrowable with the argument [{}]");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default LongConsumerWithThrowable<E> withLogging() {
        return withLogging(LoggerFactory.getLogger(getClass()));
    }



    /**
     * @param consumer An exception consumer.
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default LongConsumerWithThrowable<E> onException(final Consumer<Throwable> consumer) {
        return (final long v1) -> {
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
    default LongConsumerWithThrowable<E> onException(final java.util.function.BiConsumer<Throwable, Object[]> consumer) {
        return (final long v1) -> {
            try {
                acceptWithThrowable(v1);
            } catch (final Throwable throwable) {
                consumer.accept(throwable, new Object[]{v1});
                throw throwable;
            }
        };
    }
}
