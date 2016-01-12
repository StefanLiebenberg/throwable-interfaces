package org.slieb.throwables;

/**
 * Generated from java.util.function.Consumer
 * Extends java.util.function.Consumer to allow for a checked exception.
 *
 * @param <T> some generic flag
 * @param <E> The extension
 */
@FunctionalInterface
public interface ConsumerWithThrowable<T, E extends Throwable> extends java.util.function.Consumer<T> {


    /**
     * Utility method to mark lambdas of type ConsumerWithThrowable
     *
     * @param consumerwiththrowable The interface instance
     * @param <T> Generic that corresponds to the same generic on Consumer  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, E extends Throwable> ConsumerWithThrowable<T, E> castConsumerWithThrowable(ConsumerWithThrowable<T, E> consumerwiththrowable) {
        return consumerwiththrowable;
    }
    /**
     * Utility method to convert ConsumerWithThrowable
     * @param consumer The interface instance
     * @param <T> Generic that corresponds to the same generic on Consumer  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, E extends Throwable> ConsumerWithThrowable<T, E> asConsumerWithThrowable(java.util.function.Consumer<T> consumer) {
        return consumer::accept;
    }

    /** 
     * Overridden method of ConsumerWithThrowable that will call acceptWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     */
    @Override
    default void accept(T v1) {
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
    void acceptWithThrowable(T v1) throws E;


    /**
     * @param throwableClasses A varargs of throwable types to ignore.
     * @return An interface that ignores some exceptions.
     */
    @SuppressWarnings("Duplicates")
    default ConsumerWithThrowable<T, E> thatIgnores(Class<? extends Throwable> ... throwableClasses) {
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
    default java.util.function.Consumer<T> thatIgnoresThrowables() {
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
    default ConsumerWithThrowable<T, E> withLogging(org.slf4j.Logger logger, String message) {
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
    default ConsumerWithThrowable<T, E> withLogging(org.slf4j.Logger logger) {
        return withLogging(logger, "Exception in ConsumerWithThrowable");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default ConsumerWithThrowable<T, E> withLogging() {
        return withLogging(org.slf4j.LoggerFactory.getLogger(getClass()));
    }

}
