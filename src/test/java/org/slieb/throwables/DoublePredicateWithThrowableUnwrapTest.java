package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.DoublePredicateWithThrowable.castDoublePredicateWithThrowable;

public class DoublePredicateWithThrowableUnwrapTest {

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            return castDoublePredicateWithThrowable((v1) -> {
                throw expected;
            }).test(0);
        }, CustomException.class);
    }

    private class CustomException extends Exception {}
}
