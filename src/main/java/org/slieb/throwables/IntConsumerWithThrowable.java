package org.slieb.throwables;

/**
 * Generated from java.util.function.IntConsumer
 * Extends java.util.function.IntConsumer to allow for a checked exception.
 *
 * @param <E> The extension
 */
@FunctionalInterface
public interface IntConsumerWithThrowable<E extends Throwable> extends java.util.function.IntConsumer {


    /**
     * Utility method to mark lambdas of type IntConsumerWithThrowable
     *
     * @param intconsumerwiththrowable The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> IntConsumerWithThrowable<E> castIntConsumerWithThrowable(IntConsumerWithThrowable<E> intconsumerwiththrowable) {
        return intconsumerwiththrowable;
    }
    /**
     * Utility method to convert IntConsumerWithThrowable
     * @param intconsumer The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> IntConsumerWithThrowable<E> asIntConsumerWithThrowable(java.util.function.IntConsumer intconsumer) {
        return intconsumer::accept;
    }

    /** 
     * Overridden method of IntConsumerWithThrowable that will call acceptWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     */
    @Override
    default void accept(int v1) {
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
    void acceptWithThrowable(int v1) throws E;


    /**
     * @return A interface that ignores some exceptions.
     */
    @SuppressWarnings("Duplicates")
    default IntConsumerWithThrowable thatIgnores(Class<? extends Throwable> ... throwableClasses) {
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
     * @return A interface that completely ignores exceptions. Consider using this method withLogging() as well.
     */
    default java.util.function.IntConsumer thatIgnoresThrowables() {
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
    default IntConsumerWithThrowable<E> withLogging(org.slf4j.Logger logger, String message) {
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
    default IntConsumerWithThrowable<E> withLogging(org.slf4j.Logger logger) {
        return withLogging(logger, "Exception in IntConsumerWithThrowable");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default IntConsumerWithThrowable<E> withLogging() {
        return withLogging(org.slf4j.LoggerFactory.getLogger(getClass()));
    }

}
