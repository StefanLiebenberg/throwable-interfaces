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
     *
     * @param doubleconsumerwiththrowable The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> DoubleConsumerWithThrowable<E> castDoubleConsumerWithThrowable(DoubleConsumerWithThrowable<E> doubleconsumerwiththrowable) {
        return doubleconsumerwiththrowable;
    }
    /**
     * Utility method to convert DoubleConsumerWithThrowable
     * @param doubleconsumer The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> DoubleConsumerWithThrowable<E> asDoubleConsumerWithThrowable(java.util.function.DoubleConsumer doubleconsumer) {
        return doubleconsumer::accept;
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


    /**
     * @param throwableClasses A varargs of throwable types to ignore.
     * @return An interface that ignores some exceptions.
     */
    @SuppressWarnings("Duplicates")
    default DoubleConsumerWithThrowable thatIgnores(Class<? extends Throwable> ... throwableClasses) {
        return (v1) -> {
            try {
                acceptWithThrowable(v1);
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
    default java.util.function.DoubleConsumer thatIgnoresThrowables() {
        return (v1) -> {
            try {
                acceptWithThrowable(v1);
            } catch(Throwable ignored) {}
        };
    }


    /**
     * @param logger The logger to log exceptions on
     * @param message A message to use for logging exceptions
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default DoubleConsumerWithThrowable<E> withLogging(org.slf4j.Logger logger, String message) {
        return (v1) -> {
            try {
                acceptWithThrowable(v1);
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
    default DoubleConsumerWithThrowable<E> withLogging(org.slf4j.Logger logger) {
        return withLogging(logger, "Exception in DoubleConsumerWithThrowable");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default DoubleConsumerWithThrowable<E> withLogging() {
        return withLogging(org.slf4j.LoggerFactory.getLogger(getClass()));
    }

}
