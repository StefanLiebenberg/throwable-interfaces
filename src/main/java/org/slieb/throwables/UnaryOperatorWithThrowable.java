package org.slieb.throwables;

import java.lang.Throwable;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;
import org.slf4j.Logger;
/**
 * Generated from UnaryOperator
 * Extends java.util.function.UnaryOperator to allow for a checked exception.
 *
 * @param <T> some generic flag
 * @param <E> The extension
 */
@FunctionalInterface
public interface UnaryOperatorWithThrowable<T, E extends Throwable> extends UnaryOperator<T> {


    /**
     * Utility method to mark lambdas of type UnaryOperatorWithThrowable
     *
     * @param unaryoperatorwiththrowable The interface instance
     * @param <T> Generic that corresponds to the same generic on UnaryOperator  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, E extends Throwable> UnaryOperatorWithThrowable<T, E> castUnaryOperatorWithThrowable(final UnaryOperatorWithThrowable<T, E> unaryoperatorwiththrowable) {
        return unaryoperatorwiththrowable;
    }
    /**
     * Utility method to convert UnaryOperatorWithThrowable
     * @param unaryoperator The interface instance
     * @param <T> Generic that corresponds to the same generic on UnaryOperator  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, E extends Throwable> UnaryOperatorWithThrowable<T, E> asUnaryOperatorWithThrowable(final UnaryOperator<T> unaryoperator) {
        return unaryoperator::apply;
    }

    /** 
     * Overridden method of UnaryOperatorWithThrowable that will call applyWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @return the value
     */
    @Override
    default T apply(final T v1) {
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
    T applyWithThrowable(final T v1) throws E;


    /**
     * @return An interface that returns a default value if any exception occurs.
     */
    default UnaryOperator<T> thatReturnsOnCatch(T defaultReturnValue) {
      return (v1) -> {
        try {
          return applyWithThrowable(v1);
        } catch(Throwable throwable) {
          return defaultReturnValue;
        }
      };
    }


    /**
     * @param logger The logger to log exceptions on
     * @param message A message to use for logging exceptions
     * @return An interface that will log all exceptions to given logger
     */
    default UnaryOperatorWithThrowable<T, E> withLogging(Logger logger, String message) {
        return (final T v1) -> {
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
    default UnaryOperatorWithThrowable<T, E> withLogging(org.slf4j.Logger logger) {
        return withLogging(logger, "Exception in UnaryOperatorWithThrowable with arguments {}");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default UnaryOperatorWithThrowable<T, E> withLogging() {
        return withLogging(org.slf4j.LoggerFactory.getLogger(getClass()));
    }



    /**
     * @param consumer An exception consumer.
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default UnaryOperatorWithThrowable<T, E> onException(Consumer<Throwable> consumer) {
        return (final T v1) -> {
            try {
                return applyWithThrowable(v1);
            } catch (final Throwable throwable) {
                consumer.accept(throwable);
                throw throwable;
            }
        };
    }
}
