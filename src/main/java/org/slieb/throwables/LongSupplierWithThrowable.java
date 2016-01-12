package org.slieb.throwables;

/**
 * Generated from java.util.function.LongSupplier
 * Extends java.util.function.LongSupplier to allow for a checked exception.
 *
 * @param <E> The extension
 */
@FunctionalInterface
public interface LongSupplierWithThrowable<E extends Throwable> extends java.util.function.LongSupplier {
    /**
     * Utility method to mark lambdas of type LongSupplierWithThrowable
     * @param longsupplierwiththrowable The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> LongSupplierWithThrowable<E> castLongSupplierWithThrowable(LongSupplierWithThrowable<E> longsupplierwiththrowable) {
        return longsupplierwiththrowable;
    }

    /** 
     * Overridden method of LongSupplierWithThrowable that will call getAsLongWithThrowable, but catching any exceptions.
     *
     * @return the value
     */
    @Override
    default long getAsLong() {
        try {
            return getAsLongWithThrowable();
        } catch (final RuntimeException | Error exception) {
            throw exception;
        } catch (final Throwable throwable) {
            throw new org.slieb.throwables.SuppressedException(throwable);
        }
    }

    /** 
     * Functional method that will throw exceptions.
     *
     * @return the value
     * @throws E some exception
     */
    long getAsLongWithThrowable() throws E;


    /**
     * 
     */
    default LongSupplierWithThrowable<E> withLogging(java.util.logging.Logger logger, java.util.logging.Level level) {
        return () -> {
            try {
                return getAsLongWithThrowable();
            } catch (final Throwable throwable) {
                logger.log(level, "exception in LongSupplierWithThrowable", throwable);
                throw throwable;
            }
        };
    }


    /**
     * 
     */
    default LongSupplierWithThrowable<E> withLogging(java.util.logging.Logger logger) {
  return withLogging(logger, java.util.logging.Level.WARNING);
}

    default LongSupplierWithThrowable<E> withLogging() {
  return withLogging(java.util.logging.Logger.getGlobal());
}

}
