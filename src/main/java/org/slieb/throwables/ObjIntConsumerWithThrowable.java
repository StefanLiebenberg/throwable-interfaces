package org.slieb.throwables;

/**
 * Generated from java.util.function.ObjIntConsumer
 * Extends java.util.function.ObjIntConsumer to allow for a checked exception.
 *
 * @param <T> some generic flag
 * @param <E> The extension
 */
@FunctionalInterface
public interface ObjIntConsumerWithThrowable<T, E extends Throwable> extends java.util.function.ObjIntConsumer<T> {


    /**
     * Utility method to mark lambdas of type ObjIntConsumerWithThrowable
     *
     * @param objintconsumerwiththrowable The interface instance
     * @param <T> Generic that corresponds to the same generic on ObjIntConsumer  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, E extends Throwable> ObjIntConsumerWithThrowable<T, E> castObjIntConsumerWithThrowable(ObjIntConsumerWithThrowable<T, E> objintconsumerwiththrowable) {
        return objintconsumerwiththrowable;
    }
    /**
     * Utility method to convert ObjIntConsumerWithThrowable
     * @param objintconsumer The interface instance
     * @param <T> Generic that corresponds to the same generic on ObjIntConsumer  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, E extends Throwable> ObjIntConsumerWithThrowable<T, E> asObjIntConsumerWithThrowable(java.util.function.ObjIntConsumer<T> objintconsumer) {
        return objintconsumer::accept;
    }

    /** 
     * Overridden method of ObjIntConsumerWithThrowable that will call acceptWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @param v2 parameter to overridden method
     */
    @Override
    default void accept(T v1, int v2) {
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
    void acceptWithThrowable(T v1, int v2) throws E;


    /**
     * @return A interface that ignores some exceptions.
     */
    @SuppressWarnings("Duplicates")
    default ObjIntConsumerWithThrowable<T, E> thatIgnores(Class<? extends Throwable> ... throwableClasses) {
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
     * @return A interface that completely ignores exceptions. Consider using this method withLogging() as well.
     */
    default java.util.function.ObjIntConsumer<T> thatIgnoresThrowables() {
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
    default ObjIntConsumerWithThrowable<T, E> withLogging(org.slf4j.Logger logger, String message) {
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
    default ObjIntConsumerWithThrowable<T, E> withLogging(org.slf4j.Logger logger) {
        return withLogging(logger, "Exception in ObjIntConsumerWithThrowable");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default ObjIntConsumerWithThrowable<T, E> withLogging() {
        return withLogging(org.slf4j.LoggerFactory.getLogger(getClass()));
    }

}
