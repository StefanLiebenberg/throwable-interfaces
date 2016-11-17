package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.ObjDoubleConsumerWithThrowable.castObjDoubleConsumerWithThrowable;

public class ObjDoubleConsumerWithThrowableUnwrapTest {

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            castObjDoubleConsumerWithThrowable((v1, v2) -> {
                throw expected;
            }).accept(null, 0);
        }, CustomException.class);
    }

    private class CustomException extends Exception {}
}
