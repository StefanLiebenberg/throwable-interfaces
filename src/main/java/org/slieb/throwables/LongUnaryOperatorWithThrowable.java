package org.slieb.throwables;

import java.lang.FunctionalInterface;
import java.lang.SuppressWarnings;
import java.lang.Throwable;
import java.util.function.Consumer;
import java.util.function.LongUnaryOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Generated from LongUnaryOperator
 * Extends java.util.function.LongUnaryOperator to allow for a checked exception.
 *
 * @param <E> The extension
 */
@FunctionalInterface
@SuppressWarnings({"WeakerAccess"})
public interface LongUnaryOperatorWithThrowable<E extends Throwable> extends LongUnaryOperator {

    /**
     * Utility method to mark lambdas of type LongUnaryOperatorWithThrowable
     *
     * @param longunaryoperatorwiththrowable The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> LongUnaryOperatorWithThrowable<E> castLongUnaryOperatorWithThrowable(final LongUnaryOperatorWithThrowable<E> longunaryoperatorwiththrowable) {
        return longunaryoperatorwiththrowable;
    }

    /**
     * Utility method to unwrap lambdas of type LongUnaryOperator and withUncheckedThrowable any Exception
     *
     * @param longunaryoperatorwiththrowable The interface instance
     * @param <E> The type this interface is allowed to throw
     * @throws E the original Exception from longunaryoperatorwiththrowable
     * @return the cast interface
     */
    static <E extends Throwable> LongUnaryOperator aLongUnaryOperatorThatUnsafelyThrowsUnchecked(final LongUnaryOperatorWithThrowable<E> longunaryoperatorwiththrowable) throws E {
        return longunaryoperatorwiththrowable.thatUnsafelyThrowsUnchecked();
    }

    /**
     * Utility method to convert LongUnaryOperatorWithThrowable
     * @param longunaryoperator The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> LongUnaryOperatorWithThrowable<E> asLongUnaryOperatorWithThrowable(final LongUnaryOperator longunaryoperator) {
        return longunaryoperator::applyAsLong;
    }

    /**
     * Overridden method of LongUnaryOperatorWithThrowable that will call applyAsLongWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @return the value
     */
    @Override
    default long applyAsLong(final long v1) {
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
    long applyAsLongWithThrowable(final long v1) throws E;


    /**
     * @param defaultReturnValue A value to return if any throwable is caught.
     * @return An interface that returns a default value if any exception occurs.
     */
    default LongUnaryOperator thatReturnsOnCatch(final long defaultReturnValue) {
      return (final long v1) -> {
        try {
          return applyAsLongWithThrowable(v1);
        } catch(final Throwable throwable) {
          return defaultReturnValue;
        }
      };
    }


    /**
     * @throws E if an exception E has been thrown, it is rethrown by this method
     * @return An interface that is only returned if no exception has been thrown.
     */
    default LongUnaryOperator thatUnsafelyThrowsUnchecked() throws E {
      return (final long v1) -> {
        try {
          return applyAsLongWithThrowable(v1);
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
    default LongUnaryOperatorWithThrowable<E> withLogging(final Logger logger, final String message) {
        return (final long v1) -> {
            try {
                return applyAsLongWithThrowable(v1);
            } catch (final Throwable throwable) {
                logger.error(message, v1, throwable);
                throw throwable;
            }
        };
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @param logger The logger instance to log exceptions on
     * @return An interface that will log exceptions on given logger
     */
    default LongUnaryOperatorWithThrowable<E> withLogging(final Logger logger) {
        return withLogging(logger, "Exception in LongUnaryOperatorWithThrowable with the argument [{}]");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default LongUnaryOperatorWithThrowable<E> withLogging() {
        return withLogging(LoggerFactory.getLogger(getClass()));
    }



    /**
     * @param consumer An exception consumer.
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default LongUnaryOperatorWithThrowable<E> onException(final Consumer<Throwable> consumer) {
        return (final long v1) -> {
            try {
                return applyAsLongWithThrowable(v1);
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
    default LongUnaryOperatorWithThrowable<E> onException(final java.util.function.BiConsumer<Throwable, Object[]> consumer) {
        return (final long v1) -> {
            try {
                return applyAsLongWithThrowable(v1);
            } catch (final Throwable throwable) {
                consumer.accept(throwable, new Object[]{v1});
                throw throwable;
            }
        };
    }
}
