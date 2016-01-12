package org.slieb.throwables;

/**
 * Generated from java.util.function.ToLongBiFunction
 * Extends java.util.function.ToLongBiFunction to allow for a checked exception.
 *
 * @param <T> some generic flag
 * @param <U> some generic flag
 * @param <E> The extension
 */
@FunctionalInterface
public interface ToLongBiFunctionWithThrowable<T, U, E extends Throwable> extends java.util.function.ToLongBiFunction<T, U> {
    /**
     * Utility method to mark lambdas of type ToLongBiFunctionWithThrowable
     * @param tolongbifunctionwiththrowable The interface instance
     * @param <T> Generic that corresponds to the same generic on ToLongBiFunction  
     * @param <U> Generic that corresponds to the same generic on ToLongBiFunction  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, U, E extends Throwable> ToLongBiFunctionWithThrowable<T, U, E> castToLongBiFunctionWithThrowable(ToLongBiFunctionWithThrowable<T, U, E> tolongbifunctionwiththrowable) {
        return tolongbifunctionwiththrowable;
    }
    /**
     * Utility method to convert ToLongBiFunctionWithThrowable
     * @param tolongbifunction The interface instance
     * @param <T> Generic that corresponds to the same generic on ToLongBiFunction  
     * @param <U> Generic that corresponds to the same generic on ToLongBiFunction  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, U, E extends Throwable> ToLongBiFunctionWithThrowable<T, U, E> asToLongBiFunctionWithThrowable(java.util.function.ToLongBiFunction<T, U> tolongbifunction) {
        return tolongbifunction::applyAsLong;
    }

    /** 
     * Overridden method of ToLongBiFunctionWithThrowable that will call applyAsLongWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @param v2 parameter to overridden method
     * @return the value
     */
    @Override
    default long applyAsLong(T v1, U v2) {
        try {
            return applyAsLongWithThrowable(v1, v2);
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
     * @param v2 parameter to overridden method
     * @return the value
     * @throws E some exception
     */
    long applyAsLongWithThrowable(T v1, U v2) throws E;


    /**
     * @param logger The logger to log exceptions on
     * @param level The log level to use when logging exceptions
     * @param message A message to use for logging exceptions
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default ToLongBiFunctionWithThrowable<T, U, E> withLogging(java.util.logging.Logger logger, java.util.logging.Level level, String message) {
        return (v1, v2) -> {
            try {
                return applyAsLongWithThrowable(v1, v2);
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
    default ToLongBiFunctionWithThrowable<T, U, E> withLogging(java.util.logging.Logger logger) {
        return withLogging(logger, java.util.logging.Level.WARNING, "Exception in ToLongBiFunctionWithThrowable");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default ToLongBiFunctionWithThrowable<T, U, E> withLogging() {
        return withLogging(java.util.logging.Logger.getGlobal());
    }

}
