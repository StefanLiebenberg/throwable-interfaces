package org.slieb.throwables;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;
import java.util.function.DoubleBinaryOperator;

/**
 * Generated from DoubleBinaryOperator
 * Extends java.util.function.DoubleBinaryOperator to allow for a checked exception.
 *
 * @param <E> The extension
 */
@FunctionalInterface
@SuppressWarnings({"WeakerAccess"})
public interface DoubleBinaryOperatorWithThrowable<E extends Throwable> extends DoubleBinaryOperator {

    /**
     * Utility method to mark lambdas of type DoubleBinaryOperatorWithThrowable
     *
     * @param doublebinaryoperatorwiththrowable The interface instance
     * @param <E>                               The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> DoubleBinaryOperatorWithThrowable<E> castDoubleBinaryOperatorWithThrowable(final DoubleBinaryOperatorWithThrowable<E>
                                                                                                                    doublebinaryoperatorwiththrowable) {
        return doublebinaryoperatorwiththrowable;
    }

    /**
     * Utility method to convert DoubleBinaryOperatorWithThrowable
     *
     * @param doublebinaryoperator The interface instance
     * @param <E>                  The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> DoubleBinaryOperatorWithThrowable<E> asDoubleBinaryOperatorWithThrowable(final DoubleBinaryOperator doublebinaryoperator) {
        return doublebinaryoperator::applyAsDouble;
    }

    /**
     * Overridden method of DoubleBinaryOperatorWithThrowable that will call applyAsDoubleWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @param v2 parameter to overridden method
     * @return the value
     */
    @Override
    default double applyAsDouble(final double v1, final double v2) {
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
    double applyAsDoubleWithThrowable(final double v1, final double v2) throws E;

    /**
     * @param defaultReturnValue A value to return if any throwable is caught.
     * @return An interface that returns a default value if any exception occurs.
     */
    default DoubleBinaryOperator thatReturnsOnCatch(final double defaultReturnValue) {
        return (final double v1, final double v2) -> {
            try {
                return applyAsDoubleWithThrowable(v1, v2);
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
    default DoubleBinaryOperatorWithThrowable<E> withLogging(final Logger logger, final String message) {
        return (final double v1, final double v2) -> {
            try {
                return applyAsDoubleWithThrowable(v1, v2);
            } catch (final Throwable throwable) {
                logger.error(message, v1, v2, throwable);
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
    default DoubleBinaryOperatorWithThrowable<E> withLogging(final Logger logger) {
        return withLogging(logger, "Exception in DoubleBinaryOperatorWithThrowable with the arguments [{}, {}]");
    }

    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     *
     * @return An interface that will log exceptions on global logger
     */
    default DoubleBinaryOperatorWithThrowable<E> withLogging() {
        return withLogging(LoggerFactory.getLogger(getClass()));
    }

    /**
     * @param consumer An exception consumer.
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default DoubleBinaryOperatorWithThrowable<E> onException(final Consumer<Throwable> consumer) {
        return (final double v1, final double v2) -> {
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
    default DoubleBinaryOperatorWithThrowable<E> onException(final java.util.function.BiConsumer<Throwable, Object[]> consumer) {
        return (final double v1, final double v2) -> {
            try {
                return applyAsDoubleWithThrowable(v1, v2);
            } catch (final Throwable throwable) {
                consumer.accept(throwable, new Object[]{
                        v1,
                        v2
                });
                throw throwable;
            }
        };
    }
}
