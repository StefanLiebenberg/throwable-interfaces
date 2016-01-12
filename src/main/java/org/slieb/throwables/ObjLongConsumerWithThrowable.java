package org.slieb.throwables;

/**
 * Generated from java.util.function.ObjLongConsumer
 * Extends java.util.function.ObjLongConsumer to allow for a checked exception.
 *
 * @param <T> some generic flag
 * @param <E> The extension
 */
@FunctionalInterface
public interface ObjLongConsumerWithThrowable<T, E extends Throwable> extends java.util.function.ObjLongConsumer<T> {
    /**
     * Utility method to mark lambdas of type ObjLongConsumerWithThrowable
     * @param objlongconsumerwiththrowable The interface instance
     * @param <T> Generic that corresponds to the same generic on ObjLongConsumer  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, E extends Throwable> ObjLongConsumerWithThrowable<T, E> castObjLongConsumerWithThrowable(ObjLongConsumerWithThrowable<T, E> objlongconsumerwiththrowable) {
        return objlongconsumerwiththrowable;
    }
    /**
     * Utility method to convert ObjLongConsumerWithThrowable
     * @param objlongconsumer The interface instance
     * @param <T> Generic that corresponds to the same generic on ObjLongConsumer  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, E extends Throwable> ObjLongConsumerWithThrowable<T, E> asObjLongConsumerWithThrowable(java.util.function.ObjLongConsumer<T> objlongconsumer) {
        return objlongconsumer::accept;
    }

    /** 
     * Overridden method of ObjLongConsumerWithThrowable that will call acceptWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @param v2 parameter to overridden method
     */
    @Override
    default void accept(T v1, long v2) {
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
    void acceptWithThrowable(T v1, long v2) throws E;


    /**
     * @return A interface that completely ignores exceptions. Consider using this method withLogging() as well.
     */
    default java.util.function.ObjLongConsumer<T> thatDoesNothing() {
        return (v1, v2) -> {
            try {
                acceptWithThrowable(v1, v2);
            } catch(Throwable ignored) {}
        };
    }


    /**
     * @param logger The logger to log exceptions on
     * @param level The log level to use when logging exceptions
     * @param message A message to use for logging exceptions
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default ObjLongConsumerWithThrowable<T, E> withLogging(java.util.logging.Logger logger, java.util.logging.Level level, String message) {
        return (v1, v2) -> {
            try {
                acceptWithThrowable(v1, v2);
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
    default ObjLongConsumerWithThrowable<T, E> withLogging(java.util.logging.Logger logger) {
        return withLogging(logger, java.util.logging.Level.WARNING, "Exception in ObjLongConsumerWithThrowable");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default ObjLongConsumerWithThrowable<T, E> withLogging() {
        return withLogging(java.util.logging.Logger.getGlobal());
    }

}
