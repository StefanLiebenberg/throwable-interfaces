package org.slieb.throwables;

/**
 * Generated from java.util.function.Predicate
 *
 * @param <T> some generic flag
 * @param <E> The extension
 */
@FunctionalInterface
public interface PredicateWithThrowable<T, E extends Throwable> extends java.util.function.Predicate<T> {
    /**
     * @param predicatewiththrowable object
     * @param <T> some generic flag
     * @param <E> The extension
     * @return the cast interface
     */
    static <T, E extends Throwable> PredicateWithThrowable<T, E> castPredicateWithThrowable(PredicateWithThrowable<T, E> predicatewiththrowable) {
        return predicatewiththrowable;
    }

    /** 
     * Overridden method of PredicateWithThrowable that will call testWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @return the value
     */
    @Override
    default boolean test(T v1) {
        try {
            return testWithThrowable(v1);
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
     * @return the value
     * @throws E some exception
     */
    boolean testWithThrowable(T v1) throws E;
}
