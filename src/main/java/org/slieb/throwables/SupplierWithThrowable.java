package org.slieb.throwables;

import java.lang.FunctionalInterface;
import java.lang.SuppressWarnings;
import java.lang.Throwable;
import java.util.function.Consumer;
import java.util.function.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Generated from Supplier
 * Extends java.util.function.Supplier to allow for a checked exception.
 *
 * @param <T> some generic flag
 * @param <E> The extension
 */
@FunctionalInterface
@SuppressWarnings({"WeakerAccess"})
public interface SupplierWithThrowable<T, E extends Throwable> extends Supplier<T> {

    /**
     * Utility method to mark lambdas of type SupplierWithThrowable
     *
     * @param supplierwiththrowable The interface instance
     * @param <T> Generic that corresponds to the same generic on Supplier  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, E extends Throwable> SupplierWithThrowable<T, E> castSupplierWithThrowable(final SupplierWithThrowable<T, E> supplierwiththrowable) {
        return supplierwiththrowable;
    }

    /**
     * Utility method to convert SupplierWithThrowable
     * @param supplier The interface instance
     * @param <T> Generic that corresponds to the same generic on Supplier  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, E extends Throwable> SupplierWithThrowable<T, E> asSupplierWithThrowable(final Supplier<T> supplier) {
        return supplier::get;
    }

    /** 
     * Overridden method of SupplierWithThrowable that will call getWithThrowable, but catching any exceptions.
     *
     * @return the value
     */
    @Override
    default T get() {
        try {
            return getWithThrowable();
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
    T getWithThrowable() throws E;


    /**
     * @return An interface that will wrap the result in an optional, and return an empty optional when an exception occurs.
     */
    default Supplier<java.util.Optional<T>>    thatReturnsOptional() {
      return ()     -> {
        try {
          return java.util.Optional.ofNullable(getWithThrowable());
        } catch(Throwable throwable) {
          return java.util.Optional.empty();
        }
      };
    }


    /**
     * @param defaultReturnValue A value to return if any throwable is caught.
     * @return An interface that returns a default value if any exception occurs.
     */
    default Supplier<T> thatReturnsOnCatch(final T defaultReturnValue) {
      return () -> {
        try {
          return getWithThrowable();
        } catch(final Throwable throwable) {
          return defaultReturnValue;
        }
      };
    }


    /**
     * @param logger The logger to log exceptions on
     * @param message A message to use for logging exceptions
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default SupplierWithThrowable<T, E> withLogging(final Logger logger, final String message) {
        return () -> {
            try {
                return getWithThrowable();
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
    default SupplierWithThrowable<T, E> withLogging(final Logger logger) {
        return withLogging(logger, "Exception in SupplierWithThrowable");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default SupplierWithThrowable<T, E> withLogging() {
        return withLogging(LoggerFactory.getLogger(getClass()));
    }



    /**
     * @param consumer An exception consumer.
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default SupplierWithThrowable<T, E> onException(final Consumer<Throwable> consumer) {
        return () -> {
            try {
                return getWithThrowable();
            } catch (final Throwable throwable) {
                consumer.accept(throwable);
                throw throwable;
            }
        };
    }
}
