package org.slieb.throwables;

import java.lang.Throwable;
import java.util.function.Consumer;
import java.util.function.DoubleUnaryOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Generated from DoubleUnaryOperator
 * Extends java.util.function.DoubleUnaryOperator to allow for a checked exception.
 *
 * @param <E> The extension
 */
@FunctionalInterface
public interface DoubleUnaryOperatorWithThrowable<E extends Throwable> extends DoubleUnaryOperator {

    /**
     * Utility method to mark lambdas of type DoubleUnaryOperatorWithThrowable
     *
     * @param doubleunaryoperatorwiththrowable The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> DoubleUnaryOperatorWithThrowable<E> castDoubleUnaryOperatorWithThrowable(final DoubleUnaryOperatorWithThrowable<E> doubleunaryoperatorwiththrowable) {
        return doubleunaryoperatorwiththrowable;
    }
    /**
     * Utility method to convert DoubleUnaryOperatorWithThrowable
     * @param doubleunaryoperator The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> DoubleUnaryOperatorWithThrowable<E> asDoubleUnaryOperatorWithThrowable(final DoubleUnaryOperator doubleunaryoperator) {
        return doubleunaryoperator::applyAsDouble;
    }

    /** 
     * Overridden method of DoubleUnaryOperatorWithThrowable that will call applyAsDoubleWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @return the value
     */
    @Override
    default double applyAsDouble(final double v1) {
        try {
            return applyAsDoubleWithThrowable(v1);
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
    double applyAsDoubleWithThrowable(final double v1) throws E;


    /**
     * @param logger The logger to log exceptions on
     * @param message A message to use for logging exceptions
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default DoubleUnaryOperatorWithThrowable<E> withLogging(final Logger logger, final String message) {
        return (final double v1) -> {
            try {
                return applyAsDoubleWithThrowable(v1);
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
    default DoubleUnaryOperatorWithThrowable<E> withLogging(final Logger logger) {
        return withLogging(logger, "Exception in DoubleUnaryOperatorWithThrowable");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default DoubleUnaryOperatorWithThrowable<E> withLogging() {
        return withLogging(LoggerFactory.getLogger(getClass()));
    }



    /**
     * @param consumer An exception consumer.
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default DoubleUnaryOperatorWithThrowable<E> onException(final Consumer<Throwable> consumer) {
        return (final double v1) -> {
            try {
                return applyAsDoubleWithThrowable(v1);
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
    default DoubleUnaryOperatorWithThrowable<E> onException(final java.util.function.BiConsumer<Throwable, Object[]> consumer) {
        return (final double v1) -> {
            try {
                return applyAsDoubleWithThrowable(v1);
            } catch (final Throwable throwable) {
                consumer.accept(throwable, new Object[]{v1});
                throw throwable;
            }
        };
    }
}
