package org.slieb.throwables;

import org.junit.Test;
import java.util.concurrent.atomic.AtomicReference;
import static org.slieb.throwables.DoubleUnaryOperatorWithThrowable.castDoubleUnaryOperatorWithThrowable;
import static org.junit.Assert.assertEquals;
public class DoubleUnaryOperatorWithThrowableUnwrapTest {

    private class CustomException extends Exception {}

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            return castDoubleUnaryOperatorWithThrowable((v1) -> {
                throw expected;
            }).applyAsDouble(0);
        }, CustomException.class);
    }
}
