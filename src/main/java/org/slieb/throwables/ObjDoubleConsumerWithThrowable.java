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
     *
     * @param objdoubleconsumerwiththrowable The interface instance
     * @param <T> Generic that corresponds to the same generic on ObjDoubleConsumer  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, E extends Throwable> ObjDoubleConsumerWithThrowable<T, E> castObjDoubleConsumerWithThrowable(ObjDoubleConsumerWithThrowable<T, E> objdoubleconsumerwiththrowable) {
        return objdoubleconsumerwiththrowable;
    }
    /**
     * Utility method to convert ObjDoubleConsumerWithThrowable
     * @param objdoubleconsumer The interface instance
     * @param <T> Generic that corresponds to the same generic on ObjDoubleConsumer  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, E extends Throwable> ObjDoubleConsumerWithThrowable<T, E> asObjDoubleConsumerWithThrowable(java.util.function.ObjDoubleConsumer<T> objdoubleconsumer) {
        return objdoubleconsumer::accept;
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


    /**
     * @param throwableClasses A varargs of throwable types to ignore.
     * @return An interface that ignores some exceptions.
     */
    @SuppressWarnings("Duplicates")
    default ObjDoubleConsumerWithThrowable<T, E> thatIgnores(Class<? extends Throwable> ... throwableClasses) {
        return (v1, v2) -> {
            try {
                acceptWithThrowable(v1, v2);
            } catch(Throwable throwable) {
                if(java.util.Arrays.stream(throwableClasses).noneMatch((Class<? extends Throwable> klass) -> klass.isInstance(throwable))) {
                    throw throwable;
                }
            }
        };
    }


    /**
     * @return An interface that completely ignores exceptions. Consider using this method withLogging() as well.
     */
    default java.util.function.ObjDoubleConsumer<T> thatIgnoresThrowables() {
        return (v1, v2) -> {
            try {
                acceptWithThrowable(v1, v2);
            } catch(Throwable ignored) {}
        };
    }


    /**
     * @param logger The logger to log exceptions on
     * @param message A message to use for logging exceptions
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default ObjDoubleConsumerWithThrowable<T, E> withLogging(org.slf4j.Logger logger, String message) {
        return (v1, v2) -> {
            try {
                acceptWithThrowable(v1, v2);
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
    default ObjDoubleConsumerWithThrowable<T, E> withLogging(org.slf4j.Logger logger) {
        return withLogging(logger, "Exception in ObjDoubleConsumerWithThrowable");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default ObjDoubleConsumerWithThrowable<T, E> withLogging() {
        return withLogging(org.slf4j.LoggerFactory.getLogger(getClass()));
    }

}
