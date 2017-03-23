package org.slieb.throwables;

import java.lang.FunctionalInterface;
import java.lang.SuppressWarnings;
import java.lang.Throwable;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Generated from BinaryOperator
 * Extends java.util.function.BinaryOperator to allow for a checked exception.
 *
 * @param <T> some generic flag
 * @param <E> The extension
 */
@FunctionalInterface
@SuppressWarnings({"WeakerAccess"})
public interface BinaryOperatorWithThrowable<T, E extends Throwable> extends BinaryOperator<T> {

    /**
     * Utility method to mark lambdas of type BinaryOperatorWithThrowable
     *
     * @param binaryoperatorwiththrowable The interface instance
     * @param <T> Generic that corresponds to the same generic on BinaryOperator  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, E extends Throwable> BinaryOperatorWithThrowable<T, E> castBinaryOperatorWithThrowable(final BinaryOperatorWithThrowable<T, E> binaryoperatorwiththrowable) {
        return binaryoperatorwiththrowable;
    }

    /**
     * Utility method to unwrap lambdas of type BinaryOperator and withUncheckedThrowable any Exception
     *
     * @param binaryoperatorwiththrowable The interface instance
     * @param <T> Generic that corresponds to the same generic on BinaryOperator  
     * @param <E> The type this interface is allowed to throw
     * @throws E the original Exception from binaryoperatorwiththrowable
     * @return the cast interface
     */
    static <T, E extends Throwable> BinaryOperator<T> aBinaryOperatorThatUnSafelyThrowsUncheckedThrowable(final BinaryOperatorWithThrowable<T, E> binaryoperatorwiththrowable) throws E {
        return binaryoperatorwiththrowable.thatUnSafelyThrowsUncheckedThrowable();
    }

    /**
     * Utility method to convert BinaryOperatorWithThrowable
     * @param binaryoperator The interface instance
     * @param <T> Generic that corresponds to the same generic on BinaryOperator  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, E extends Throwable> BinaryOperatorWithThrowable<T, E> asBinaryOperatorWithThrowable(final BinaryOperator<T> binaryoperator) {
        return binaryoperator::apply;
    }

    /**
     * Overridden method of BinaryOperatorWithThrowable that will call applyWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @param v2 parameter to overridden method
     * @return the value
     */
    @Override
    default T apply(final T v1, final T v2) {
        try {
            return applyWithThrowable(v1, v2);
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
    T applyWithThrowable(final T v1, final T v2) throws E;


    /**
     * @param defaultReturnValue A value to return if any throwable is caught.
     * @return An interface that returns a default value if any exception occurs.
     */
    default BinaryOperator<T> thatReturnsOnCatch(final T defaultReturnValue) {
      return (final T v1, final T v2) -> {
        try {
          return applyWithThrowable(v1, v2);
        } catch(final Throwable throwable) {
          return defaultReturnValue;
        }
      };
    }


    /**
     * @throws E if an exception E has been thrown, it is rethrown by this method
     * @return An interface that is only returned if no exception has been thrown.
     */
    default BinaryOperator<T> thatUnSafelyThrowsUncheckedThrowable() throws E {
      return (final T v1, final T v2) -> {
        try {
          return applyWithThrowable(v1, v2);
        } catch(final Throwable throwable) {
           SuppressedException.throwUnsafelyAsUnchecked(throwable);
           return null;
        }
      };
    }


    /**
     * @param logger The logger to log exceptions on
     * @param message A message to use for logging exceptions
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default BinaryOperatorWithThrowable<T, E> withLogging(final Logger logger, final String message) {
        return (final T v1, final T v2) -> {
            try {
                return applyWithThrowable(v1, v2);
            } catch (final Throwable throwable) {
                logger.error(message, v1, v2, throwable);
                throw throwable;
            }
        };
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @param logger The logger instance to log exceptions on
     * @return An interface that will log exceptions on given logger
     */
    default BinaryOperatorWithThrowable<T, E> withLogging(final Logger logger) {
        return withLogging(logger, "Exception in BinaryOperatorWithThrowable with the arguments [{}, {}]");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default BinaryOperatorWithThrowable<T, E> withLogging() {
        return withLogging(LoggerFactory.getLogger(getClass()));
    }



    /**
     * @param consumer An exception consumer.
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default BinaryOperatorWithThrowable<T, E> onException(final Consumer<Throwable> consumer) {
        return (final T v1, final T v2) -> {
            try {
                return applyWithThrowable(v1, v2);
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
    default BinaryOperatorWithThrowable<T, E> onException(final java.util.function.BiConsumer<Throwable, Object[]> consumer) {
        return (final T v1, final T v2) -> {
            try {
                return applyWithThrowable(v1, v2);
            } catch (final Throwable throwable) {
                consumer.accept(throwable, new Object[]{v1, v2});
                throw throwable;
            }
        };
    }
}
