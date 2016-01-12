package org.slieb.throwables;

/**
 * Generated from java.util.function.DoubleSupplier
 * Extends java.util.function.DoubleSupplier to allow for a checked exception.
 *
 * @param <E> The extension
 */
@FunctionalInterface
public interface DoubleSupplierWithThrowable<E extends Throwable> extends java.util.function.DoubleSupplier {
    /**
     * Utility method to mark lambdas of type DoubleSupplierWithThrowable
     * @param doublesupplierwiththrowable The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> DoubleSupplierWithThrowable<E> castDoubleSupplierWithThrowable(DoubleSupplierWithThrowable<E> doublesupplierwiththrowable) {
        return doublesupplierwiththrowable;
    }

    /** 
     * Overridden method of DoubleSupplierWithThrowable that will call getAsDoubleWithThrowable, but catching any exceptions.
     *
     * @return the value
     */
    @Override
    default double getAsDouble() {
        try {
            return getAsDoubleWithThrowable();
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
    double getAsDoubleWithThrowable() throws E;


    /**
     * 
     */
    default DoubleSupplierWithThrowable<E> withLogging(java.util.logging.Logger logger, java.util.logging.Level level) {
        return () -> {
            try {
                return getAsDoubleWithThrowable();
            } catch (final Throwable throwable) {
                logger.log(level, "exception in DoubleSupplierWithThrowable", throwable);
                throw throwable;
            }
        };
    }


    /**
     * 
     */
    default DoubleSupplierWithThrowable<E> withLogging(java.util.logging.Logger logger) {
  return withLogging(logger, java.util.logging.Level.WARNING);
}

    default DoubleSupplierWithThrowable<E> withLogging() {
  return withLogging(java.util.logging.Logger.getGlobal());
}

}
