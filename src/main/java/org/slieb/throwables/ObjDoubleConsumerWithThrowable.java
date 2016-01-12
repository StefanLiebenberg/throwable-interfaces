package org.slieb.throwables;

/**
 * Generated from java.util.function.ObjDoubleConsumer
 * Extends java.util.function.ObjDoubleConsumer to allow for a checked exception.
 *
 * @param <T> some generic flag
 * @param <E> The extension
 */
@FunctionalInterface
public interface ObjDoubleConsumerWithThrowable<T, E extends Throwable> extends java.util.function.ObjDoubleConsumer<T> {
    /**
     * Utility method to mark lambdas of type ObjDoubleConsumerWithThrowable
     * @param objdoubleconsumerwiththrowable The interface instance
     * @param <T> Generic that corresponds to the same generic on ObjDoubleConsumer  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, E extends Throwable> ObjDoubleConsumerWithThrowable<T, E> castObjDoubleConsumerWithThrowable(ObjDoubleConsumerWithThrowable<T, E> objdoubleconsumerwiththrowable) {
        return objdoubleconsumerwiththrowable;
    }

    /** 
     * Overridden method of ObjDoubleConsumerWithThrowable that will call acceptWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @param v2 parameter to overridden method
     */
    @Override
    default void accept(T v1, double v2) {
        try {
            acceptWithThrowable(v1, v2);
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
     * @throws E some exception
     */
    void acceptWithThrowable(T v1, double v2) throws E;
default java.util.function.ObjDoubleConsumer<T> thatDoesNothing() {
   return (v1, v2) -> {
    try {
      acceptWithThrowable(v1, v2);
    } catch(Throwable ignored) {}
  };
}


    /**
     * 
     */
    default ObjDoubleConsumerWithThrowable<T, E> withLogging(java.util.logging.Logger logger, java.util.logging.Level level) {
        return (v1, v2) -> {
            try {
                acceptWithThrowable(v1, v2);
            } catch (final Throwable throwable) {
                logger.log(level, "exception in ObjDoubleConsumerWithThrowable", throwable);
                throw throwable;
            }
        };
    }


    /**
     * 
     */
    default ObjDoubleConsumerWithThrowable<T, E> withLogging(java.util.logging.Logger logger) {
  return withLogging(logger, java.util.logging.Level.WARNING);
}

    default ObjDoubleConsumerWithThrowable<T, E> withLogging() {
  return withLogging(java.util.logging.Logger.getGlobal());
}

}
