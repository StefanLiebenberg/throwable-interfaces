package org.slieb.throwables;

import org.junit.Test;
import java.util.concurrent.atomic.AtomicReference;
import static org.slieb.throwables.IntBinaryOperatorWithThrowable.castIntBinaryOperatorWithThrowable;
import static org.junit.Assert.assertEquals;
public class IntBinaryOperatorWithThrowableUnwrapTest {

    private class CustomException extends Exception {}

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            return castIntBinaryOperatorWithThrowable((v1, v2) -> {
                throw expected;
            }).applyAsInt(0, 0);
        }, CustomException.class);
    }
}
