package org.slieb.throwables;

import org.junit.Test;
import java.util.concurrent.atomic.AtomicReference;
import static org.slieb.throwables.UnaryOperatorWithThrowable.castUnaryOperatorWithThrowable;
import static org.junit.Assert.assertEquals;
public class UnaryOperatorWithThrowableUnwrapTest {

    private class CustomException extends Exception {}

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            return castUnaryOperatorWithThrowable((v1) -> {
                throw expected;
            }).apply(null);
        }, CustomException.class);
    }
}
