package org.slieb.throwables;

import java.lang.FunctionalInterface;
import java.lang.SuppressWarnings;
import java.lang.Throwable;
import java.util.function.Consumer;
import java.util.function.LongToDoubleFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Generated from LongToDoubleFunction
 * Extends java.util.function.LongToDoubleFunction to allow for a checked exception.
 *
 * @param <E> The extension
 */
@FunctionalInterface
@SuppressWarnings({"WeakerAccess"})
public interface LongToDoubleFunctionWithThrowable<E extends Throwable> extends LongToDoubleFunction {

    /**
     * Utility method to mark lambdas of type LongToDoubleFunctionWithThrowable
     *
     * @param longtodoublefunctionwiththrowable The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> LongToDoubleFunctionWithThrowable<E> castLongToDoubleFunctionWithThrowable(final LongToDoubleFunctionWithThrowable<E> longtodoublefunctionwiththrowable) {
        return longtodoublefunctionwiththrowable;
    }

    /**
     * Utility method to unwrap lambdas of type LongToDoubleFunction and rethrow any Exception
     *
     * @param longtodoublefunctionwiththrowable The interface instance
     * @param <E> The type this interface is allowed to throw
     * @throws E the original Exception from longtodoublefunctionwiththrowable
     * @return the cast interface
     */
    static <E extends Throwable> LongToDoubleFunction rethrowLongToDoubleFunction(final LongToDoubleFunctionWithThrowable<E> longtodoublefunctionwiththrowable) throws E {
        return longtodoublefunctionwiththrowable.rethrow();
    }

    /**
     * Utility method to convert LongToDoubleFunctionWithThrowable
     * @param longtodoublefunction The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> LongToDoubleFunctionWithThrowable<E> asLongToDoubleFunctionWithThrowable(final LongToDoubleFunction longtodoublefunction) {
        return longtodoublefunction::applyAsDouble;
    }

    /**
     * Overridden method of LongToDoubleFunctionWithThrowable that will call applyAsDoubleWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @return the value
     */
    @Override
    default double applyAsDouble(final long v1) {
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
    double applyAsDoubleWithThrowable(final long v1) throws E;


    /**
     * @param defaultReturnValue A value to return if any throwable is caught.
     * @return An interface that returns a default value if any exception occurs.
     */
    default LongToDoubleFunction thatReturnsOnCatch(final double defaultReturnValue) {
      return (final long v1) -> {
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
    default LongToDoubleFunction rethrow() throws E {
      return (final long v1) -> {
        try {
          return applyAsDoubleWithThrowable(v1);
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
    default LongToDoubleFunctionWithThrowable<E> withLogging(final Logger logger, final String message) {
        return (final long v1) -> {
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
    default LongToDoubleFunctionWithThrowable<E> withLogging(final Logger logger) {
        return withLogging(logger, "Exception in LongToDoubleFunctionWithThrowable with the argument [{}]");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default LongToDoubleFunctionWithThrowable<E> withLogging() {
        return withLogging(LoggerFactory.getLogger(getClass()));
    }



    /**
     * @param consumer An exception consumer.
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default LongToDoubleFunctionWithThrowable<E> onException(final Consumer<Throwable> consumer) {
        return (final long v1) -> {
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
    default LongToDoubleFunctionWithThrowable<E> onException(final java.util.function.BiConsumer<Throwable, Object[]> consumer) {
        return (final long v1) -> {
            try {
                return applyAsDoubleWithThrowable(v1);
            } catch (final Throwable throwable) {
                consumer.accept(throwable, new Object[]{v1});
                throw throwable;
            }
        };
    }
}
