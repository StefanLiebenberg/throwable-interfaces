package org.slieb.throwables;

import java.lang.Throwable;
import java.util.function.Consumer;
import java.util.function.DoubleToLongFunction;
import org.slf4j.Logger;
/**
 * Generated from DoubleToLongFunction
 * Extends java.util.function.DoubleToLongFunction to allow for a checked exception.
 *
 * @param <E> The extension
 */
@FunctionalInterface
public interface DoubleToLongFunctionWithThrowable<E extends Throwable> extends DoubleToLongFunction {


    /**
     * Utility method to mark lambdas of type DoubleToLongFunctionWithThrowable
     *
     * @param doubletolongfunctionwiththrowable The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> DoubleToLongFunctionWithThrowable<E> castDoubleToLongFunctionWithThrowable(final DoubleToLongFunctionWithThrowable<E> doubletolongfunctionwiththrowable) {
        return doubletolongfunctionwiththrowable;
    }
    /**
     * Utility method to convert DoubleToLongFunctionWithThrowable
     * @param doubletolongfunction The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> DoubleToLongFunctionWithThrowable<E> asDoubleToLongFunctionWithThrowable(final DoubleToLongFunction doubletolongfunction) {
        return doubletolongfunction::applyAsLong;
    }

    /** 
     * Overridden method of DoubleToLongFunctionWithThrowable that will call applyAsLongWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @return the value
     */
    @Override
    default long applyAsLong(final double v1) {
        try {
            return applyAsLongWithThrowable(v1);
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
    long applyAsLongWithThrowable(final double v1) throws E;


    /**
     * @param logger The logger to log exceptions on
     * @param message A message to use for logging exceptions
     * @return An interface that will log all exceptions to given logger
     */
    default DoubleToLongFunctionWithThrowable<E> withLogging(Logger logger, String message) {
        return (final double v1) -> {
            try {
                return applyAsLongWithThrowable(v1);
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
    default DoubleToLongFunctionWithThrowable<E> withLogging(org.slf4j.Logger logger) {
        return withLogging(logger, "Exception in DoubleToLongFunctionWithThrowable with arguments {}");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default DoubleToLongFunctionWithThrowable<E> withLogging() {
        return withLogging(org.slf4j.LoggerFactory.getLogger(getClass()));
    }



    /**
     * @param consumer An exception consumer.
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default DoubleToLongFunctionWithThrowable<E> onException(Consumer<Throwable> consumer) {
        return (final double v1) -> {
            try {
                return applyAsLongWithThrowable(v1);
            } catch (final Throwable throwable) {
                consumer.accept(throwable);
                throw throwable;
            }
        };
    }
}
