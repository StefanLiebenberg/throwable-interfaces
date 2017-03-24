package org.slieb.throwables;

import java.lang.FunctionalInterface;
import java.lang.SuppressWarnings;
import java.lang.Throwable;
import java.util.function.Consumer;
import java.util.function.ToDoubleFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Generated from ToDoubleFunction
 * Extends java.util.function.ToDoubleFunction to allow for a checked exception.
 *
 * @param <T> some generic flag
 * @param <E> The extension
 */
@FunctionalInterface
@SuppressWarnings({"WeakerAccess"})
public interface ToDoubleFunctionWithThrowable<T, E extends Throwable> extends ToDoubleFunction<T> {

    /**
     * Utility method to mark lambdas of type ToDoubleFunctionWithThrowable
     *
     * @param todoublefunctionwiththrowable The interface instance
     * @param <T> Generic that corresponds to the same generic on ToDoubleFunction  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, E extends Throwable> ToDoubleFunctionWithThrowable<T, E> castToDoubleFunctionWithThrowable(final ToDoubleFunctionWithThrowable<T, E> todoublefunctionwiththrowable) {
        return todoublefunctionwiththrowable;
    }

    /**
     * Utility method to unwrap lambdas of type ToDoubleFunction and withUncheckedThrowable any Exception
     *
     * @param todoublefunctionwiththrowable The interface instance
     * @param <T> Generic that corresponds to the same generic on ToDoubleFunction  
     * @param <E> The type this interface is allowed to throw
     * @throws E the original Exception from todoublefunctionwiththrowable
     * @return the cast interface
     */
    static <T, E extends Throwable> ToDoubleFunction<T> aToDoubleFunctionThatUnsafelyThrowsUnchecked(final ToDoubleFunctionWithThrowable<T, E> todoublefunctionwiththrowable) throws E {
        return todoublefunctionwiththrowable.thatUnsafelyThrowsUnchecked();
    }

    /**
     * Utility method to convert ToDoubleFunctionWithThrowable
     * @param todoublefunction The interface instance
     * @param <T> Generic that corresponds to the same generic on ToDoubleFunction  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, E extends Throwable> ToDoubleFunctionWithThrowable<T, E> asToDoubleFunctionWithThrowable(final ToDoubleFunction<T> todoublefunction) {
        return todoublefunction::applyAsDouble;
    }

    /**
     * Overridden method of ToDoubleFunctionWithThrowable that will call applyAsDoubleWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @return the value
     */
    @Override
    default double applyAsDouble(final T v1) {
        try {
            return applyAsDoubleWithThrowable(v1);
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
    double applyAsDoubleWithThrowable(final T v1) throws E;


    /**
     * @return An interface that will wrap the result in an optional, and return an empty optional when an exception occurs.
     */
    default java.util.function.Function<T, java.util.OptionalDouble>     thatReturnsOptional() {
      return (v1)     -> {
        try {
          return java.util.OptionalDouble.of(applyAsDoubleWithThrowable(v1));
        } catch(Throwable throwable) {
          return java.util.OptionalDouble.empty();
        }
      };
    }


    /**
     * @param defaultReturnValue A value to return if any throwable is caught.
     * @return An interface that returns a default value if any exception occurs.
     */
    default ToDoubleFunction<T> thatReturnsOnCatch(final double defaultReturnValue) {
      return (final T v1) -> {
        try {
          return applyAsDoubleWithThrowable(v1);
        } catch(final Throwable throwable) {
          return defaultReturnValue;
        }
      };
    }


    /**
     * @throws E if an exception E has been thrown, it is rethrown by this method
     * @return An interface that is only returned if no exception has been thrown.
     */
    default ToDoubleFunction<T> thatUnsafelyThrowsUnchecked() throws E {
      return (final T v1) -> {
        try {
          return applyAsDoubleWithThrowable(v1);
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
    default ToDoubleFunctionWithThrowable<T, E> withLogging(final Logger logger, final String message) {
        return (final T v1) -> {
            try {
                return applyAsDoubleWithThrowable(v1);
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
    default ToDoubleFunctionWithThrowable<T, E> withLogging(final Logger logger) {
        return withLogging(logger, "Exception in ToDoubleFunctionWithThrowable with the argument [{}]");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default ToDoubleFunctionWithThrowable<T, E> withLogging() {
        return withLogging(LoggerFactory.getLogger(getClass()));
    }



    /**
     * @param consumer An exception consumer.
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default ToDoubleFunctionWithThrowable<T, E> onException(final Consumer<Throwable> consumer) {
        return (final T v1) -> {
            try {
                return applyAsDoubleWithThrowable(v1);
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
    default ToDoubleFunctionWithThrowable<T, E> onException(final java.util.function.BiConsumer<Throwable, Object[]> consumer) {
        return (final T v1) -> {
            try {
                return applyAsDoubleWithThrowable(v1);
            } catch (final Throwable throwable) {
                consumer.accept(throwable, new Object[]{v1});
                throw throwable;
            }
        };
    }
}
