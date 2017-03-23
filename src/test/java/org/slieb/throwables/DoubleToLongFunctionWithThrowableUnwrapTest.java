package org.slieb.throwables;

import org.junit.Test;
import java.util.concurrent.atomic.AtomicReference;
import static org.slieb.throwables.DoubleToLongFunctionWithThrowable.castDoubleToLongFunctionWithThrowable;
import static org.junit.Assert.assertEquals;
public class DoubleToLongFunctionWithThrowableUnwrapTest {

    private class CustomException extends Exception {}

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            return castDoubleToLongFunctionWithThrowable((v1) -> {
                throw expected;
            }).applyAsLong(0);
        }, CustomException.class);
    }
}
