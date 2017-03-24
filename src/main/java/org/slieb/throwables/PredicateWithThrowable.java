package org.slieb.throwables;

import java.lang.FunctionalInterface;
import java.lang.SuppressWarnings;
import java.lang.Throwable;
import java.util.function.Consumer;
import java.util.function.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Generated from Predicate
 * Extends java.util.function.Predicate to allow for a checked exception.
 *
 * @param <T> some generic flag
 * @param <E> The extension
 */
@FunctionalInterface
@SuppressWarnings({"WeakerAccess"})
public interface PredicateWithThrowable<T, E extends Throwable> extends Predicate<T> {

    /**
     * Utility method to mark lambdas of type PredicateWithThrowable
     *
     * @param predicatewiththrowable The interface instance
     * @param <T> Generic that corresponds to the same generic on Predicate  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, E extends Throwable> PredicateWithThrowable<T, E> castPredicateWithThrowable(final PredicateWithThrowable<T, E> predicatewiththrowable) {
        return predicatewiththrowable;
    }

    /**
     * Utility method to unwrap lambdas of type Predicate and withUncheckedThrowable any Exception
     *
     * @param predicatewiththrowable The interface instance
     * @param <T> Generic that corresponds to the same generic on Predicate  
     * @param <E> The type this interface is allowed to throw
     * @throws E the original Exception from predicatewiththrowable
     * @return the cast interface
     */
    static <T, E extends Throwable> Predicate<T> aPredicateThatUnsafelyThrowsUnchecked(final PredicateWithThrowable<T, E> predicatewiththrowable) throws E {
        return predicatewiththrowable.thatUnsafelyThrowsUnchecked();
    }

    /**
     * Utility method to convert PredicateWithThrowable
     * @param predicate The interface instance
     * @param <T> Generic that corresponds to the same generic on Predicate  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, E extends Throwable> PredicateWithThrowable<T, E> asPredicateWithThrowable(final Predicate<T> predicate) {
        return predicate::test;
    }

    /**
     * Overridden method of PredicateWithThrowable that will call testWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @return the value
     */
    @Override
    default boolean test(final T v1) {
        try {
            return testWithThrowable(v1);
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
    boolean testWithThrowable(final T v1) throws E;


    /**
     * @param defaultReturnValue A value to return if any throwable is caught.
     * @return An interface that returns a default value if any exception occurs.
     */
    default Predicate<T> thatReturnsOnCatch(final boolean defaultReturnValue) {
      return (final T v1) -> {
        try {
          return testWithThrowable(v1);
        } catch(final Throwable throwable) {
          return defaultReturnValue;
        }
      };
    }


    /**
     * @throws E if an exception E has been thrown, it is rethrown by this method
     * @return An interface that is only returned if no exception has been thrown.
     */
    default Predicate<T> thatUnsafelyThrowsUnchecked() throws E {
      return (final T v1) -> {
        try {
          return testWithThrowable(v1);
        } catch(final Throwable throwable) {
           SuppressedException.throwUnsafelyAsUnchecked(throwable);
           return false;        }
      };
    }


    /**
     * @param logger The logger to log exceptions on
     * @param message A message to use for logging exceptions
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default PredicateWithThrowable<T, E> withLogging(final Logger logger, final String message) {
        return (final T v1) -> {
            try {
                return testWithThrowable(v1);
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
    default PredicateWithThrowable<T, E> withLogging(final Logger logger) {
        return withLogging(logger, "Exception in PredicateWithThrowable with the argument [{}]");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default PredicateWithThrowable<T, E> withLogging() {
        return withLogging(LoggerFactory.getLogger(getClass()));
    }



    /**
     * @param consumer An exception consumer.
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default PredicateWithThrowable<T, E> onException(final Consumer<Throwable> consumer) {
        return (final T v1) -> {
            try {
                return testWithThrowable(v1);
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
    default PredicateWithThrowable<T, E> onException(final java.util.function.BiConsumer<Throwable, Object[]> consumer) {
        return (final T v1) -> {
            try {
                return testWithThrowable(v1);
            } catch (final Throwable throwable) {
                consumer.accept(throwable, new Object[]{v1});
                throw throwable;
            }
        };
    }
}
