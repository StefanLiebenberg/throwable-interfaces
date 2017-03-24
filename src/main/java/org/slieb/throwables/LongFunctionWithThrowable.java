package org.slieb.throwables;

import java.lang.FunctionalInterface;
import java.lang.SuppressWarnings;
import java.lang.Throwable;
import java.util.function.Consumer;
import java.util.function.LongFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Generated from LongFunction
 * Extends java.util.function.LongFunction to allow for a checked exception.
 *
 * @param <R> some generic flag
 * @param <E> The extension
 */
@FunctionalInterface
@SuppressWarnings({"WeakerAccess"})
public interface LongFunctionWithThrowable<R, E extends Throwable> extends LongFunction<R> {

    /**
     * Utility method to mark lambdas of type LongFunctionWithThrowable
     *
     * @param longfunctionwiththrowable The interface instance
     * @param <R> Generic that corresponds to the same generic on LongFunction  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <R, E extends Throwable> LongFunctionWithThrowable<R, E> castLongFunctionWithThrowable(final LongFunctionWithThrowable<R, E> longfunctionwiththrowable) {
        return longfunctionwiththrowable;
    }

    /**
     * Utility method to unwrap lambdas of type LongFunction and withUncheckedThrowable any Exception
     *
     * @param longfunctionwiththrowable The interface instance
     * @param <R> Generic that corresponds to the same generic on LongFunction  
     * @param <E> The type this interface is allowed to throw
     * @throws E the original Exception from longfunctionwiththrowable
     * @return the cast interface
     */
    static <R, E extends Throwable> LongFunction<R> aLongFunctionThatUnsafelyThrowsUnchecked(final LongFunctionWithThrowable<R, E> longfunctionwiththrowable) throws E {
        return longfunctionwiththrowable.thatUnsafelyThrowsUnchecked();
    }

    /**
     * Utility method to convert LongFunctionWithThrowable
     * @param longfunction The interface instance
     * @param <R> Generic that corresponds to the same generic on LongFunction  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <R, E extends Throwable> LongFunctionWithThrowable<R, E> asLongFunctionWithThrowable(final LongFunction<R> longfunction) {
        return longfunction::apply;
    }

    /**
     * Overridden method of LongFunctionWithThrowable that will call applyWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @return the value
     */
    @Override
    default R apply(final long v1) {
        try {
            return applyWithThrowable(v1);
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
    R applyWithThrowable(final long v1) throws E;


    /**
     * @return An interface that will wrap the result in an optional, and return an empty optional when an exception occurs.
     */
    default LongFunction<java.util.Optional<R>>    thatReturnsOptional() {
      return (final long v1)     -> {
        try {
          return java.util.Optional.ofNullable(applyWithThrowable(v1));
        } catch(Throwable throwable) {
          return java.util.Optional.empty();
        }
      };
    }


    /**
     * @param defaultReturnValue A value to return if any throwable is caught.
     * @return An interface that returns a default value if any exception occurs.
     */
    default LongFunction<R> thatReturnsOnCatch(final R defaultReturnValue) {
      return (final long v1) -> {
        try {
          return applyWithThrowable(v1);
        } catch(final Throwable throwable) {
          return defaultReturnValue;
        }
      };
    }


    /**
     * @throws E if an exception E has been thrown, it is rethrown by this method
     * @return An interface that is only returned if no exception has been thrown.
     */
    default LongFunction<R> thatUnsafelyThrowsUnchecked() throws E {
      return (final long v1) -> {
        try {
          return applyWithThrowable(v1);
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
    default LongFunctionWithThrowable<R, E> withLogging(final Logger logger, final String message) {
        return (final long v1) -> {
            try {
                return applyWithThrowable(v1);
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
    default LongFunctionWithThrowable<R, E> withLogging(final Logger logger) {
        return withLogging(logger, "Exception in LongFunctionWithThrowable with the argument [{}]");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default LongFunctionWithThrowable<R, E> withLogging() {
        return withLogging(LoggerFactory.getLogger(getClass()));
    }



    /**
     * @param consumer An exception consumer.
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default LongFunctionWithThrowable<R, E> onException(final Consumer<Throwable> consumer) {
        return (final long v1) -> {
            try {
                return applyWithThrowable(v1);
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
    default LongFunctionWithThrowable<R, E> onException(final java.util.function.BiConsumer<Throwable, Object[]> consumer) {
        return (final long v1) -> {
            try {
                return applyWithThrowable(v1);
            } catch (final Throwable throwable) {
                consumer.accept(throwable, new Object[]{v1});
                throw throwable;
            }
        };
    }
}
