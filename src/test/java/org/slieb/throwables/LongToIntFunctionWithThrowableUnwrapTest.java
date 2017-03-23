package org.slieb.throwables;

import org.junit.Test;
import java.util.concurrent.atomic.AtomicReference;
import static org.slieb.throwables.LongToIntFunctionWithThrowable.castLongToIntFunctionWithThrowable;
import static org.junit.Assert.assertEquals;
public class LongToIntFunctionWithThrowableUnwrapTest {

    private class CustomException extends Exception {}

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            return castLongToIntFunctionWithThrowable((v1) -> {
                throw expected;
            }).applyAsInt(0);
        }, CustomException.class);
    }
}
