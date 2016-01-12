package org.slieb.throwables;

/**
 * Generated from java.util.function.IntPredicate
 * Extends java.util.function.IntPredicate to allow for a checked exception.
 *
 * @param <E> The extension
 */
@FunctionalInterface
public interface IntPredicateWithThrowable<E extends Throwable> extends java.util.function.IntPredicate {
    /**
     * Utility method to mark lambdas of type IntPredicateWithThrowable
     * @param intpredicatewiththrowable The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> IntPredicateWithThrowable<E> castIntPredicateWithThrowable(IntPredicateWithThrowable<E> intpredicatewiththrowable) {
        return intpredicatewiththrowable;
    }

    /** 
     * Overridden method of IntPredicateWithThrowable that will call testWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @return the value
     */
    @Override
    default boolean test(int v1) {
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
    boolean testWithThrowable(int v1) throws E;


    /**
     * 
     */
    default IntPredicateWithThrowable<E> withLogging(java.util.logging.Logger logger, java.util.logging.Level level) {
        return (v1) -> {
            try {
                return testWithThrowable(v1);
            } catch (final Throwable throwable) {
                logger.log(level, "exception in IntPredicateWithThrowable", throwable);
                throw throwable;
            }
        };
    }


    /**
     * 
     */
    default IntPredicateWithThrowable<E> withLogging(java.util.logging.Logger logger) {
  return withLogging(logger, java.util.logging.Level.WARNING);
}

    default IntPredicateWithThrowable<E> withLogging() {
  return withLogging(java.util.logging.Logger.getGlobal());
}

}
