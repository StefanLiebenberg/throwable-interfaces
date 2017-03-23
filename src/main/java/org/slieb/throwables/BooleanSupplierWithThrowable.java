package org.slieb.throwables;

import java.lang.FunctionalInterface;
import java.lang.SuppressWarnings;
import java.lang.Throwable;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Generated from BooleanSupplier
 * Extends java.util.function.BooleanSupplier to allow for a checked exception.
 *
 * @param <E> The extension
 */
@FunctionalInterface
@SuppressWarnings({"WeakerAccess"})
public interface BooleanSupplierWithThrowable<E extends Throwable> extends BooleanSupplier {

    /**
     * Utility method to mark lambdas of type BooleanSupplierWithThrowable
     *
     * @param booleansupplierwiththrowable The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> BooleanSupplierWithThrowable<E> castBooleanSupplierWithThrowable(final BooleanSupplierWithThrowable<E> booleansupplierwiththrowable) {
        return booleansupplierwiththrowable;
    }

    /**
     * Utility method to unwrap lambdas of type BooleanSupplier and withUncheckedThrowable any Exception
     *
     * @param booleansupplierwiththrowable The interface instance
     * @param <E> The type this interface is allowed to throw
     * @throws E the original Exception from booleansupplierwiththrowable
     * @return the cast interface
     */
    static <E extends Throwable> BooleanSupplier aBooleanSupplierThatUnSafelyThrowsUncheckedThrowable(final BooleanSupplierWithThrowable<E> booleansupplierwiththrowable) throws E {
        return booleansupplierwiththrowable.thatUnSafelyThrowsUncheckedThrowable();
    }

    /**
     * Utility method to convert BooleanSupplierWithThrowable
     * @param booleansupplier The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> BooleanSupplierWithThrowable<E> asBooleanSupplierWithThrowable(final BooleanSupplier booleansupplier) {
        return booleansupplier::getAsBoolean;
    }

    /**
     * Overridden method of BooleanSupplierWithThrowable that will call getAsBooleanWithThrowable, but catching any exceptions.
     *
     * @return the value
     */
    @Override
    default boolean getAsBoolean() {
        try {
            return getAsBooleanWithThrowable();
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
    boolean getAsBooleanWithThrowable() throws E;


    /**
     * @param defaultReturnValue A value to return if any throwable is caught.
     * @return An interface that returns a default value if any exception occurs.
     */
    default BooleanSupplier thatReturnsOnCatch(final boolean defaultReturnValue) {
      return () -> {
        try {
          return getAsBooleanWithThrowable();
        } catch(final Throwable throwable) {
          return defaultReturnValue;
        }
      };
    }


    /**
     * @throws E if an exception E has been thrown, it is rethrown by this method
     * @return An interface that is only returned if no exception has been thrown.
     */
    default BooleanSupplier thatUnSafelyThrowsUncheckedThrowable() throws E {
      return () -> {
        try {
          return getAsBooleanWithThrowable();
        } catch(final Throwable throwable) {
           SuppressedException.throwUnsafelyAsUnchecked(throwable);
           return false;        }
      };
    }


    /**
     * @param logger The logger to log exceptions on
     * @param message A message to use for logging exceptions
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default BooleanSupplierWithThrowable<E> withLogging(final Logger logger, final String message) {
        return () -> {
            try {
                return getAsBooleanWithThrowable();
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
    default BooleanSupplierWithThrowable<E> withLogging(final Logger logger) {
        return withLogging(logger, "Exception in BooleanSupplierWithThrowable");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default BooleanSupplierWithThrowable<E> withLogging() {
        return withLogging(LoggerFactory.getLogger(getClass()));
    }



    /**
     * @param consumer An exception consumer.
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default BooleanSupplierWithThrowable<E> onException(final Consumer<Throwable> consumer) {
        return () -> {
            try {
                return getAsBooleanWithThrowable();
            } catch (final Throwable throwable) {
                consumer.accept(throwable);
                throw throwable;
            }
        };
    }
}
