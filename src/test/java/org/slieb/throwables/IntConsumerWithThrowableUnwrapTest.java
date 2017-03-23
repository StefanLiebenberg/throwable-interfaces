package org.slieb.throwables;

import org.junit.Test;
import java.util.concurrent.atomic.AtomicReference;
import static org.slieb.throwables.IntConsumerWithThrowable.castIntConsumerWithThrowable;
import static org.junit.Assert.assertEquals;
public class IntConsumerWithThrowableUnwrapTest {

    private class CustomException extends Exception {}

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            castIntConsumerWithThrowable((v1) -> {
                throw expected;
            }).accept(0);
        }, CustomException.class);
    }
}
