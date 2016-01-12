package org.slieb.throwables;

/**
 * Generated from java.util.function.LongPredicate
 * Extends java.util.function.LongPredicate to allow for a checked exception.
 *
 * @param <E> The extension
 */
@FunctionalInterface
public interface LongPredicateWithThrowable<E extends Throwable> extends java.util.function.LongPredicate {
    /**
     * Utility method to mark lambdas of type LongPredicateWithThrowable
     * @param longpredicatewiththrowable The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> LongPredicateWithThrowable<E> castLongPredicateWithThrowable(LongPredicateWithThrowable<E> longpredicatewiththrowable) {
        return longpredicatewiththrowable;
    }
    /**
     * Utility method to convert LongPredicateWithThrowable
     * @param longpredicate The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> LongPredicateWithThrowable<E> asLongPredicateWithThrowable(java.util.function.LongPredicate longpredicate) {
        return longpredicate::test;
    }

    /** 
     * Overridden method of LongPredicateWithThrowable that will call testWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @return the value
     */
    @Override
    default boolean test(long v1) {
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
    boolean testWithThrowable(long v1) throws E;


    /**
     * @param logger The logger to log exceptions on
     * @param level The log level to use when logging exceptions
     * @param message A message to use for logging exceptions
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default LongPredicateWithThrowable<E> withLogging(java.util.logging.Logger logger, java.util.logging.Level level, String message) {
        return (v1) -> {
            try {
                return testWithThrowable(v1);
            } catch (final Throwable throwable) {
                logger.log(level, message, throwable);
                throw throwable;
            }
        };
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @param logger The logger instance to log exceptions on
     * @return An interface that will log exceptions on given logger
     */
    default LongPredicateWithThrowable<E> withLogging(java.util.logging.Logger logger) {
        return withLogging(logger, java.util.logging.Level.WARNING, "Exception in LongPredicateWithThrowable");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default LongPredicateWithThrowable<E> withLogging() {
        return withLogging(java.util.logging.Logger.getGlobal());
    }

}
