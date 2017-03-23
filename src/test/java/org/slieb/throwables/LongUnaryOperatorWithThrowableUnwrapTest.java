package org.slieb.throwables;

import org.junit.Test;
import java.util.concurrent.atomic.AtomicReference;
import static org.slieb.throwables.LongUnaryOperatorWithThrowable.castLongUnaryOperatorWithThrowable;
import static org.junit.Assert.assertEquals;
public class LongUnaryOperatorWithThrowableUnwrapTest {

    private class CustomException extends Exception {}

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            return castLongUnaryOperatorWithThrowable((v1) -> {
                throw expected;
            }).applyAsLong(0);
        }, CustomException.class);
    }
}
