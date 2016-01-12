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
     * 
     */
    default ToDoubleFunctionWithThrowable<T, E> withLogging(java.util.logging.Logger logger, java.util.logging.Level level) {
        return (v1) -> {
            try {
                return applyAsDoubleWithThrowable(v1);
            } catch (final Throwable throwable) {
                logger.log(level, "exception in ToDoubleFunctionWithThrowable", throwable);
                throw throwable;
            }
        };
    }


    /**
     * 
     */
    default ToDoubleFunctionWithThrowable<T, E> withLogging(java.util.logging.Logger logger) {
  return withLogging(logger, java.util.logging.Level.WARNING);
}

    default ToDoubleFunctionWithThrowable<T, E> withLogging() {
  return withLogging(java.util.logging.Logger.getGlobal());
}

}
