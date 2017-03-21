package org.slieb.throwables;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;
import java.util.function.DoubleToIntFunction;

/**
 * Generated from DoubleToIntFunction
 * Extends java.util.function.DoubleToIntFunction to allow for a checked exception.
 *
 * @param <E> The extension
 */
@FunctionalInterface
@SuppressWarnings({"WeakerAccess"})
public interface DoubleToIntFunctionWithThrowable<E extends Throwable> extends DoubleToIntFunction {

    /**
     * Utility method to mark lambdas of type DoubleToIntFunctionWithThrowable
     *
     * @param doubletointfunctionwiththrowable The interface instance
     * @param <E>                              The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> DoubleToIntFunctionWithThrowable<E> castDoubleToIntFunctionWithThrowable(final DoubleToIntFunctionWithThrowable<E>
                                                                                                                  doubletointfunctionwiththrowable) {
        return doubletointfunctionwiththrowable;
    }

    /**
     * Utility method to convert DoubleToIntFunctionWithThrowable
     *
     * @param doubletointfunction The interface instance
     * @param <E>                 The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> DoubleToIntFunctionWithThrowable<E> asDoubleToIntFunctionWithThrowable(final DoubleToIntFunction doubletointfunction) {
        return doubletointfunction::applyAsInt;
    }

    /**
     * Overridden method of DoubleToIntFunctionWithThrowable that will call applyAsIntWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @return the value
     */
    @Override
    default int applyAsInt(final double v1) {
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
    int applyAsIntWithThrowable(final double v1) throws E;

    /**
     * @param defaultReturnValue A value to return if any throwable is caught.
     * @return An interface that returns a default value if any exception occurs.
     */
    default DoubleToIntFunction thatReturnsOnCatch(final int defaultReturnValue) {
        return (final double v1) -> {
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
    default DoubleToIntFunctionWithThrowable<E> withLogging(final Logger logger, final String message) {
        return (final double v1) -> {
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
    default DoubleToIntFunctionWithThrowable<E> withLogging(final Logger logger) {
        return withLogging(logger, "Exception in DoubleToIntFunctionWithThrowable with the argument [{}]");
    }

    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     *
     * @return An interface that will log exceptions on global logger
     */
    default DoubleToIntFunctionWithThrowable<E> withLogging() {
        return withLogging(LoggerFactory.getLogger(getClass()));
    }

    /**
     * @param consumer An exception consumer.
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default DoubleToIntFunctionWithThrowable<E> onException(final Consumer<Throwable> consumer) {
        return (final double v1) -> {
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
    default DoubleToIntFunctionWithThrowable<E> onException(final java.util.function.BiConsumer<Throwable, Object[]> consumer) {
        return (final double v1) -> {
            try {
                return applyAsIntWithThrowable(v1);
            } catch (final Throwable throwable) {
                consumer.accept(throwable, new Object[]{v1});
                throw throwable;
            }
        };
    }
}
