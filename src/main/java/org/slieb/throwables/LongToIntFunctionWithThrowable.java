package org.slieb.throwables;

import java.lang.FunctionalInterface;
import java.lang.SuppressWarnings;
import java.lang.Throwable;
import java.util.function.Consumer;
import java.util.function.LongToIntFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Generated from LongToIntFunction
 * Extends java.util.function.LongToIntFunction to allow for a checked exception.
 *
 * @param <E> The extension
 */
@FunctionalInterface
@SuppressWarnings({"WeakerAccess"})
public interface LongToIntFunctionWithThrowable<E extends Throwable> extends LongToIntFunction {

    /**
     * Utility method to mark lambdas of type LongToIntFunctionWithThrowable
     *
     * @param longtointfunctionwiththrowable The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> LongToIntFunctionWithThrowable<E> castLongToIntFunctionWithThrowable(final LongToIntFunctionWithThrowable<E> longtointfunctionwiththrowable) {
        return longtointfunctionwiththrowable;
    }

    /**
     * Utility method to unwrap lambdas of type LongToIntFunction and withUncheckedThrowable any Exception
     *
     * @param longtointfunctionwiththrowable The interface instance
     * @param <E> The type this interface is allowed to throw
     * @throws E the original Exception from longtointfunctionwiththrowable
     * @return the cast interface
     */
    static <E extends Throwable> LongToIntFunction aLongToIntFunctionThatUnsafelyThrowsUnchecked(final LongToIntFunctionWithThrowable<E> longtointfunctionwiththrowable) throws E {
        return longtointfunctionwiththrowable.thatUnsafelyThrowsUnchecked();
    }

    /**
     * Utility method to convert LongToIntFunctionWithThrowable
     * @param longtointfunction The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> LongToIntFunctionWithThrowable<E> asLongToIntFunctionWithThrowable(final LongToIntFunction longtointfunction) {
        return longtointfunction::applyAsInt;
    }

    /**
     * Overridden method of LongToIntFunctionWithThrowable that will call applyAsIntWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @return the value
     */
    @Override
    default int applyAsInt(final long v1) {
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
    int applyAsIntWithThrowable(final long v1) throws E;


    /**
     * @param defaultReturnValue A value to return if any throwable is caught.
     * @return An interface that returns a default value if any exception occurs.
     */
    default LongToIntFunction thatReturnsOnCatch(final int defaultReturnValue) {
      return (final long v1) -> {
        try {
          return applyAsIntWithThrowable(v1);
        } catch(final Throwable throwable) {
          return defaultReturnValue;
        }
      };
    }


    /**
     * @throws E if an exception E has been thrown, it is rethrown by this method
     * @return An interface that is only returned if no exception has been thrown.
     */
    default LongToIntFunction thatUnsafelyThrowsUnchecked() throws E {
      return (final long v1) -> {
        try {
          return applyAsIntWithThrowable(v1);
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
    default LongToIntFunctionWithThrowable<E> withLogging(final Logger logger, final String message) {
        return (final long v1) -> {
            try {
                return applyAsIntWithThrowable(v1);
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
    default LongToIntFunctionWithThrowable<E> withLogging(final Logger logger) {
        return withLogging(logger, "Exception in LongToIntFunctionWithThrowable with the argument [{}]");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default LongToIntFunctionWithThrowable<E> withLogging() {
        return withLogging(LoggerFactory.getLogger(getClass()));
    }



    /**
     * @param consumer An exception consumer.
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default LongToIntFunctionWithThrowable<E> onException(final Consumer<Throwable> consumer) {
        return (final long v1) -> {
            try {
                return applyAsIntWithThrowable(v1);
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
    default LongToIntFunctionWithThrowable<E> onException(final java.util.function.BiConsumer<Throwable, Object[]> consumer) {
        return (final long v1) -> {
            try {
                return applyAsIntWithThrowable(v1);
            } catch (final Throwable throwable) {
                consumer.accept(throwable, new Object[]{v1});
                throw throwable;
            }
        };
    }
}
