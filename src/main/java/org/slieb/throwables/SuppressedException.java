package org.slieb.throwables;

import java.util.Optional;
import java.util.function.Supplier;

import static org.slieb.throwables.FunctionWithThrowable.castFunctionWithThrowable;

/**
 * This exception class wraps checked exceptions as a runtime exceptions, so we can throw them in Functional interfaces
 * such as Function and Supplier in a consistent manner.
 */
@SuppressWarnings({
        "WeakerAccess",
        "unused"
})
public class SuppressedException extends RuntimeException {

    protected SuppressedException(Throwable cause) {
        super(cause);
    }

    /**
     * @param supplier A supplier object which may throw some exception
     * @param <E>      The exception type.
     */
    public static <T, E extends Throwable> T suppress(SupplierWithThrowable<T, E> supplier) {
        return supplier.get();
    }

    /**
     * @param runnable A supplier object which may throw some exception
     * @param <E>      The exception type.
     */
    public static <E extends Throwable> void suppress(RunnableWithThrowable<E> runnable) {
        runnable.run();
    }

    /**
     * @param cause The original cause.
     * @return A SuppressedException
     */
    public static SuppressedException wrapException(Throwable cause) {
        return new SuppressedException(cause);
    }

    /**
     * @param supplier       A supplier that will throw SuppressedException
     * @param exceptionClass The class type to intercept and rethrow.
     * @param <T>            The generic return type.
     * @param <E>            The exception type.
     * @return The result of the supplier if no exception occurred.
     * @throws E The original exception, that was wrapped into a SuppressedException.
     */
    public static <T, E extends Throwable> T unwrapSuppressedException(final Supplier<T> supplier, final Class<E> exceptionClass) throws E {
        try {
            return supplier.get();
        } catch (SuppressedException e) {
            throw unwrapExceptionCause(e, exceptionClass).orElseThrow(() -> e);
        }
    }

    /**
     * @param supplier A supplier that throws SuppressedExceptions.
     * @param <T>      The return type generic.
     * @return The supplier's return value if not exception is thrown.
     * @throws Throwable any throwable cause attached to the SuppressedException
     */
    public static <T> T unwrapSuppressedException(final Supplier<T> supplier) throws Throwable {
        try {
            return supplier.get();
        } catch (SuppressedException e) {
            throw e.getCause();
        }
    }

    /**
     * @param runnable  A runnable that throws suppressed exceptions
     * @param exception The exception class to intercept.
     * @param <E>       The exception class generic type.
     * @throws E The intercepted exception.
     */
    public static <E extends Throwable> void unwrapSuppressedException(final Runnable runnable, final Class<E> exception) throws E {
        try {
            runnable.run();
        } catch (SuppressedException e) {
            throw unwrapExceptionCause(e, exception).orElseThrow(() -> e);
        }
    }

    /**
     * @param runnable A runnable that throws suppressed exceptions
     * @throws Throwable Any throwable caused that is intercepted from caught Suppressed Exceptions.
     */
    public static void unwrapSuppressedException(final Runnable runnable) throws Throwable {
        try {
            runnable.run();
        } catch (SuppressedException e) {
            throw e.getCause();
        }
    }

    /**
     * @param suppressed     The caught suppressed exception
     * @param exceptionClass The exception class type to try extract from the caught exception
     * @param <E>            The exception class type generic
     * @return An optional of the exception, if it is a instance of the exceptionClass.
     */
    public static <E extends Throwable> Optional<E> unwrapExceptionCause(final SuppressedException suppressed, final Class<E> exceptionClass) {
        return Optional.of(suppressed).map(Throwable::getCause).flatMap(castFunctionWithThrowable(exceptionClass::cast).thatReturnsOptional());
    }

    @SuppressWarnings("unchecked")
    public static <E extends Throwable> void throwAsUnchecked(Throwable exception) throws E {
        assert exception != null;
        throw (E) exception;
    }
}

