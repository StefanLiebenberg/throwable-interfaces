package org.slieb.throwables;

import java.lang.FunctionalInterface;
import java.lang.SuppressWarnings;
import java.lang.Throwable;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Generated from IntConsumer
 * Extends java.util.function.IntConsumer to allow for a checked exception.
 *
 * @param <E> The extension
 */
@FunctionalInterface
@SuppressWarnings({"WeakerAccess"})
public interface IntConsumerWithThrowable<E extends Throwable> extends IntConsumer {

    /**
     * Utility method to mark lambdas of type IntConsumerWithThrowable
     *
     * @param intconsumerwiththrowable The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> IntConsumerWithThrowable<E> castIntConsumerWithThrowable(final IntConsumerWithThrowable<E> intconsumerwiththrowable) {
        return intconsumerwiththrowable;
    }

    /**
     * Utility method to unwrap lambdas of type IntConsumer and withUncheckedThrowable any Exception
     *
     * @param intconsumerwiththrowable The interface instance
     * @param <E> The type this interface is allowed to throw
     * @throws E the original Exception from intconsumerwiththrowable
     * @return the cast interface
     */
    static <E extends Throwable> IntConsumer aIntConsumerThatUnsafelyThrowsUnchecked(final IntConsumerWithThrowable<E> intconsumerwiththrowable) throws E {
        return intconsumerwiththrowable.thatUnsafelyThrowsUnchecked();
    }

    /**
     * Utility method to convert IntConsumerWithThrowable
     * @param intconsumer The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> IntConsumerWithThrowable<E> asIntConsumerWithThrowable(final IntConsumer intconsumer) {
        return intconsumer::accept;
    }

    /**
     * Overridden method of IntConsumerWithThrowable that will call acceptWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     */
    @Override
    default void accept(final int v1) {
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
    void acceptWithThrowable(final int v1) throws E;


    /**
     * @return An interface that completely ignores exceptions. Consider using this method withLogging() as well.
     */
    default IntConsumer thatThrowsNothing() {
        return (final int v1) -> {
            try {
                acceptWithThrowable(v1);
            } catch(Throwable ignored) {}
        };
    }


    /**
     * @throws E if an exception E has been thrown, it is rethrown by this method
     * @return An interface that is only returned if no exception has been thrown.
     */
    default IntConsumer thatUnsafelyThrowsUnchecked() throws E {
        return (final int v1) -> {
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
    default IntConsumerWithThrowable<E> withLogging(final Logger logger, final String message) {
        return (final int v1) -> {
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
    default IntConsumerWithThrowable<E> withLogging(final Logger logger) {
        return withLogging(logger, "Exception in IntConsumerWithThrowable with the argument [{}]");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default IntConsumerWithThrowable<E> withLogging() {
        return withLogging(LoggerFactory.getLogger(getClass()));
    }



    /**
     * @param consumer An exception consumer.
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default IntConsumerWithThrowable<E> onException(final Consumer<Throwable> consumer) {
        return (final int v1) -> {
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
    default IntConsumerWithThrowable<E> onException(final java.util.function.BiConsumer<Throwable, Object[]> consumer) {
        return (final int v1) -> {
            try {
                acceptWithThrowable(v1);
            } catch (final Throwable throwable) {
                consumer.accept(throwable, new Object[]{v1});
                throw throwable;
            }
        };
    }
}
