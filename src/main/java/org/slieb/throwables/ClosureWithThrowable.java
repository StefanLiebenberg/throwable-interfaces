package org.slieb.throwables;

import java.lang.Throwable;
import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Generated from Closure
 * Extends org.slieb.throwables.Closure to allow for a checked exception.
 *
 * @param <E> The extension
 */
@FunctionalInterface
public interface ClosureWithThrowable<E extends Throwable> extends Closure {


    /**
     * Utility method to mark lambdas of type ClosureWithThrowable
     *
     * @param closurewiththrowable The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> ClosureWithThrowable<E> castClosureWithThrowable(final ClosureWithThrowable<E> closurewiththrowable) {
        return closurewiththrowable;
    }
    /**
     * Utility method to convert ClosureWithThrowable
     * @param closure The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> ClosureWithThrowable<E> asClosureWithThrowable(final Closure closure) {
        return closure::call;
    }

    /** 
     * Overridden method of ClosureWithThrowable that will call callWithThrowable, but catching any exceptions.
     *
     */
    @Override
    default void call() {
        try {
            callWithThrowable();
        } catch (final RuntimeException | Error exception) {
            throw exception;
        } catch (final Throwable throwable) {
            throw new SuppressedException(throwable);
        }
    }

    /** 
     * Functional method that will throw exceptions.
     *
     * @throws E some exception
     */
    void callWithThrowable() throws E;


    /**
     * @return An interface that completely ignores exceptions. Consider using this method withLogging() as well.
     */
    default Closure thatThrowsNothing() {
        return () -> {
            try {
                callWithThrowable();
            } catch(Throwable ignored) {}
        };
    }


    /**
     * @param logger The logger to log exceptions on
     * @param message A message to use for logging exceptions
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default ClosureWithThrowable<E> withLogging(final Logger logger, final String message) {
        return () -> {
            try {
                callWithThrowable();
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
    default ClosureWithThrowable<E> withLogging(final Logger logger) {
        return withLogging(logger, "Exception in ClosureWithThrowable");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default ClosureWithThrowable<E> withLogging() {
        return withLogging(LoggerFactory.getLogger(getClass()));
    }



    /**
     * @param consumer An exception consumer.
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default ClosureWithThrowable<E> onException(final Consumer<Throwable> consumer) {
        return () -> {
            try {
                callWithThrowable();
            } catch (final Throwable throwable) {
                consumer.accept(throwable);
                throw throwable;
            }
        };
    }
}
