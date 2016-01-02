package org.slieb.throwables;


import java.io.Serializable;
import java.util.function.Predicate;

@FunctionalInterface
public interface PredicateWithThrowable<T, E extends Throwable> extends Predicate<T>, Serializable {

    @Override
    default boolean test(final T value) {
        try {
            return testWithThrowable(value);
        } catch (final RuntimeException | Error e) {
            throw e;
        } catch (final Throwable e) {
            throw new SuppressedException(e);
        }
    }

    boolean testWithThrowable(T value) throws E;

    static <A, E extends Throwable> PredicateWithThrowable<A, E> castPredicateWithThrowable(final PredicateWithThrowable<A, E> predicate) {
        return predicate;
    }

}
