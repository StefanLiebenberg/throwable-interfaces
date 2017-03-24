package org.slieb.throwables;

import java.lang.FunctionalInterface;
import java.lang.SuppressWarnings;
import java.lang.Throwable;
import java.util.function.Consumer;
import java.util.function.IntPredicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Generated from IntPredicate
 * Extends java.util.function.IntPredicate to allow for a checked exception.
 *
 * @param <E> The extension
 */
@FunctionalInterface
@SuppressWarnings({"WeakerAccess"})
public interface IntPredicateWithThrowable<E extends Throwable> extends IntPredicate {

    /**
     * Utility method to mark lambdas of type IntPredicateWithThrowable
     *
     * @param intpredicatewiththrowable The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> IntPredicateWithThrowable<E> castIntPredicateWithThrowable(final IntPredicateWithThrowable<E> intpredicatewiththrowable) {
        return intpredicatewiththrowable;
    }

    /**
     * Utility method to unwrap lambdas of type IntPredicate and withUncheckedThrowable any Exception
     *
     * @param intpredicatewiththrowable The interface instance
     * @param <E> The type this interface is allowed to throw
     * @throws E the original Exception from intpredicatewiththrowable
     * @return the cast interface
     */
    static <E extends Throwable> IntPredicate aIntPredicateThatUnsafelyThrowsUnchecked(final IntPredicateWithThrowable<E> intpredicatewiththrowable) throws E {
        return intpredicatewiththrowable.thatUnsafelyThrowsUnchecked();
    }

    /**
     * Utility method to convert IntPredicateWithThrowable
     * @param intpredicate The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> IntPredicateWithThrowable<E> asIntPredicateWithThrowable(final IntPredicate intpredicate) {
        return intpredicate::test;
    }

    /**
     * Overridden method of IntPredicateWithThrowable that will call testWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @return the value
     */
    @Override
    default boolean test(final int v1) {
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
    boolean testWithThrowable(final int v1) throws E;


    /**
     * @param defaultReturnValue A value to return if any throwable is caught.
     * @return An interface that returns a default value if any exception occurs.
     */
    default IntPredicate thatReturnsOnCatch(final boolean defaultReturnValue) {
      return (final int v1) -> {
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
    default IntPredicate thatUnsafelyThrowsUnchecked() throws E {
      return (final int v1) -> {
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
    default IntPredicateWithThrowable<E> withLogging(final Logger logger, final String message) {
        return (final int v1) -> {
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
    default IntPredicateWithThrowable<E> withLogging(final Logger logger) {
        return withLogging(logger, "Exception in IntPredicateWithThrowable with the argument [{}]");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default IntPredicateWithThrowable<E> withLogging() {
        return withLogging(LoggerFactory.getLogger(getClass()));
    }



    /**
     * @param consumer An exception consumer.
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default IntPredicateWithThrowable<E> onException(final Consumer<Throwable> consumer) {
        return (final int v1) -> {
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
    default IntPredicateWithThrowable<E> onException(final java.util.function.BiConsumer<Throwable, Object[]> consumer) {
        return (final int v1) -> {
            try {
                return testWithThrowable(v1);
            } catch (final Throwable throwable) {
                consumer.accept(throwable, new Object[]{v1});
                throw throwable;
            }
        };
    }
}
