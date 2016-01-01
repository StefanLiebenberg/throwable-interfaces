package org.slieb.throwables;


import java.io.Serializable;
import java.util.function.Predicate;

@FunctionalInterface
public interface PredicateWithException<T, E extends Exception> extends Predicate<T>, Serializable {

    @Override
    default boolean test(final T elem) {
        try {
            return testWithException(elem);
        } catch (final RuntimeException e) {
            throw e;
        } catch (final Throwable e) {
            throw new RuntimeException(e);
        }
    }

    boolean testWithException(T elem) throws E;

    static <A, E extends Exception> PredicateWithException<A, E> castPredicateWithException(final PredicateWithException<A, E> predicate) {
        return predicate;
    }

}
