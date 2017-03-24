package org.slieb.throwables;

import java.lang.FunctionalInterface;
import java.lang.SuppressWarnings;
import java.lang.Throwable;
import java.util.function.Consumer;
import java.util.function.ObjDoubleConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Generated from ObjDoubleConsumer
 * Extends java.util.function.ObjDoubleConsumer to allow for a checked exception.
 *
 * @param <T> some generic flag
 * @param <E> The extension
 */
@FunctionalInterface
@SuppressWarnings({"WeakerAccess"})
public interface ObjDoubleConsumerWithThrowable<T, E extends Throwable> extends ObjDoubleConsumer<T> {

    /**
     * Utility method to mark lambdas of type ObjDoubleConsumerWithThrowable
     *
     * @param objdoubleconsumerwiththrowable The interface instance
     * @param <T> Generic that corresponds to the same generic on ObjDoubleConsumer  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, E extends Throwable> ObjDoubleConsumerWithThrowable<T, E> castObjDoubleConsumerWithThrowable(final ObjDoubleConsumerWithThrowable<T, E> objdoubleconsumerwiththrowable) {
        return objdoubleconsumerwiththrowable;
    }

    /**
     * Utility method to unwrap lambdas of type ObjDoubleConsumer and withUncheckedThrowable any Exception
     *
     * @param objdoubleconsumerwiththrowable The interface instance
     * @param <T> Generic that corresponds to the same generic on ObjDoubleConsumer  
     * @param <E> The type this interface is allowed to throw
     * @throws E the original Exception from objdoubleconsumerwiththrowable
     * @return the cast interface
     */
    static <T, E extends Throwable> ObjDoubleConsumer<T> aObjDoubleConsumerThatUnsafelyThrowsUnchecked(final ObjDoubleConsumerWithThrowable<T, E> objdoubleconsumerwiththrowable) throws E {
        return objdoubleconsumerwiththrowable.thatUnsafelyThrowsUnchecked();
    }

    /**
     * Utility method to convert ObjDoubleConsumerWithThrowable
     * @param objdoubleconsumer The interface instance
     * @param <T> Generic that corresponds to the same generic on ObjDoubleConsumer  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, E extends Throwable> ObjDoubleConsumerWithThrowable<T, E> asObjDoubleConsumerWithThrowable(final ObjDoubleConsumer<T> objdoubleconsumer) {
        return objdoubleconsumer::accept;
    }

    /**
     * Overridden method of ObjDoubleConsumerWithThrowable that will call acceptWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @param v2 parameter to overridden method
     */
    @Override
    default void accept(final T v1, final double v2) {
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
    void acceptWithThrowable(final T v1, final double v2) throws E;


    /**
     * @return An interface that completely ignores exceptions. Consider using this method withLogging() as well.
     */
    default ObjDoubleConsumer<T> thatThrowsNothing() {
        return (final T v1, final double v2) -> {
            try {
                acceptWithThrowable(v1, v2);
            } catch(Throwable ignored) {}
        };
    }


    /**
     * @throws E if an exception E has been thrown, it is rethrown by this method
     * @return An interface that is only returned if no exception has been thrown.
     */
    default ObjDoubleConsumer<T> thatUnsafelyThrowsUnchecked() throws E {
        return (final T v1, final double v2) -> {
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
    default ObjDoubleConsumerWithThrowable<T, E> withLogging(final Logger logger, final String message) {
        return (final T v1, final double v2) -> {
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
    default ObjDoubleConsumerWithThrowable<T, E> withLogging(final Logger logger) {
        return withLogging(logger, "Exception in ObjDoubleConsumerWithThrowable with the arguments [{}, {}]");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default ObjDoubleConsumerWithThrowable<T, E> withLogging() {
        return withLogging(LoggerFactory.getLogger(getClass()));
    }



    /**
     * @param consumer An exception consumer.
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default ObjDoubleConsumerWithThrowable<T, E> onException(final Consumer<Throwable> consumer) {
        return (final T v1, final double v2) -> {
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
    default ObjDoubleConsumerWithThrowable<T, E> onException(final java.util.function.BiConsumer<Throwable, Object[]> consumer) {
        return (final T v1, final double v2) -> {
            try {
                acceptWithThrowable(v1, v2);
            } catch (final Throwable throwable) {
                consumer.accept(throwable, new Object[]{v1, v2});
                throw throwable;
            }
        };
    }
}
