package org.slieb.throwables;

import java.lang.Throwable;
import java.util.function.Consumer;
import java.util.function.LongBinaryOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Generated from LongBinaryOperator
 * Extends java.util.function.LongBinaryOperator to allow for a checked exception.
 *
 * @param <E> The extension
 */
@FunctionalInterface
public interface LongBinaryOperatorWithThrowable<E extends Throwable> extends LongBinaryOperator {

    /**
     * Utility method to mark lambdas of type LongBinaryOperatorWithThrowable
     *
     * @param longbinaryoperatorwiththrowable The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> LongBinaryOperatorWithThrowable<E> castLongBinaryOperatorWithThrowable(final LongBinaryOperatorWithThrowable<E> longbinaryoperatorwiththrowable) {
        return longbinaryoperatorwiththrowable;
    }

    /**
     * Utility method to convert LongBinaryOperatorWithThrowable
     * @param longbinaryoperator The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> LongBinaryOperatorWithThrowable<E> asLongBinaryOperatorWithThrowable(final LongBinaryOperator longbinaryoperator) {
        return longbinaryoperator::applyAsLong;
    }

    /** 
     * Overridden method of LongBinaryOperatorWithThrowable that will call applyAsLongWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @param v2 parameter to overridden method
     * @return the value
     */
    @Override
    default long applyAsLong(final long v1, final long v2) {
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
    long applyAsLongWithThrowable(final long v1, final long v2) throws E;


    /**
     * @return An interface that will wrap the result in an optional, and return an empty optional when an exception occurs.
     */


    /**
     * @param logger The logger to log exceptions on
     * @param message A message to use for logging exceptions
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default LongBinaryOperatorWithThrowable<E> withLogging(final Logger logger, final String message) {
        return (final long v1, final long v2) -> {
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
    default LongBinaryOperatorWithThrowable<E> withLogging(final Logger logger) {
        return withLogging(logger, "Exception in LongBinaryOperatorWithThrowable");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default LongBinaryOperatorWithThrowable<E> withLogging() {
        return withLogging(LoggerFactory.getLogger(getClass()));
    }



    /**
     * @param consumer An exception consumer.
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default LongBinaryOperatorWithThrowable<E> onException(final Consumer<Throwable> consumer) {
        return (final long v1, final long v2) -> {
            try {
                return applyAsLongWithThrowable(v1, v2);
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
    default LongBinaryOperatorWithThrowable<E> onException(final java.util.function.BiConsumer<Throwable, Object[]> consumer) {
        return (final long v1, final long v2) -> {
            try {
                return applyAsLongWithThrowable(v1, v2);
            } catch (final Throwable throwable) {
                consumer.accept(throwable, new Object[]{v1, v2});
                throw throwable;
            }
        };
    }
}
