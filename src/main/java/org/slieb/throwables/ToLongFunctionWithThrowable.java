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
     *
     * @param tolongfunctionwiththrowable The interface instance
     * @param <T> Generic that corresponds to the same generic on ToLongFunction  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, E extends Throwable> ToLongFunctionWithThrowable<T, E> castToLongFunctionWithThrowable(ToLongFunctionWithThrowable<T, E> tolongfunctionwiththrowable) {
        return tolongfunctionwiththrowable;
    }
    /**
     * Utility method to convert ToLongFunctionWithThrowable
     * @param tolongfunction The interface instance
     * @param <T> Generic that corresponds to the same generic on ToLongFunction  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, E extends Throwable> ToLongFunctionWithThrowable<T, E> asToLongFunctionWithThrowable(java.util.function.ToLongFunction<T> tolongfunction) {
        return tolongfunction::applyAsLong;
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
     * @param logger The logger to log exceptions on
     * @param message A message to use for logging exceptions
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default ToLongFunctionWithThrowable<T, E> withLogging(org.slf4j.Logger logger, String message) {
        return (v1) -> {
            try {
                return applyAsLongWithThrowable(v1);
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
    default ToLongFunctionWithThrowable<T, E> withLogging(org.slf4j.Logger logger) {
        return withLogging(logger, "Exception in ToLongFunctionWithThrowable");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default ToLongFunctionWithThrowable<T, E> withLogging() {
        return withLogging(org.slf4j.LoggerFactory.getLogger(getClass()));
    }

}
