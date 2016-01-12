package org.slieb.throwables;

/**
 * Generated from java.util.function.BooleanSupplier
 * Extends java.util.function.BooleanSupplier to allow for a checked exception.
 *
 * @param <E> The extension
 */
@FunctionalInterface
public interface BooleanSupplierWithThrowable<E extends Throwable> extends java.util.function.BooleanSupplier {


    /**
     * Utility method to mark lambdas of type BooleanSupplierWithThrowable
     *
     * @param booleansupplierwiththrowable The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> BooleanSupplierWithThrowable<E> castBooleanSupplierWithThrowable(BooleanSupplierWithThrowable<E> booleansupplierwiththrowable) {
        return booleansupplierwiththrowable;
    }
    /**
     * Utility method to convert BooleanSupplierWithThrowable
     * @param booleansupplier The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> BooleanSupplierWithThrowable<E> asBooleanSupplierWithThrowable(java.util.function.BooleanSupplier booleansupplier) {
        return booleansupplier::getAsBoolean;
    }

    /** 
     * Overridden method of BooleanSupplierWithThrowable that will call getAsBooleanWithThrowable, but catching any exceptions.
     *
     * @return the value
     */
    @Override
    default boolean getAsBoolean() {
        try {
            return getAsBooleanWithThrowable();
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
    boolean getAsBooleanWithThrowable() throws E;


    /**
     * @param logger The logger to log exceptions on
     * @param message A message to use for logging exceptions
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default BooleanSupplierWithThrowable<E> withLogging(org.slf4j.Logger logger, String message) {
        return () -> {
            try {
                return getAsBooleanWithThrowable();
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
    default BooleanSupplierWithThrowable<E> withLogging(org.slf4j.Logger logger) {
        return withLogging(logger, "Exception in BooleanSupplierWithThrowable");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default BooleanSupplierWithThrowable<E> withLogging() {
        return withLogging(org.slf4j.LoggerFactory.getLogger(getClass()));
    }

}
