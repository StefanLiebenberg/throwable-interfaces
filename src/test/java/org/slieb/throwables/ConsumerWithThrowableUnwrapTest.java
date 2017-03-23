package org.slieb.throwables;

import org.junit.Test;
import java.util.concurrent.atomic.AtomicReference;
import static org.slieb.throwables.ConsumerWithThrowable.castConsumerWithThrowable;
import static org.junit.Assert.assertEquals;
public class ConsumerWithThrowableUnwrapTest {

    private class CustomException extends Exception {}

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            castConsumerWithThrowable((v1) -> {
                throw expected;
            }).accept(null);
        }, CustomException.class);
    }
}
