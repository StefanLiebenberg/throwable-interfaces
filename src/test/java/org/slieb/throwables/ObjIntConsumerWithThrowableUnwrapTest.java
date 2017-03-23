package org.slieb.throwables;

import org.junit.Test;
import java.util.concurrent.atomic.AtomicReference;
import static org.slieb.throwables.ObjIntConsumerWithThrowable.castObjIntConsumerWithThrowable;
import static org.junit.Assert.assertEquals;
public class ObjIntConsumerWithThrowableUnwrapTest {

    private class CustomException extends Exception {}

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            castObjIntConsumerWithThrowable((v1, v2) -> {
                throw expected;
            }).accept(null, 0);
        }, CustomException.class);
    }
}
