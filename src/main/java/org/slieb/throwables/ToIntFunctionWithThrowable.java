package org.slieb.throwables;

import java.lang.Throwable;
import java.util.function.Consumer;
import java.util.function.ToIntFunction;
import org.slf4j.Logger;
/**
 * Generated from ToIntFunction
 * Extends java.util.function.ToIntFunction to allow for a checked exception.
 *
 * @param <T> some generic flag
 * @param <E> The extension
 */
@FunctionalInterface
public interface ToIntFunctionWithThrowable<T, E extends Throwable> extends ToIntFunction<T> {


    /**
     * Utility method to mark lambdas of type ToIntFunctionWithThrowable
     *
     * @param tointfunctionwiththrowable The interface instance
     * @param <T> Generic that corresponds to the same generic on ToIntFunction  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, E extends Throwable> ToIntFunctionWithThrowable<T, E> castToIntFunctionWithThrowable(final ToIntFunctionWithThrowable<T, E> tointfunctionwiththrowable) {
        return tointfunctionwiththrowable;
    }
    /**
     * Utility method to convert ToIntFunctionWithThrowable
     * @param tointfunction The interface instance
     * @param <T> Generic that corresponds to the same generic on ToIntFunction  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, E extends Throwable> ToIntFunctionWithThrowable<T, E> asToIntFunctionWithThrowable(final ToIntFunction<T> tointfunction) {
        return tointfunction::applyAsInt;
    }

    /** 
     * Overridden method of ToIntFunctionWithThrowable that will call applyAsIntWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @return the value
     */
    @Override
    default int applyAsInt(final T v1) {
        try {
            return applyAsIntWithThrowable(v1);
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
     * @return the value
     * @throws E some exception
     */
    int applyAsIntWithThrowable(final T v1) throws E;


    /**
     * @param logger The logger to log exceptions on
     * @param message A message to use for logging exceptions
     * @return An interface that will log all exceptions to given logger
     */
    default ToIntFunctionWithThrowable<T, E> withLogging(Logger logger, String message) {
        return (final T v1) -> {
            try {
                return applyAsIntWithThrowable(v1);
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
    default ToIntFunctionWithThrowable<T, E> withLogging(org.slf4j.Logger logger) {
        return withLogging(logger, "Exception in ToIntFunctionWithThrowable with arguments {}");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default ToIntFunctionWithThrowable<T, E> withLogging() {
        return withLogging(org.slf4j.LoggerFactory.getLogger(getClass()));
    }



    /**
     * @param consumer An exception consumer.
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default ToIntFunctionWithThrowable<T, E> onException(Consumer<Throwable> consumer) {
        return (final T v1) -> {
            try {
                return applyAsIntWithThrowable(v1);
            } catch (final Throwable throwable) {
                consumer.accept(throwable);
                throw throwable;
            }
        };
    }
}
