package org.slieb.throwables;

import java.lang.FunctionalInterface;
import java.lang.SuppressWarnings;
import java.lang.Throwable;
import java.util.function.Consumer;
import java.util.function.IntBinaryOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Generated from IntBinaryOperator
 * Extends java.util.function.IntBinaryOperator to allow for a checked exception.
 *
 * @param <E> The extension
 */
@FunctionalInterface
@SuppressWarnings({"WeakerAccess"})
public interface IntBinaryOperatorWithThrowable<E extends Throwable> extends IntBinaryOperator {

    /**
     * Utility method to mark lambdas of type IntBinaryOperatorWithThrowable
     *
     * @param intbinaryoperatorwiththrowable The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> IntBinaryOperatorWithThrowable<E> castIntBinaryOperatorWithThrowable(final IntBinaryOperatorWithThrowable<E> intbinaryoperatorwiththrowable) {
        return intbinaryoperatorwiththrowable;
    }

    /**
     * Utility method to unwrap lambdas of type IntBinaryOperator and withUncheckedThrowable any Exception
     *
     * @param intbinaryoperatorwiththrowable The interface instance
     * @param <E> The type this interface is allowed to throw
     * @throws E the original Exception from intbinaryoperatorwiththrowable
     * @return the cast interface
     */
    static <E extends Throwable> IntBinaryOperator aIntBinaryOperatorThatUnsafelyThrowsUnchecked(final IntBinaryOperatorWithThrowable<E> intbinaryoperatorwiththrowable) throws E {
        return intbinaryoperatorwiththrowable.thatUnsafelyThrowsUnchecked();
    }

    /**
     * Utility method to convert IntBinaryOperatorWithThrowable
     * @param intbinaryoperator The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> IntBinaryOperatorWithThrowable<E> asIntBinaryOperatorWithThrowable(final IntBinaryOperator intbinaryoperator) {
        return intbinaryoperator::applyAsInt;
    }

    /**
     * Overridden method of IntBinaryOperatorWithThrowable that will call applyAsIntWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @param v2 parameter to overridden method
     * @return the value
     */
    @Override
    default int applyAsInt(final int v1, final int v2) {
        try {
            return applyAsIntWithThrowable(v1, v2);
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
    int applyAsIntWithThrowable(final int v1, final int v2) throws E;


    /**
     * @param defaultReturnValue A value to return if any throwable is caught.
     * @return An interface that returns a default value if any exception occurs.
     */
    default IntBinaryOperator thatReturnsOnCatch(final int defaultReturnValue) {
      return (final int v1, final int v2) -> {
        try {
          return applyAsIntWithThrowable(v1, v2);
        } catch(final Throwable throwable) {
          return defaultReturnValue;
        }
      };
    }


    /**
     * @throws E if an exception E has been thrown, it is rethrown by this method
     * @return An interface that is only returned if no exception has been thrown.
     */
    default IntBinaryOperator thatUnsafelyThrowsUnchecked() throws E {
      return (final int v1, final int v2) -> {
        try {
          return applyAsIntWithThrowable(v1, v2);
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
    default IntBinaryOperatorWithThrowable<E> withLogging(final Logger logger, final String message) {
        return (final int v1, final int v2) -> {
            try {
                return applyAsIntWithThrowable(v1, v2);
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
    default IntBinaryOperatorWithThrowable<E> withLogging(final Logger logger) {
        return withLogging(logger, "Exception in IntBinaryOperatorWithThrowable with the arguments [{}, {}]");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default IntBinaryOperatorWithThrowable<E> withLogging() {
        return withLogging(LoggerFactory.getLogger(getClass()));
    }



    /**
     * @param consumer An exception consumer.
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default IntBinaryOperatorWithThrowable<E> onException(final Consumer<Throwable> consumer) {
        return (final int v1, final int v2) -> {
            try {
                return applyAsIntWithThrowable(v1, v2);
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
    default IntBinaryOperatorWithThrowable<E> onException(final java.util.function.BiConsumer<Throwable, Object[]> consumer) {
        return (final int v1, final int v2) -> {
            try {
                return applyAsIntWithThrowable(v1, v2);
            } catch (final Throwable throwable) {
                consumer.accept(throwable, new Object[]{v1, v2});
                throw throwable;
            }
        };
    }
}
