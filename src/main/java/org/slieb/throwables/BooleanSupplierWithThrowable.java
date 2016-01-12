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
     * @param booleansupplierwiththrowable The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> BooleanSupplierWithThrowable<E> castBooleanSupplierWithThrowable(BooleanSupplierWithThrowable<E> booleansupplierwiththrowable) {
        return booleansupplierwiththrowable;
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
     * 
     */
    default BooleanSupplierWithThrowable<E> withLogging(java.util.logging.Logger logger, java.util.logging.Level level) {
        return () -> {
            try {
                return getAsBooleanWithThrowable();
            } catch (final Throwable throwable) {
                logger.log(level, "exception in BooleanSupplierWithThrowable", throwable);
                throw throwable;
            }
        };
    }


    /**
     * 
     */
    default BooleanSupplierWithThrowable<E> withLogging(java.util.logging.Logger logger) {
  return withLogging(logger, java.util.logging.Level.WARNING);
}

    default BooleanSupplierWithThrowable<E> withLogging() {
  return withLogging(java.util.logging.Logger.getGlobal());
}

}
