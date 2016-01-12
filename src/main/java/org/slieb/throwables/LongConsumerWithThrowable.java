package org.slieb.throwables;

/**
 * Generated from java.util.function.LongConsumer
 * Extends java.util.function.LongConsumer to allow for a checked exception.
 *
 * @param <E> The extension
 */
@FunctionalInterface
public interface LongConsumerWithThrowable<E extends Throwable> extends java.util.function.LongConsumer {
    /**
     * Utility method to mark lambdas of type LongConsumerWithThrowable
     * @param longconsumerwiththrowable The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> LongConsumerWithThrowable<E> castLongConsumerWithThrowable(LongConsumerWithThrowable<E> longconsumerwiththrowable) {
        return longconsumerwiththrowable;
    }

    /** 
     * Overridden method of LongConsumerWithThrowable that will call acceptWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     */
    @Override
    default void accept(long v1) {
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
    void acceptWithThrowable(long v1) throws E;
default java.util.function.LongConsumer thatDoesNothing() {
   return (v1) -> {
    try {
      acceptWithThrowable(v1);
    } catch(Throwable ignored) {}
  };
}


    /**
     * 
     */
    default LongConsumerWithThrowable<E> withLogging(java.util.logging.Logger logger, java.util.logging.Level level) {
        return (v1) -> {
            try {
                acceptWithThrowable(v1);
            } catch (final Throwable throwable) {
                logger.log(level, "exception in LongConsumerWithThrowable", throwable);
                throw throwable;
            }
        };
    }


    /**
     * 
     */
    default LongConsumerWithThrowable<E> withLogging(java.util.logging.Logger logger) {
  return withLogging(logger, java.util.logging.Level.WARNING);
}

    default LongConsumerWithThrowable<E> withLogging() {
  return withLogging(java.util.logging.Logger.getGlobal());
}

}
