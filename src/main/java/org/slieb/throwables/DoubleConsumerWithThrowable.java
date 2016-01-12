package org.slieb.throwables;

/**
 * Generated from java.util.function.DoubleConsumer
 * Extends java.util.function.DoubleConsumer to allow for a checked exception.
 *
 * @param <E> The extension
 */
@FunctionalInterface
public interface DoubleConsumerWithThrowable<E extends Throwable> extends java.util.function.DoubleConsumer {
    /**
     * Utility method to mark lambdas of type DoubleConsumerWithThrowable
     * @param doubleconsumerwiththrowable The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> DoubleConsumerWithThrowable<E> castDoubleConsumerWithThrowable(DoubleConsumerWithThrowable<E> doubleconsumerwiththrowable) {
        return doubleconsumerwiththrowable;
    }

    /** 
     * Overridden method of DoubleConsumerWithThrowable that will call acceptWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     */
    @Override
    default void accept(double v1) {
        try {
            acceptWithThrowable(v1);
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
     * @throws E some exception
     */
    void acceptWithThrowable(double v1) throws E;
default java.util.function.DoubleConsumer thatDoesNothing() {
   return (v1) -> {
    try {
      acceptWithThrowable(v1);
    } catch(Throwable ignored) {}
  };
}


    /**
     * 
     */
    default DoubleConsumerWithThrowable<E> withLogging(java.util.logging.Logger logger, java.util.logging.Level level) {
        return (v1) -> {
            try {
                acceptWithThrowable(v1);
            } catch (final Throwable throwable) {
                logger.log(level, "exception in DoubleConsumerWithThrowable", throwable);
                throw throwable;
            }
        };
    }


    /**
     * 
     */
    default DoubleConsumerWithThrowable<E> withLogging(java.util.logging.Logger logger) {
  return withLogging(logger, java.util.logging.Level.WARNING);
}

    default DoubleConsumerWithThrowable<E> withLogging() {
  return withLogging(java.util.logging.Logger.getGlobal());
}

}
