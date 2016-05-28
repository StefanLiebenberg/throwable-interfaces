package org.slieb.throwables;

import java.lang.FunctionalInterface;
import java.lang.SuppressWarnings;
import java.lang.Throwable;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Generated from IntFunction
 * Extends java.util.function.IntFunction to allow for a checked exception.
 *
 * @param <R> some generic flag
 * @param <E> The extension
 */
@FunctionalInterface
@SuppressWarnings({"WeakerAccess"})
public interface IntFunctionWithThrowable<R, E extends Throwable> extends IntFunction<R> {

    /**
     * Utility method to mark lambdas of type IntFunctionWithThrowable
     *
     * @param intfunctionwiththrowable The interface instance
     * @param <R> Generic that corresponds to the same generic on IntFunction  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <R, E extends Throwable> IntFunctionWithThrowable<R, E> castIntFunctionWithThrowable(final IntFunctionWithThrowable<R, E> intfunctionwiththrowable) {
        return intfunctionwiththrowable;
    }

    /**
     * Utility method to convert IntFunctionWithThrowable
     * @param intfunction The interface instance
     * @param <R> Generic that corresponds to the same generic on IntFunction  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <R, E extends Throwable> IntFunctionWithThrowable<R, E> asIntFunctionWithThrowable(final IntFunction<R> intfunction) {
        return intfunction::apply;
    }

    /** 
     * Overridden method of IntFunctionWithThrowable that will call applyWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @return the value
     */
    @Override
    default R apply(final int v1) {
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
    R applyWithThrowable(final int v1) throws E;


    /**
     * @return An interface that will wrap the result in an optional, and return an empty optional when an exception occurs.
     */
    default IntFunction<java.util.Optional<R>>    thatReturnsOptional() {
      return (final int v1)     -> {
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
    default IntFunction<R> thatReturnsOnCatch(final R defaultReturnValue) {
      return (final int v1) -> {
        try {
          return applyWithThrowable(v1);
        } catch(final Throwable throwable) {
          return defaultReturnValue;
        }
      };
    }


    /**
     * @param logger The logger to log exceptions on
     * @param message A message to use for logging exceptions
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default IntFunctionWithThrowable<R, E> withLogging(final Logger logger, final String message) {
        return (final int v1) -> {
            try {
                return applyWithThrowable(v1);
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
    default IntFunctionWithThrowable<R, E> withLogging(final Logger logger) {
        return withLogging(logger, "Exception in IntFunctionWithThrowable");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default IntFunctionWithThrowable<R, E> withLogging() {
        return withLogging(LoggerFactory.getLogger(getClass()));
    }



    /**
     * @param consumer An exception consumer.
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default IntFunctionWithThrowable<R, E> onException(final Consumer<Throwable> consumer) {
        return (final int v1) -> {
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
    default IntFunctionWithThrowable<R, E> onException(final java.util.function.BiConsumer<Throwable, Object[]> consumer) {
        return (final int v1) -> {
            try {
                return applyWithThrowable(v1);
            } catch (final Throwable throwable) {
                consumer.accept(throwable, new Object[]{v1});
                throw throwable;
            }
        };
    }
}
