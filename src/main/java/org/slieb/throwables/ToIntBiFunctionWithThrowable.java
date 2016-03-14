package org.slieb.throwables;

import java.lang.Throwable;
import java.util.function.Consumer;
import java.util.function.ToIntBiFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Generated from ToIntBiFunction
 * Extends java.util.function.ToIntBiFunction to allow for a checked exception.
 *
 * @param <T> some generic flag
 * @param <U> some generic flag
 * @param <E> The extension
 */
@FunctionalInterface
public interface ToIntBiFunctionWithThrowable<T, U, E extends Throwable> extends ToIntBiFunction<T, U> {

    /**
     * Utility method to mark lambdas of type ToIntBiFunctionWithThrowable
     *
     * @param tointbifunctionwiththrowable The interface instance
     * @param <T> Generic that corresponds to the same generic on ToIntBiFunction  
     * @param <U> Generic that corresponds to the same generic on ToIntBiFunction  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, U, E extends Throwable> ToIntBiFunctionWithThrowable<T, U, E> castToIntBiFunctionWithThrowable(final ToIntBiFunctionWithThrowable<T, U, E> tointbifunctionwiththrowable) {
        return tointbifunctionwiththrowable;
    }
    /**
     * Utility method to convert ToIntBiFunctionWithThrowable
     * @param tointbifunction The interface instance
     * @param <T> Generic that corresponds to the same generic on ToIntBiFunction  
     * @param <U> Generic that corresponds to the same generic on ToIntBiFunction  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, U, E extends Throwable> ToIntBiFunctionWithThrowable<T, U, E> asToIntBiFunctionWithThrowable(final ToIntBiFunction<T, U> tointbifunction) {
        return tointbifunction::applyAsInt;
    }

    /** 
     * Overridden method of ToIntBiFunctionWithThrowable that will call applyAsIntWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @param v2 parameter to overridden method
     * @return the value
     */
    @Override
    default int applyAsInt(final T v1, final U v2) {
        try {
            return applyAsIntWithThrowable(v1, v2);
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
     * @return the value
     * @throws E some exception
     */
    int applyAsIntWithThrowable(final T v1, final U v2) throws E;


    /**
     * @param logger The logger to log exceptions on
     * @param message A message to use for logging exceptions
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default ToIntBiFunctionWithThrowable<T, U, E> withLogging(final Logger logger, final String message) {
        return (final T v1, final U v2) -> {
            try {
                return applyAsIntWithThrowable(v1, v2);
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
    default ToIntBiFunctionWithThrowable<T, U, E> withLogging(final Logger logger) {
        return withLogging(logger, "Exception in ToIntBiFunctionWithThrowable");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default ToIntBiFunctionWithThrowable<T, U, E> withLogging() {
        return withLogging(LoggerFactory.getLogger(getClass()));
    }



    /**
     * @param consumer An exception consumer.
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default ToIntBiFunctionWithThrowable<T, U, E> onException(final Consumer<Throwable> consumer) {
        return (final T v1, final U v2) -> {
            try {
                return applyAsIntWithThrowable(v1, v2);
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
    default ToIntBiFunctionWithThrowable<T, U, E> onException(final java.util.function.BiConsumer<Throwable, Object[]> consumer) {
        return (final T v1, final U v2) -> {
            try {
                return applyAsIntWithThrowable(v1, v2);
            } catch (final Throwable throwable) {
                consumer.accept(throwable, new Object[]{v1, v2});
                throw throwable;
            }
        };
    }
}
