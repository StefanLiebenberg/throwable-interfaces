package org.slieb.throwables;

import java.lang.FunctionalInterface;
import java.lang.SuppressWarnings;
import java.lang.Throwable;
import java.util.function.Consumer;
import java.util.function.LongSupplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Generated from LongSupplier
 * Extends java.util.function.LongSupplier to allow for a checked exception.
 *
 * @param <E> The extension
 */
@FunctionalInterface
@SuppressWarnings({"WeakerAccess"})
public interface LongSupplierWithThrowable<E extends Throwable> extends LongSupplier {

    /**
     * Utility method to mark lambdas of type LongSupplierWithThrowable
     *
     * @param longsupplierwiththrowable The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> LongSupplierWithThrowable<E> castLongSupplierWithThrowable(final LongSupplierWithThrowable<E> longsupplierwiththrowable) {
        return longsupplierwiththrowable;
    }

    /**
     * Utility method to unwrap lambdas of type LongSupplier and rethrow any Exception
     *
     * @param longsupplierwiththrowable The interface instance
     * @param <E> The type this interface is allowed to throw
     * @throws E the original Exception from longsupplierwiththrowable
     * @return the cast interface
     */
    static <E extends Throwable> LongSupplier rethrowLongSupplier(final LongSupplierWithThrowable<E> longsupplierwiththrowable) throws E {
        return longsupplierwiththrowable.rethrow();
    }

    /**
     * Utility method to convert LongSupplierWithThrowable
     * @param longsupplier The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> LongSupplierWithThrowable<E> asLongSupplierWithThrowable(final LongSupplier longsupplier) {
        return longsupplier::getAsLong;
    }

    /**
     * Overridden method of LongSupplierWithThrowable that will call getAsLongWithThrowable, but catching any exceptions.
     *
     * @return the value
     */
    @Override
    default long getAsLong() {
        try {
            return getAsLongWithThrowable();
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
    long getAsLongWithThrowable() throws E;


    /**
     * @param defaultReturnValue A value to return if any throwable is caught.
     * @return An interface that returns a default value if any exception occurs.
     */
    default LongSupplier thatReturnsOnCatch(final long defaultReturnValue) {
      return () -> {
        try {
          return getAsLongWithThrowable();
        } catch(final Throwable throwable) {
          return defaultReturnValue;
        }
      };
    }


    /**
     * @throws E if an exception E has been thrown, it is rethrown by this method
     * @return An interface that is only returned if no exception has been thrown.
     */
    default LongSupplier rethrow() throws E {
      return () -> {
        try {
          return getAsLongWithThrowable();
        } catch(final Throwable throwable) {
          SuppressedException.throwAsUnchecked(throwable);
          throw new RuntimeException("Unreachable code.");
        }
      };
    }


    /**
     * @param logger The logger to log exceptions on
     * @param message A message to use for logging exceptions
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default LongSupplierWithThrowable<E> withLogging(final Logger logger, final String message) {
        return () -> {
            try {
                return getAsLongWithThrowable();
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
    default LongSupplierWithThrowable<E> withLogging(final Logger logger) {
        return withLogging(logger, "Exception in LongSupplierWithThrowable");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default LongSupplierWithThrowable<E> withLogging() {
        return withLogging(LoggerFactory.getLogger(getClass()));
    }



    /**
     * @param consumer An exception consumer.
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default LongSupplierWithThrowable<E> onException(final Consumer<Throwable> consumer) {
        return () -> {
            try {
                return getAsLongWithThrowable();
            } catch (final Throwable throwable) {
                consumer.accept(throwable);
                throw throwable;
            }
        };
    }
}
