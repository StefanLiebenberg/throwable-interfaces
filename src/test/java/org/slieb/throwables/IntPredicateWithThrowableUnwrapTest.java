package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.IntPredicateWithThrowable.castIntPredicateWithThrowable;

public class IntPredicateWithThrowableUnwrapTest {

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            return castIntPredicateWithThrowable((v1) -> {
                throw expected;
            }).test(0);
        }, CustomException.class);
    }

    private class CustomException extends Exception {}
}
