package org.slieb.throwables;

import java.lang.Throwable;
import java.util.function.Consumer;
import java.util.function.DoubleSupplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Generated from DoubleSupplier
 * Extends java.util.function.DoubleSupplier to allow for a checked exception.
 *
 * @param <E> The extension
 */
@FunctionalInterface
public interface DoubleSupplierWithThrowable<E extends Throwable> extends DoubleSupplier {

    /**
     * Utility method to mark lambdas of type DoubleSupplierWithThrowable
     *
     * @param doublesupplierwiththrowable The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> DoubleSupplierWithThrowable<E> castDoubleSupplierWithThrowable(final DoubleSupplierWithThrowable<E> doublesupplierwiththrowable) {
        return doublesupplierwiththrowable;
    }

    /**
     * Utility method to convert DoubleSupplierWithThrowable
     * @param doublesupplier The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> DoubleSupplierWithThrowable<E> asDoubleSupplierWithThrowable(final DoubleSupplier doublesupplier) {
        return doublesupplier::getAsDouble;
    }

    /** 
     * Overridden method of DoubleSupplierWithThrowable that will call getAsDoubleWithThrowable, but catching any exceptions.
     *
     * @return the value
     */
    @Override
    default double getAsDouble() {
        try {
            return getAsDoubleWithThrowable();
        } catch (final RuntimeException | Error exception) {
            throw exception;
        } catch (final Throwable throwable) {
            throw new SuppressedException(throwable);
        }
    }

    /** 
     * Functional method that will throw exceptions.
     *
     * @return the value
     * @throws E some exception
     */
    double getAsDoubleWithThrowable() throws E;


    /**
     * @return An interface that will wrap the result in an optional, and return an empty optional when an exception occurs.
     */


    /**
     * @param logger The logger to log exceptions on
     * @param message A message to use for logging exceptions
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default DoubleSupplierWithThrowable<E> withLogging(final Logger logger, final String message) {
        return () -> {
            try {
                return getAsDoubleWithThrowable();
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
    default DoubleSupplierWithThrowable<E> withLogging(final Logger logger) {
        return withLogging(logger, "Exception in DoubleSupplierWithThrowable");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default DoubleSupplierWithThrowable<E> withLogging() {
        return withLogging(LoggerFactory.getLogger(getClass()));
    }



    /**
     * @param consumer An exception consumer.
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default DoubleSupplierWithThrowable<E> onException(final Consumer<Throwable> consumer) {
        return () -> {
            try {
                return getAsDoubleWithThrowable();
            } catch (final Throwable throwable) {
                consumer.accept(throwable);
                throw throwable;
            }
        };
    }
}
