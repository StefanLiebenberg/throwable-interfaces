package org.slieb.throwables;

import java.lang.FunctionalInterface;
import java.lang.SuppressWarnings;
import java.lang.Throwable;
import java.util.function.Consumer;
import java.util.function.ToLongBiFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Generated from ToLongBiFunction
 * Extends java.util.function.ToLongBiFunction to allow for a checked exception.
 *
 * @param <T> some generic flag
 * @param <U> some generic flag
 * @param <E> The extension
 */
@FunctionalInterface
@SuppressWarnings({"WeakerAccess"})
public interface ToLongBiFunctionWithThrowable<T, U, E extends Throwable> extends ToLongBiFunction<T, U> {

    /**
     * Utility method to mark lambdas of type ToLongBiFunctionWithThrowable
     *
     * @param tolongbifunctionwiththrowable The interface instance
     * @param <T> Generic that corresponds to the same generic on ToLongBiFunction  
     * @param <U> Generic that corresponds to the same generic on ToLongBiFunction  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, U, E extends Throwable> ToLongBiFunctionWithThrowable<T, U, E> castToLongBiFunctionWithThrowable(final ToLongBiFunctionWithThrowable<T, U, E> tolongbifunctionwiththrowable) {
        return tolongbifunctionwiththrowable;
    }

    /**
     * Utility method to unwrap lambdas of type ToLongBiFunction and rethrow any Exception
     *
     * @param tolongbifunctionwiththrowable The interface instance
     * @param <T> Generic that corresponds to the same generic on ToLongBiFunction  
     * @param <U> Generic that corresponds to the same generic on ToLongBiFunction  
     * @param <E> The type this interface is allowed to throw
     * @throws E the original Exception from tolongbifunctionwiththrowable
     * @return the cast interface
     */
    static <T, U, E extends Throwable> ToLongBiFunction<T, U> rethrowToLongBiFunction(final ToLongBiFunctionWithThrowable<T, U, E> tolongbifunctionwiththrowable) throws E {
        return tolongbifunctionwiththrowable.rethrow();
    }

    /**
     * Utility method to convert ToLongBiFunctionWithThrowable
     * @param tolongbifunction The interface instance
     * @param <T> Generic that corresponds to the same generic on ToLongBiFunction  
     * @param <U> Generic that corresponds to the same generic on ToLongBiFunction  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, U, E extends Throwable> ToLongBiFunctionWithThrowable<T, U, E> asToLongBiFunctionWithThrowable(final ToLongBiFunction<T, U> tolongbifunction) {
        return tolongbifunction::applyAsLong;
    }

    /**
     * Overridden method of ToLongBiFunctionWithThrowable that will call applyAsLongWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @param v2 parameter to overridden method
     * @return the value
     */
    @Override
    default long applyAsLong(final T v1, final U v2) {
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
    long applyAsLongWithThrowable(final T v1, final U v2) throws E;


    /**
     * @return An interface that will wrap the result in an optional, and return an empty optional when an exception occurs.
     */
    default java.util.function.BiFunction<T, U, java.util.OptionalLong>     thatReturnsOptional() {
      return (v1, v2)     -> {
        try {
          return java.util.OptionalLong.of(applyAsLongWithThrowable(v1, v2));
        } catch(Throwable throwable) {
          return java.util.OptionalLong.empty();
        }
      };
    }


    /**
     * @param defaultReturnValue A value to return if any throwable is caught.
     * @return An interface that returns a default value if any exception occurs.
     */
    default ToLongBiFunction<T, U> thatReturnsOnCatch(final long defaultReturnValue) {
      return (final T v1, final U v2) -> {
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
    default ToLongBiFunction<T, U> rethrow() throws E {
      return (final T v1, final U v2) -> {
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
    default ToLongBiFunctionWithThrowable<T, U, E> withLogging(final Logger logger, final String message) {
        return (final T v1, final U v2) -> {
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
    default ToLongBiFunctionWithThrowable<T, U, E> withLogging(final Logger logger) {
        return withLogging(logger, "Exception in ToLongBiFunctionWithThrowable with the arguments [{}, {}]");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default ToLongBiFunctionWithThrowable<T, U, E> withLogging() {
        return withLogging(LoggerFactory.getLogger(getClass()));
    }



    /**
     * @param consumer An exception consumer.
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default ToLongBiFunctionWithThrowable<T, U, E> onException(final Consumer<Throwable> consumer) {
        return (final T v1, final U v2) -> {
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
    default ToLongBiFunctionWithThrowable<T, U, E> onException(final java.util.function.BiConsumer<Throwable, Object[]> consumer) {
        return (final T v1, final U v2) -> {
            try {
                return applyAsLongWithThrowable(v1, v2);
            } catch (final Throwable throwable) {
                consumer.accept(throwable, new Object[]{v1, v2});
                throw throwable;
            }
        };
    }
}
