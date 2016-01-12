package org.slieb.throwables;

/**
 * Generated from java.util.function.IntSupplier
 * Extends java.util.function.IntSupplier to allow for a checked exception.
 *
 * @param <E> The extension
 */
@FunctionalInterface
public interface IntSupplierWithThrowable<E extends Throwable> extends java.util.function.IntSupplier {
    /**
     * Utility method to mark lambdas of type IntSupplierWithThrowable
     * @param intsupplierwiththrowable The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> IntSupplierWithThrowable<E> castIntSupplierWithThrowable(IntSupplierWithThrowable<E> intsupplierwiththrowable) {
        return intsupplierwiththrowable;
    }
    /**
     * Utility method to convert IntSupplierWithThrowable
     * @param intsupplier The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> IntSupplierWithThrowable<E> asIntSupplierWithThrowable(java.util.function.IntSupplier intsupplier) {
        return intsupplier::getAsInt;
    }

    /** 
     * Overridden method of IntSupplierWithThrowable that will call getAsIntWithThrowable, but catching any exceptions.
     *
     * @return the value
     */
    @Override
    default int getAsInt() {
        try {
            return getAsIntWithThrowable();
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
    int getAsIntWithThrowable() throws E;


    /**
     * @param logger The logger to log exceptions on
     * @param level The log level to use when logging exceptions
     * @param message A message to use for logging exceptions
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default IntSupplierWithThrowable<E> withLogging(java.util.logging.Logger logger, java.util.logging.Level level, String message) {
        return () -> {
            try {
                return getAsIntWithThrowable();
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
    default IntSupplierWithThrowable<E> withLogging(java.util.logging.Logger logger) {
        return withLogging(logger, java.util.logging.Level.WARNING, "Exception in IntSupplierWithThrowable");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default IntSupplierWithThrowable<E> withLogging() {
        return withLogging(java.util.logging.Logger.getGlobal());
    }

}
