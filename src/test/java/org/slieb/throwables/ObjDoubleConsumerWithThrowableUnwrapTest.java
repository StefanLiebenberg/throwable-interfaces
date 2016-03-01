package org.slieb.throwables;

import org.junit.Test;
import java.util.concurrent.atomic.AtomicReference;
import static org.slieb.throwables.ObjDoubleConsumerWithThrowable.castObjDoubleConsumerWithThrowable;
import static org.junit.Assert.assertEquals;
public class ObjDoubleConsumerWithThrowableUnwrapTest {

    private class CustomException extends Exception {}

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            castObjDoubleConsumerWithThrowable((v1, v2) -> {
                throw expected;
            }).accept(null, 0);
        }, CustomException.class);
    }
}
