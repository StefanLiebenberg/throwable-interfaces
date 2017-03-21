package org.slieb.throwables;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;
import java.util.function.ToIntFunction;

/**
 * Generated from ToIntFunction
 * Extends java.util.function.ToIntFunction to allow for a checked exception.
 *
 * @param <T> some generic flag
 * @param <E> The extension
 */
@FunctionalInterface
@SuppressWarnings({"WeakerAccess"})
public interface ToIntFunctionWithThrowable<T, E extends Throwable> extends ToIntFunction<T> {

    /**
     * Utility method to mark lambdas of type ToIntFunctionWithThrowable
     *
     * @param tointfunctionwiththrowable The interface instance
     * @param <T>                        Generic that corresponds to the same generic on ToIntFunction
     * @param <E>                        The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, E extends Throwable> ToIntFunctionWithThrowable<T, E> castToIntFunctionWithThrowable(final ToIntFunctionWithThrowable<T, E>
                                                                                                            tointfunctionwiththrowable) {
        return tointfunctionwiththrowable;
    }

    /**
     * Utility method to convert ToIntFunctionWithThrowable
     *
     * @param tointfunction The interface instance
     * @param <T>           Generic that corresponds to the same generic on ToIntFunction
     * @param <E>           The type this interface is allowed to throw
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
     * @return An interface that will wrap the result in an optional, and return an empty optional when an exception occurs.
     */
    default java.util.function.Function<T, java.util.OptionalInt> thatReturnsOptional() {
        return (v1) -> {
            try {
                return java.util.OptionalInt.of(applyAsIntWithThrowable(v1));
            } catch (Throwable throwable) {
                return java.util.OptionalInt.empty();
            }
        };
    }

    /**
     * @param defaultReturnValue A value to return if any throwable is caught.
     * @return An interface that returns a default value if any exception occurs.
     */
    default ToIntFunction<T> thatReturnsOnCatch(final int defaultReturnValue) {
        return (final T v1) -> {
            try {
                return applyAsIntWithThrowable(v1);
            } catch (final Throwable throwable) {
                return defaultReturnValue;
            }
        };
    }

    /**
     * @param logger  The logger to log exceptions on
     * @param message A message to use for logging exceptions
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default ToIntFunctionWithThrowable<T, E> withLogging(final Logger logger, final String message) {
        return (final T v1) -> {
            try {
                return applyAsIntWithThrowable(v1);
            } catch (final Throwable throwable) {
                logger.error(message, v1, throwable);
                throw throwable;
            }
        };
    }

    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     *
     * @param logger The logger instance to log exceptions on
     * @return An interface that will log exceptions on given logger
     */
    default ToIntFunctionWithThrowable<T, E> withLogging(final Logger logger) {
        return withLogging(logger, "Exception in ToIntFunctionWithThrowable with the argument [{}]");
    }

    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     *
     * @return An interface that will log exceptions on global logger
     */
    default ToIntFunctionWithThrowable<T, E> withLogging() {
        return withLogging(LoggerFactory.getLogger(getClass()));
    }

    /**
     * @param consumer An exception consumer.
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default ToIntFunctionWithThrowable<T, E> onException(final Consumer<Throwable> consumer) {
        return (final T v1) -> {
            try {
                return applyAsIntWithThrowable(v1);
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
    default ToIntFunctionWithThrowable<T, E> onException(final java.util.function.BiConsumer<Throwable, Object[]> consumer) {
        return (final T v1) -> {
            try {
                return applyAsIntWithThrowable(v1);
            } catch (final Throwable throwable) {
                consumer.accept(throwable, new Object[]{v1});
                throw throwable;
            }
        };
    }
}
