package org.slieb.throwables;

import org.junit.Test;
import java.util.concurrent.atomic.AtomicReference;
import static org.slieb.throwables.DoubleConsumerWithThrowable.castDoubleConsumerWithThrowable;
import static org.junit.Assert.assertEquals;
public class DoubleConsumerWithThrowableUnwrapTest {

    private class CustomException extends Exception {}

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            castDoubleConsumerWithThrowable((v1) -> {
                throw expected;
            }).accept(0);
        }, CustomException.class);
    }
}
