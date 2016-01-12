package org.slieb.throwables;

/**
 * Generated from java.util.function.ToLongFunction
 * Extends java.util.function.ToLongFunction to allow for a checked exception.
 *
 * @param <T> some generic flag
 * @param <E> The extension
 */
@FunctionalInterface
public interface ToLongFunctionWithThrowable<T, E extends Throwable> extends java.util.function.ToLongFunction<T> {
    /**
     * Utility method to mark lambdas of type ToLongFunctionWithThrowable
     * @param tolongfunctionwiththrowable The interface instance
     * @param <T> Generic that corresponds to the same generic on ToLongFunction  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, E extends Throwable> ToLongFunctionWithThrowable<T, E> castToLongFunctionWithThrowable(ToLongFunctionWithThrowable<T, E> tolongfunctionwiththrowable) {
        return tolongfunctionwiththrowable;
    }

    /** 
     * Overridden method of ToLongFunctionWithThrowable that will call applyAsLongWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @return the value
     */
    @Override
    default long applyAsLong(T v1) {
        try {
            return applyAsLongWithThrowable(v1);
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
    long applyAsLongWithThrowable(T v1) throws E;


    /**
     * 
     */
    default ToLongFunctionWithThrowable<T, E> withLogging(java.util.logging.Logger logger, java.util.logging.Level level) {
        return (v1) -> {
            try {
                return applyAsLongWithThrowable(v1);
            } catch (final Throwable throwable) {
                logger.log(level, "exception in ToLongFunctionWithThrowable", throwable);
                throw throwable;
            }
        };
    }


    /**
     * 
     */
    default ToLongFunctionWithThrowable<T, E> withLogging(java.util.logging.Logger logger) {
  return withLogging(logger, java.util.logging.Level.WARNING);
}

    default ToLongFunctionWithThrowable<T, E> withLogging() {
  return withLogging(java.util.logging.Logger.getGlobal());
}

}
