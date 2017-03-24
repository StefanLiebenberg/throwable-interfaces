package org.slieb.throwables;

import java.lang.FunctionalInterface;
import java.lang.SuppressWarnings;
import java.lang.Throwable;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Generated from DoubleConsumer
 * Extends java.util.function.DoubleConsumer to allow for a checked exception.
 *
 * @param <E> The extension
 */
@FunctionalInterface
@SuppressWarnings({"WeakerAccess"})
public interface DoubleConsumerWithThrowable<E extends Throwable> extends DoubleConsumer {

    /**
     * Utility method to mark lambdas of type DoubleConsumerWithThrowable
     *
     * @param doubleconsumerwiththrowable The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> DoubleConsumerWithThrowable<E> castDoubleConsumerWithThrowable(final DoubleConsumerWithThrowable<E> doubleconsumerwiththrowable) {
        return doubleconsumerwiththrowable;
    }

    /**
     * Utility method to unwrap lambdas of type DoubleConsumer and withUncheckedThrowable any Exception
     *
     * @param doubleconsumerwiththrowable The interface instance
     * @param <E> The type this interface is allowed to throw
     * @throws E the original Exception from doubleconsumerwiththrowable
     * @return the cast interface
     */
    static <E extends Throwable> DoubleConsumer aDoubleConsumerThatUnsafelyThrowsUnchecked(final DoubleConsumerWithThrowable<E> doubleconsumerwiththrowable) throws E {
        return doubleconsumerwiththrowable.thatUnsafelyThrowsUnchecked();
    }

    /**
     * Utility method to convert DoubleConsumerWithThrowable
     * @param doubleconsumer The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> DoubleConsumerWithThrowable<E> asDoubleConsumerWithThrowable(final DoubleConsumer doubleconsumer) {
        return doubleconsumer::accept;
    }

    /**
     * Overridden method of DoubleConsumerWithThrowable that will call acceptWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     */
    @Override
    default void accept(final double v1) {
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
    void acceptWithThrowable(final double v1) throws E;


    /**
     * @return An interface that completely ignores exceptions. Consider using this method withLogging() as well.
     */
    default DoubleConsumer thatThrowsNothing() {
        return (final double v1) -> {
            try {
                acceptWithThrowable(v1);
            } catch(Throwable ignored) {}
        };
    }


    /**
     * @throws E if an exception E has been thrown, it is rethrown by this method
     * @return An interface that is only returned if no exception has been thrown.
     */
    default DoubleConsumer thatUnsafelyThrowsUnchecked() throws E {
        return (final double v1) -> {
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
    default DoubleConsumerWithThrowable<E> withLogging(final Logger logger, final String message) {
        return (final double v1) -> {
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
    default DoubleConsumerWithThrowable<E> withLogging(final Logger logger) {
        return withLogging(logger, "Exception in DoubleConsumerWithThrowable with the argument [{}]");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default DoubleConsumerWithThrowable<E> withLogging() {
        return withLogging(LoggerFactory.getLogger(getClass()));
    }



    /**
     * @param consumer An exception consumer.
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default DoubleConsumerWithThrowable<E> onException(final Consumer<Throwable> consumer) {
        return (final double v1) -> {
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
    default DoubleConsumerWithThrowable<E> onException(final java.util.function.BiConsumer<Throwable, Object[]> consumer) {
        return (final double v1) -> {
            try {
                acceptWithThrowable(v1);
            } catch (final Throwable throwable) {
                consumer.accept(throwable, new Object[]{v1});
                throw throwable;
            }
        };
    }
}
