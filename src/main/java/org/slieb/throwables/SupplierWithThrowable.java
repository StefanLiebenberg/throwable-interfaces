package org.slieb.throwables;

/**
 * Generated from java.util.function.Supplier
 * Extends java.util.function.Supplier to allow for a checked exception.
 *
 * @param <T> some generic flag
 * @param <E> The extension
 */
@FunctionalInterface
public interface SupplierWithThrowable<T, E extends Throwable> extends java.util.function.Supplier<T> {
    /**
     * Utility method to mark lambdas of type SupplierWithThrowable
     * @param supplierwiththrowable The interface instance
     * @param <T> Generic that corresponds to the same generic on Supplier  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, E extends Throwable> SupplierWithThrowable<T, E> castSupplierWithThrowable(SupplierWithThrowable<T, E> supplierwiththrowable) {
        return supplierwiththrowable;
    }

    /** 
     * Overridden method of SupplierWithThrowable that will call getWithThrowable, but catching any exceptions.
     *
     * @return the value
     */
    @Override
    default T get() {
        try {
            return getWithThrowable();
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
    T getWithThrowable() throws E;
default java.util.function.Supplier<java.util.Optional<T>> thatReturnsOptional() {
  return () -> {
    try {
      return java.util.Optional.of(getWithThrowable());
    } catch(Throwable throwable) {
      return java.util.Optional.empty();
    }
  };
}
default java.util.function.Supplier<T> thatReturnsDefaultValue(T defaultReturnValue) {
  return () -> {
    try {
      return getWithThrowable();
    } catch(Throwable throwable) {
      return defaultReturnValue;
    }
  };
}


    /**
     * 
     */
    default SupplierWithThrowable<T, E> withLogging(java.util.logging.Logger logger, java.util.logging.Level level) {
        return () -> {
            try {
                return getWithThrowable();
            } catch (final Throwable throwable) {
                logger.log(level, "exception in SupplierWithThrowable", throwable);
                throw throwable;
            }
        };
    }


    /**
     * 
     */
    default SupplierWithThrowable<T, E> withLogging(java.util.logging.Logger logger) {
  return withLogging(logger, java.util.logging.Level.WARNING);
}

    default SupplierWithThrowable<T, E> withLogging() {
  return withLogging(java.util.logging.Logger.getGlobal());
}

}
