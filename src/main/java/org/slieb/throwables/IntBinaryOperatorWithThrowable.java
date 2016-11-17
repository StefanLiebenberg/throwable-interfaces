package org.slieb.throwables;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;
import java.util.function.IntBinaryOperator;

/**
 * Generated from IntBinaryOperator
 * Extends java.util.function.IntBinaryOperator to allow for a checked exception.
 *
 * @param <E> The extension
 */
@FunctionalInterface
@SuppressWarnings({"WeakerAccess"})
public interface IntBinaryOperatorWithThrowable<E extends Throwable> extends IntBinaryOperator {

    /**
     * Utility method to mark lambdas of type IntBinaryOperatorWithThrowable
     *
     * @param intbinaryoperatorwiththrowable The interface instance
     * @param <E>                            The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> IntBinaryOperatorWithThrowable<E> castIntBinaryOperatorWithThrowable(
            final IntBinaryOperatorWithThrowable<E> intbinaryoperatorwiththrowable) {
        return intbinaryoperatorwiththrowable;
    }

    /**
     * Utility method to convert IntBinaryOperatorWithThrowable
     *
     * @param intbinaryoperator The interface instance
     * @param <E>               The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> IntBinaryOperatorWithThrowable<E> asIntBinaryOperatorWithThrowable(final IntBinaryOperator intbinaryoperator) {
        return intbinaryoperator::applyAsInt;
    }

    /**
     * Overridden method of IntBinaryOperatorWithThrowable that will call applyAsIntWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @param v2 parameter to overridden method
     * @return the value
     */
    @Override
    default int applyAsInt(final int v1, final int v2) {
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
    int applyAsIntWithThrowable(final int v1, final int v2) throws E;

    /**
     * @return An interface that will wrap the result in an optional, and return an empty optional when an exception occurs.
     */

    /**
     * @param logger  The logger to log exceptions on
     * @param message A message to use for logging exceptions
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default IntBinaryOperatorWithThrowable<E> withLogging(final Logger logger, final String message) {
        return (final int v1, final int v2) -> {
            try {
                return applyAsIntWithThrowable(v1, v2);
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
    default IntBinaryOperatorWithThrowable<E> withLogging(final Logger logger) {
        return withLogging(logger, "Exception in IntBinaryOperatorWithThrowable with the arguments [{}, {}]");
    }

    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     *
     * @return An interface that will log exceptions on global logger
     */
    default IntBinaryOperatorWithThrowable<E> withLogging() {
        return withLogging(LoggerFactory.getLogger(getClass()));
    }

    /**
     * @param consumer An exception consumer.
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default IntBinaryOperatorWithThrowable<E> onException(final Consumer<Throwable> consumer) {
        return (final int v1, final int v2) -> {
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
    default IntBinaryOperatorWithThrowable<E> onException(final java.util.function.BiConsumer<Throwable, Object[]> consumer) {
        return (final int v1, final int v2) -> {
            try {
                return applyAsIntWithThrowable(v1, v2);
            } catch (final Throwable throwable) {
                consumer.accept(throwable, new Object[]{v1, v2});
                throw throwable;
            }
        };
    }
}
