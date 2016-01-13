package org.slieb.throwables;

import java.lang.Throwable;
import java.util.function.Consumer;
import java.util.function.DoubleToIntFunction;
import org.slf4j.Logger;
/**
 * Generated from DoubleToIntFunction
 * Extends java.util.function.DoubleToIntFunction to allow for a checked exception.
 *
 * @param <E> The extension
 */
@FunctionalInterface
public interface DoubleToIntFunctionWithThrowable<E extends Throwable> extends DoubleToIntFunction {


    /**
     * Utility method to mark lambdas of type DoubleToIntFunctionWithThrowable
     *
     * @param doubletointfunctionwiththrowable The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> DoubleToIntFunctionWithThrowable<E> castDoubleToIntFunctionWithThrowable(final DoubleToIntFunctionWithThrowable<E> doubletointfunctionwiththrowable) {
        return doubletointfunctionwiththrowable;
    }
    /**
     * Utility method to convert DoubleToIntFunctionWithThrowable
     * @param doubletointfunction The interface instance
     * @param <E> The type this interface is allowed to throw
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
     * @param logger The logger to log exceptions on
     * @param message A message to use for logging exceptions
     * @return An interface that will log all exceptions to given logger
     */
    default DoubleToIntFunctionWithThrowable<E> withLogging(Logger logger, String message) {
        return (final double v1) -> {
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
    default DoubleToIntFunctionWithThrowable<E> withLogging(org.slf4j.Logger logger) {
        return withLogging(logger, "Exception in DoubleToIntFunctionWithThrowable with arguments {}");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default DoubleToIntFunctionWithThrowable<E> withLogging() {
        return withLogging(org.slf4j.LoggerFactory.getLogger(getClass()));
    }



    /**
     * @param consumer An exception consumer.
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default DoubleToIntFunctionWithThrowable<E> onException(Consumer<Throwable> consumer) {
        return (final double v1) -> {
            try {
                return applyAsIntWithThrowable(v1);
            } catch (final Throwable throwable) {
                consumer.accept(throwable);
                throw throwable;
            }
        };
    }
}
