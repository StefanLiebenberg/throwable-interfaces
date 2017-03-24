package org.slieb.throwables;

import java.lang.FunctionalInterface;
import java.lang.SuppressWarnings;
import java.lang.Throwable;
import java.util.function.Consumer;
import java.util.function.ObjIntConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Generated from ObjIntConsumer
 * Extends java.util.function.ObjIntConsumer to allow for a checked exception.
 *
 * @param <T> some generic flag
 * @param <E> The extension
 */
@FunctionalInterface
@SuppressWarnings({"WeakerAccess"})
public interface ObjIntConsumerWithThrowable<T, E extends Throwable> extends ObjIntConsumer<T> {

    /**
     * Utility method to mark lambdas of type ObjIntConsumerWithThrowable
     *
     * @param objintconsumerwiththrowable The interface instance
     * @param <T> Generic that corresponds to the same generic on ObjIntConsumer  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, E extends Throwable> ObjIntConsumerWithThrowable<T, E> castObjIntConsumerWithThrowable(final ObjIntConsumerWithThrowable<T, E> objintconsumerwiththrowable) {
        return objintconsumerwiththrowable;
    }

    /**
     * Utility method to unwrap lambdas of type ObjIntConsumer and withUncheckedThrowable any Exception
     *
     * @param objintconsumerwiththrowable The interface instance
     * @param <T> Generic that corresponds to the same generic on ObjIntConsumer  
     * @param <E> The type this interface is allowed to throw
     * @throws E the original Exception from objintconsumerwiththrowable
     * @return the cast interface
     */
    static <T, E extends Throwable> ObjIntConsumer<T> aObjIntConsumerThatUnsafelyThrowsUnchecked(final ObjIntConsumerWithThrowable<T, E> objintconsumerwiththrowable) throws E {
        return objintconsumerwiththrowable.thatUnsafelyThrowsUnchecked();
    }

    /**
     * Utility method to convert ObjIntConsumerWithThrowable
     * @param objintconsumer The interface instance
     * @param <T> Generic that corresponds to the same generic on ObjIntConsumer  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, E extends Throwable> ObjIntConsumerWithThrowable<T, E> asObjIntConsumerWithThrowable(final ObjIntConsumer<T> objintconsumer) {
        return objintconsumer::accept;
    }

    /**
     * Overridden method of ObjIntConsumerWithThrowable that will call acceptWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @param v2 parameter to overridden method
     */
    @Override
    default void accept(final T v1, final int v2) {
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
    void acceptWithThrowable(final T v1, final int v2) throws E;


    /**
     * @return An interface that completely ignores exceptions. Consider using this method withLogging() as well.
     */
    default ObjIntConsumer<T> thatThrowsNothing() {
        return (final T v1, final int v2) -> {
            try {
                acceptWithThrowable(v1, v2);
            } catch(Throwable ignored) {}
        };
    }


    /**
     * @throws E if an exception E has been thrown, it is rethrown by this method
     * @return An interface that is only returned if no exception has been thrown.
     */
    default ObjIntConsumer<T> thatUnsafelyThrowsUnchecked() throws E {
        return (final T v1, final int v2) -> {
            try {
                acceptWithThrowable(v1, v2);
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
    default ObjIntConsumerWithThrowable<T, E> withLogging(final Logger logger, final String message) {
        return (final T v1, final int v2) -> {
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
     * @param logger The logger instance to log exceptions on
     * @return An interface that will log exceptions on given logger
     */
    default ObjIntConsumerWithThrowable<T, E> withLogging(final Logger logger) {
        return withLogging(logger, "Exception in ObjIntConsumerWithThrowable with the arguments [{}, {}]");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default ObjIntConsumerWithThrowable<T, E> withLogging() {
        return withLogging(LoggerFactory.getLogger(getClass()));
    }



    /**
     * @param consumer An exception consumer.
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default ObjIntConsumerWithThrowable<T, E> onException(final Consumer<Throwable> consumer) {
        return (final T v1, final int v2) -> {
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
    default ObjIntConsumerWithThrowable<T, E> onException(final java.util.function.BiConsumer<Throwable, Object[]> consumer) {
        return (final T v1, final int v2) -> {
            try {
                acceptWithThrowable(v1, v2);
            } catch (final Throwable throwable) {
                consumer.accept(throwable, new Object[]{v1, v2});
                throw throwable;
            }
        };
    }
}
