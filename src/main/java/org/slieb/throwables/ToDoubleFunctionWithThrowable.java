package org.slieb.throwables;

/**
 * Generated from java.util.function.ToDoubleFunction
 * Extends java.util.function.ToDoubleFunction to allow for a checked exception.
 *
 * @param <T> some generic flag
 * @param <E> The extension
 */
@FunctionalInterface
public interface ToDoubleFunctionWithThrowable<T, E extends Throwable> extends java.util.function.ToDoubleFunction<T> {
    /**
     * Utility method to mark lambdas of type ToDoubleFunctionWithThrowable
     * @param todoublefunctionwiththrowable The interface instance
     * @param <T> Generic that corresponds to the same generic on ToDoubleFunction  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, E extends Throwable> ToDoubleFunctionWithThrowable<T, E> castToDoubleFunctionWithThrowable(ToDoubleFunctionWithThrowable<T, E> todoublefunctionwiththrowable) {
        return todoublefunctionwiththrowable;
    }
    /**
     * Utility method to convert ToDoubleFunctionWithThrowable
     * @param todoublefunction The interface instance
     * @param <T> Generic that corresponds to the same generic on ToDoubleFunction  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, E extends Throwable> ToDoubleFunctionWithThrowable<T, E> asToDoubleFunctionWithThrowable(java.util.function.ToDoubleFunction<T> todoublefunction) {
        return todoublefunction::applyAsDouble;
    }

    /** 
     * Overridden method of ToDoubleFunctionWithThrowable that will call applyAsDoubleWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @return the value
     */
    @Override
    default double applyAsDouble(T v1) {
        try {
            return applyAsDoubleWithThrowable(v1);
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
    double applyAsDoubleWithThrowable(T v1) throws E;


    /**
     * @param logger The logger to log exceptions on
     * @param level The log level to use when logging exceptions
     * @param message A message to use for logging exceptions
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default ToDoubleFunctionWithThrowable<T, E> withLogging(java.util.logging.Logger logger, java.util.logging.Level level, String message) {
        return (v1) -> {
            try {
                return applyAsDoubleWithThrowable(v1);
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
    default ToDoubleFunctionWithThrowable<T, E> withLogging(java.util.logging.Logger logger) {
        return withLogging(logger, java.util.logging.Level.WARNING, "Exception in ToDoubleFunctionWithThrowable");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default ToDoubleFunctionWithThrowable<T, E> withLogging() {
        return withLogging(java.util.logging.Logger.getGlobal());
    }

}
