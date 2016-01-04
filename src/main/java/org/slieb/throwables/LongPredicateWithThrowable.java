package org.slieb.throwables;

/**
 * Generated from java.util.function.LongPredicate
 *
 * @param <E> The extension
 */
@FunctionalInterface
public interface LongPredicateWithThrowable<E extends Throwable> extends java.util.function.LongPredicate {
    /**
     * @param longpredicatewiththrowable object
     * @param <E> The extension
     * @return the cast interface
     */
    static <E extends Throwable> LongPredicateWithThrowable<E> castLongPredicateWithThrowable(LongPredicateWithThrowable<E> longpredicatewiththrowable) {
        return longpredicatewiththrowable;
    }

    /** 
     * Overridden method of LongPredicateWithThrowable that will call testWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @return the value
     */
    @Override
    default boolean test(long v1) {
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
    boolean testWithThrowable(long v1) throws E;
}
