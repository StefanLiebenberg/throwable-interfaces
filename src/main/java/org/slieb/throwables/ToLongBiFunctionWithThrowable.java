package org.slieb.throwables;

import java.lang.Throwable;
import java.util.function.Consumer;
import java.util.function.ToLongBiFunction;
import org.slf4j.Logger;
/**
 * Generated from ToLongBiFunction
 * Extends java.util.function.ToLongBiFunction to allow for a checked exception.
 *
 * @param <T> some generic flag
 * @param <U> some generic flag
 * @param <E> The extension
 */
@FunctionalInterface
public interface ToLongBiFunctionWithThrowable<T, U, E extends Throwable> extends ToLongBiFunction<T, U> {


    /**
     * Utility method to mark lambdas of type ToLongBiFunctionWithThrowable
     *
     * @param tolongbifunctionwiththrowable The interface instance
     * @param <T> Generic that corresponds to the same generic on ToLongBiFunction  
     * @param <U> Generic that corresponds to the same generic on ToLongBiFunction  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, U, E extends Throwable> ToLongBiFunctionWithThrowable<T, U, E> castToLongBiFunctionWithThrowable(final ToLongBiFunctionWithThrowable<T, U, E> tolongbifunctionwiththrowable) {
        return tolongbifunctionwiththrowable;
    }
    /**
     * Utility method to convert ToLongBiFunctionWithThrowable
     * @param tolongbifunction The interface instance
     * @param <T> Generic that corresponds to the same generic on ToLongBiFunction  
     * @param <U> Generic that corresponds to the same generic on ToLongBiFunction  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, U, E extends Throwable> ToLongBiFunctionWithThrowable<T, U, E> asToLongBiFunctionWithThrowable(final ToLongBiFunction<T, U> tolongbifunction) {
        return tolongbifunction::applyAsLong;
    }

    /** 
     * Overridden method of ToLongBiFunctionWithThrowable that will call applyAsLongWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @param v2 parameter to overridden method
     * @return the value
     */
    @Override
    default long applyAsLong(final T v1, final U v2) {
        try {
            return applyAsLongWithThrowable(v1, v2);
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
    long applyAsLongWithThrowable(final T v1, final U v2) throws E;


    /**
     * @param logger The logger to log exceptions on
     * @param message A message to use for logging exceptions
     * @return An interface that will log all exceptions to given logger
     */
    default ToLongBiFunctionWithThrowable<T, U, E> withLogging(Logger logger, String message) {
        return (final T v1, final U v2) -> {
            try {
                return applyAsLongWithThrowable(v1, v2);
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
    default ToLongBiFunctionWithThrowable<T, U, E> withLogging(org.slf4j.Logger logger) {
        return withLogging(logger, "Exception in ToLongBiFunctionWithThrowable with arguments {} {}");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default ToLongBiFunctionWithThrowable<T, U, E> withLogging() {
        return withLogging(org.slf4j.LoggerFactory.getLogger(getClass()));
    }



    /**
     * @param consumer An exception consumer.
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default ToLongBiFunctionWithThrowable<T, U, E> onException(Consumer<Throwable> consumer) {
        return (final T v1, final U v2) -> {
            try {
                return applyAsLongWithThrowable(v1, v2);
            } catch (final Throwable throwable) {
                consumer.accept(throwable);
                throw throwable;
            }
        };
    }
}
