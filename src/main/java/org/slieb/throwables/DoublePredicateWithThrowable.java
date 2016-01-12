package org.slieb.throwables;

/**
 * Generated from java.util.function.DoublePredicate
 * Extends java.util.function.DoublePredicate to allow for a checked exception.
 *
 * @param <E> The extension
 */
@FunctionalInterface
public interface DoublePredicateWithThrowable<E extends Throwable> extends java.util.function.DoublePredicate {
    /**
     * Utility method to mark lambdas of type DoublePredicateWithThrowable
     * @param doublepredicatewiththrowable The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> DoublePredicateWithThrowable<E> castDoublePredicateWithThrowable(DoublePredicateWithThrowable<E> doublepredicatewiththrowable) {
        return doublepredicatewiththrowable;
    }
    /**
     * Utility method to convert DoublePredicateWithThrowable
     * @param doublepredicate The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> DoublePredicateWithThrowable<E> asDoublePredicateWithThrowable(java.util.function.DoublePredicate doublepredicate) {
        return doublepredicate::test;
    }

    /** 
     * Overridden method of DoublePredicateWithThrowable that will call testWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @return the value
     */
    @Override
    default boolean test(double v1) {
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
    boolean testWithThrowable(double v1) throws E;


    /**
     * @param logger The logger to log exceptions on
     * @param level The log level to use when logging exceptions
     * @param message A message to use for logging exceptions
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default DoublePredicateWithThrowable<E> withLogging(java.util.logging.Logger logger, java.util.logging.Level level, String message) {
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
    default DoublePredicateWithThrowable<E> withLogging(java.util.logging.Logger logger) {
        return withLogging(logger, java.util.logging.Level.WARNING, "Exception in DoublePredicateWithThrowable");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default DoublePredicateWithThrowable<E> withLogging() {
        return withLogging(java.util.logging.Logger.getGlobal());
    }

}
