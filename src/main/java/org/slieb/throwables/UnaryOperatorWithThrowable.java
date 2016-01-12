package org.slieb.throwables;

/**
 * Generated from java.util.function.UnaryOperator
 * Extends java.util.function.UnaryOperator to allow for a checked exception.
 *
 * @param <T> some generic flag
 * @param <E> The extension
 */
@FunctionalInterface
public interface UnaryOperatorWithThrowable<T, E extends Throwable> extends java.util.function.UnaryOperator<T> {
    /**
     * Utility method to mark lambdas of type UnaryOperatorWithThrowable
     * @param unaryoperatorwiththrowable The interface instance
     * @param <T> Generic that corresponds to the same generic on UnaryOperator  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, E extends Throwable> UnaryOperatorWithThrowable<T, E> castUnaryOperatorWithThrowable(UnaryOperatorWithThrowable<T, E> unaryoperatorwiththrowable) {
        return unaryoperatorwiththrowable;
    }

    /** 
     * Overridden method of UnaryOperatorWithThrowable that will call applyWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @return the value
     */
    @Override
    default T apply(T v1) {
        try {
            return applyWithThrowable(v1);
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
    T applyWithThrowable(T v1) throws E;
default java.util.function.UnaryOperator<T> thatReturnsDefaultValue(T defaultReturnValue) {
  return (v1) -> {
    try {
      return applyWithThrowable(v1);
    } catch(Throwable throwable) {
      return defaultReturnValue;
    }
  };
}


    /**
     * 
     */
    default UnaryOperatorWithThrowable<T, E> withLogging(java.util.logging.Logger logger, java.util.logging.Level level) {
        return (v1) -> {
            try {
                return applyWithThrowable(v1);
            } catch (final Throwable throwable) {
                logger.log(level, "exception in UnaryOperatorWithThrowable", throwable);
                throw throwable;
            }
        };
    }


    /**
     * 
     */
    default UnaryOperatorWithThrowable<T, E> withLogging(java.util.logging.Logger logger) {
  return withLogging(logger, java.util.logging.Level.WARNING);
}

    default UnaryOperatorWithThrowable<T, E> withLogging() {
  return withLogging(java.util.logging.Logger.getGlobal());
}

}
