package org.slieb.throwables;

import java.lang.Throwable;
import java.util.function.Consumer;
import java.util.function.ToDoubleBiFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Generated from ToDoubleBiFunction
 * Extends java.util.function.ToDoubleBiFunction to allow for a checked exception.
 *
 * @param <T> some generic flag
 * @param <U> some generic flag
 * @param <E> The extension
 */
@FunctionalInterface
public interface ToDoubleBiFunctionWithThrowable<T, U, E extends Throwable> extends ToDoubleBiFunction<T, U> {

    /**
     * Utility method to mark lambdas of type ToDoubleBiFunctionWithThrowable
     *
     * @param todoublebifunctionwiththrowable The interface instance
     * @param <T> Generic that corresponds to the same generic on ToDoubleBiFunction  
     * @param <U> Generic that corresponds to the same generic on ToDoubleBiFunction  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, U, E extends Throwable> ToDoubleBiFunctionWithThrowable<T, U, E> castToDoubleBiFunctionWithThrowable(final ToDoubleBiFunctionWithThrowable<T, U, E> todoublebifunctionwiththrowable) {
        return todoublebifunctionwiththrowable;
    }
    /**
     * Utility method to convert ToDoubleBiFunctionWithThrowable
     * @param todoublebifunction The interface instance
     * @param <T> Generic that corresponds to the same generic on ToDoubleBiFunction  
     * @param <U> Generic that corresponds to the same generic on ToDoubleBiFunction  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, U, E extends Throwable> ToDoubleBiFunctionWithThrowable<T, U, E> asToDoubleBiFunctionWithThrowable(final ToDoubleBiFunction<T, U> todoublebifunction) {
        return todoublebifunction::applyAsDouble;
    }

    /** 
     * Overridden method of ToDoubleBiFunctionWithThrowable that will call applyAsDoubleWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @param v2 parameter to overridden method
     * @return the value
     */
    @Override
    default double applyAsDouble(final T v1, final U v2) {
        try {
            return applyAsDoubleWithThrowable(v1, v2);
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
    double applyAsDoubleWithThrowable(final T v1, final U v2) throws E;


    /**
     * @param logger The logger to log exceptions on
     * @param message A message to use for logging exceptions
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default ToDoubleBiFunctionWithThrowable<T, U, E> withLogging(final Logger logger, final String message) {
        return (final T v1, final U v2) -> {
            try {
                return applyAsDoubleWithThrowable(v1, v2);
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
    default ToDoubleBiFunctionWithThrowable<T, U, E> withLogging(final Logger logger) {
        return withLogging(logger, "Exception in ToDoubleBiFunctionWithThrowable");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default ToDoubleBiFunctionWithThrowable<T, U, E> withLogging() {
        return withLogging(LoggerFactory.getLogger(getClass()));
    }



    /**
     * @param consumer An exception consumer.
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default ToDoubleBiFunctionWithThrowable<T, U, E> onException(final Consumer<Throwable> consumer) {
        return (final T v1, final U v2) -> {
            try {
                return applyAsDoubleWithThrowable(v1, v2);
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
    default ToDoubleBiFunctionWithThrowable<T, U, E> onException(final java.util.function.BiConsumer<Throwable, Object[]> consumer) {
        return (final T v1, final U v2) -> {
            try {
                return applyAsDoubleWithThrowable(v1, v2);
            } catch (final Throwable throwable) {
                consumer.accept(throwable, new Object[]{v1, v2});
                throw throwable;
            }
        };
    }
}
