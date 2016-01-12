package org.slieb.throwables;

/**
 * Generated from java.util.function.Predicate
 * Extends java.util.function.Predicate to allow for a checked exception.
 *
 * @param <T> some generic flag
 * @param <E> The extension
 */
@FunctionalInterface
public interface PredicateWithThrowable<T, E extends Throwable> extends java.util.function.Predicate<T> {


    /**
     * Utility method to mark lambdas of type PredicateWithThrowable
     *
     * @param predicatewiththrowable The interface instance
     * @param <T> Generic that corresponds to the same generic on Predicate  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, E extends Throwable> PredicateWithThrowable<T, E> castPredicateWithThrowable(PredicateWithThrowable<T, E> predicatewiththrowable) {
        return predicatewiththrowable;
    }
    /**
     * Utility method to convert PredicateWithThrowable
     * @param predicate The interface instance
     * @param <T> Generic that corresponds to the same generic on Predicate  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, E extends Throwable> PredicateWithThrowable<T, E> asPredicateWithThrowable(java.util.function.Predicate<T> predicate) {
        return predicate::test;
    }

    /** 
     * Overridden method of PredicateWithThrowable that will call testWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @return the value
     */
    @Override
    default boolean test(T v1) {
        try {
            return testWithThrowable(v1);
        } catch (final RuntimeException | Error exception) {
            throw exception;
        } catch (final Throwable throwable) {
            throw new org.slieb.throwables.SuppressedException(throwable);
        }
    }

    /** 
     * Functional method that will throw exceptions.
     *
     * @param v1 parameter to overridden method
     * @return the value
     * @throws E some exception
     */
    boolean testWithThrowable(T v1) throws E;


    /**
     * @param logger The logger to log exceptions on
     * @param message A message to use for logging exceptions
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default PredicateWithThrowable<T, E> withLogging(org.slf4j.Logger logger, String message) {
        return (v1) -> {
            try {
                return testWithThrowable(v1);
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
    default PredicateWithThrowable<T, E> withLogging(org.slf4j.Logger logger) {
        return withLogging(logger, "Exception in PredicateWithThrowable");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default PredicateWithThrowable<T, E> withLogging() {
        return withLogging(org.slf4j.LoggerFactory.getLogger(getClass()));
    }

}
