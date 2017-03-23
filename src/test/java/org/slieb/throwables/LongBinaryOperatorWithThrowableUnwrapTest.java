package org.slieb.throwables;

import org.junit.Test;
import java.util.concurrent.atomic.AtomicReference;
import static org.slieb.throwables.LongBinaryOperatorWithThrowable.castLongBinaryOperatorWithThrowable;
import static org.junit.Assert.assertEquals;
public class LongBinaryOperatorWithThrowableUnwrapTest {

    private class CustomException extends Exception {}

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            return castLongBinaryOperatorWithThrowable((v1, v2) -> {
                throw expected;
            }).applyAsLong(0, 0);
        }, CustomException.class);
    }
}
