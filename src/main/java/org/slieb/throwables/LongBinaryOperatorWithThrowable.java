package org.slieb.throwables;

import java.lang.FunctionalInterface;
import java.lang.SuppressWarnings;
import java.lang.Throwable;
import java.util.function.Consumer;
import java.util.function.LongBinaryOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Generated from LongBinaryOperator
 * Extends java.util.function.LongBinaryOperator to allow for a checked exception.
 *
 * @param <E> The extension
 */
@FunctionalInterface
@SuppressWarnings({"WeakerAccess"})
public interface LongBinaryOperatorWithThrowable<E extends Throwable> extends LongBinaryOperator {

    /**
     * Utility method to mark lambdas of type LongBinaryOperatorWithThrowable
     *
     * @param longbinaryoperatorwiththrowable The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> LongBinaryOperatorWithThrowable<E> castLongBinaryOperatorWithThrowable(final LongBinaryOperatorWithThrowable<E> longbinaryoperatorwiththrowable) {
        return longbinaryoperatorwiththrowable;
    }

    /**
     * Utility method to unwrap lambdas of type LongBinaryOperator and rethrow any Exception
     *
     * @param longbinaryoperatorwiththrowable The interface instance
     * @param <E> The type this interface is allowed to throw
     * @throws E the original Exception from longbinaryoperatorwiththrowable
     * @return the cast interface
     */
    static <E extends Throwable> LongBinaryOperator rethrowLongBinaryOperator(final LongBinaryOperatorWithThrowable<E> longbinaryoperatorwiththrowable) throws E {
        return longbinaryoperatorwiththrowable.rethrow();
    }

    /**
     * Utility method to convert LongBinaryOperatorWithThrowable
     * @param longbinaryoperator The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> LongBinaryOperatorWithThrowable<E> asLongBinaryOperatorWithThrowable(final LongBinaryOperator longbinaryoperator) {
        return longbinaryoperator::applyAsLong;
    }

    /**
     * Overridden method of LongBinaryOperatorWithThrowable that will call applyAsLongWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @param v2 parameter to overridden method
     * @return the value
     */
    @Override
    default long applyAsLong(final long v1, final long v2) {
        try {
            return applyAsLongWithThrowable(v1, v2);
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
    long applyAsLongWithThrowable(final long v1, final long v2) throws E;


    /**
     * @param defaultReturnValue A value to return if any throwable is caught.
     * @return An interface that returns a default value if any exception occurs.
     */
    default LongBinaryOperator thatReturnsOnCatch(final long defaultReturnValue) {
      return (final long v1, final long v2) -> {
        try {
          return applyAsLongWithThrowable(v1, v2);
        } catch(final Throwable throwable) {
          return defaultReturnValue;
        }
      };
    }


    /**
     * @throws E if an exception E has been thrown, it is rethrown by this method
     * @return An interface that is only returned if no exception has been thrown.
     */
    default LongBinaryOperator rethrow() throws E {
      return (final long v1, final long v2) -> {
        try {
          return applyAsLongWithThrowable(v1, v2);
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
    default LongBinaryOperatorWithThrowable<E> withLogging(final Logger logger, final String message) {
        return (final long v1, final long v2) -> {
            try {
                return applyAsLongWithThrowable(v1, v2);
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
    default LongBinaryOperatorWithThrowable<E> withLogging(final Logger logger) {
        return withLogging(logger, "Exception in LongBinaryOperatorWithThrowable with the arguments [{}, {}]");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default LongBinaryOperatorWithThrowable<E> withLogging() {
        return withLogging(LoggerFactory.getLogger(getClass()));
    }



    /**
     * @param consumer An exception consumer.
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default LongBinaryOperatorWithThrowable<E> onException(final Consumer<Throwable> consumer) {
        return (final long v1, final long v2) -> {
            try {
                return applyAsLongWithThrowable(v1, v2);
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
    default LongBinaryOperatorWithThrowable<E> onException(final java.util.function.BiConsumer<Throwable, Object[]> consumer) {
        return (final long v1, final long v2) -> {
            try {
                return applyAsLongWithThrowable(v1, v2);
            } catch (final Throwable throwable) {
                consumer.accept(throwable, new Object[]{v1, v2});
                throw throwable;
            }
        };
    }
}
