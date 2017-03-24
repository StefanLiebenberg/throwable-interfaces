package org.slieb.throwables;

import java.lang.FunctionalInterface;
import java.lang.SuppressWarnings;
import java.lang.Throwable;
import java.util.function.Consumer;
import java.util.function.IntSupplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Generated from IntSupplier
 * Extends java.util.function.IntSupplier to allow for a checked exception.
 *
 * @param <E> The extension
 */
@FunctionalInterface
@SuppressWarnings({"WeakerAccess"})
public interface IntSupplierWithThrowable<E extends Throwable> extends IntSupplier {

    /**
     * Utility method to mark lambdas of type IntSupplierWithThrowable
     *
     * @param intsupplierwiththrowable The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> IntSupplierWithThrowable<E> castIntSupplierWithThrowable(final IntSupplierWithThrowable<E> intsupplierwiththrowable) {
        return intsupplierwiththrowable;
    }

    /**
     * Utility method to unwrap lambdas of type IntSupplier and withUncheckedThrowable any Exception
     *
     * @param intsupplierwiththrowable The interface instance
     * @param <E> The type this interface is allowed to throw
     * @throws E the original Exception from intsupplierwiththrowable
     * @return the cast interface
     */
    static <E extends Throwable> IntSupplier aIntSupplierThatUnsafelyThrowsUnchecked(final IntSupplierWithThrowable<E> intsupplierwiththrowable) throws E {
        return intsupplierwiththrowable.thatUnsafelyThrowsUnchecked();
    }

    /**
     * Utility method to convert IntSupplierWithThrowable
     * @param intsupplier The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> IntSupplierWithThrowable<E> asIntSupplierWithThrowable(final IntSupplier intsupplier) {
        return intsupplier::getAsInt;
    }

    /**
     * Overridden method of IntSupplierWithThrowable that will call getAsIntWithThrowable, but catching any exceptions.
     *
     * @return the value
     */
    @Override
    default int getAsInt() {
        try {
            return getAsIntWithThrowable();
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
    int getAsIntWithThrowable() throws E;


    /**
     * @param defaultReturnValue A value to return if any throwable is caught.
     * @return An interface that returns a default value if any exception occurs.
     */
    default IntSupplier thatReturnsOnCatch(final int defaultReturnValue) {
      return () -> {
        try {
          return getAsIntWithThrowable();
        } catch(final Throwable throwable) {
          return defaultReturnValue;
        }
      };
    }


    /**
     * @throws E if an exception E has been thrown, it is rethrown by this method
     * @return An interface that is only returned if no exception has been thrown.
     */
    default IntSupplier thatUnsafelyThrowsUnchecked() throws E {
      return () -> {
        try {
          return getAsIntWithThrowable();
        } catch(final Throwable throwable) {
           SuppressedException.throwUnsafelyAsUnchecked(throwable);
           return 0;        }
      };
    }


    /**
     * @param logger The logger to log exceptions on
     * @param message A message to use for logging exceptions
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default IntSupplierWithThrowable<E> withLogging(final Logger logger, final String message) {
        return () -> {
            try {
                return getAsIntWithThrowable();
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
    default IntSupplierWithThrowable<E> withLogging(final Logger logger) {
        return withLogging(logger, "Exception in IntSupplierWithThrowable");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default IntSupplierWithThrowable<E> withLogging() {
        return withLogging(LoggerFactory.getLogger(getClass()));
    }



    /**
     * @param consumer An exception consumer.
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default IntSupplierWithThrowable<E> onException(final Consumer<Throwable> consumer) {
        return () -> {
            try {
                return getAsIntWithThrowable();
            } catch (final Throwable throwable) {
                consumer.accept(throwable);
                throw throwable;
            }
        };
    }
}
