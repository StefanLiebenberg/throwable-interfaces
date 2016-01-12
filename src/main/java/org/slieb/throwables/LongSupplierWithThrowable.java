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
     *
     * @param longsupplierwiththrowable The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> LongSupplierWithThrowable<E> castLongSupplierWithThrowable(LongSupplierWithThrowable<E> longsupplierwiththrowable) {
        return longsupplierwiththrowable;
    }
    /**
     * Utility method to convert LongSupplierWithThrowable
     * @param longsupplier The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> LongSupplierWithThrowable<E> asLongSupplierWithThrowable(java.util.function.LongSupplier longsupplier) {
        return longsupplier::getAsLong;
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
     * @param logger The logger to log exceptions on
     * @param message A message to use for logging exceptions
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default LongSupplierWithThrowable<E> withLogging(org.slf4j.Logger logger, String message) {
        return () -> {
            try {
                return getAsLongWithThrowable();
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
    default LongSupplierWithThrowable<E> withLogging(org.slf4j.Logger logger) {
        return withLogging(logger, "Exception in LongSupplierWithThrowable");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default LongSupplierWithThrowable<E> withLogging() {
        return withLogging(org.slf4j.LoggerFactory.getLogger(getClass()));
    }

}