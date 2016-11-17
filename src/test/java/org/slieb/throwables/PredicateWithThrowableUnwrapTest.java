package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.PredicateWithThrowable.castPredicateWithThrowable;

public class PredicateWithThrowableUnwrapTest {

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            return castPredicateWithThrowable((v1) -> {
                throw expected;
            }).test(null);
        }, CustomException.class);
    }

    private class CustomException extends Exception {}
}
