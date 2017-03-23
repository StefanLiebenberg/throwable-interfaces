package org.slieb.throwables;

import org.junit.Test;
import java.util.concurrent.atomic.AtomicReference;
import static org.slieb.throwables.ToIntFunctionWithThrowable.castToIntFunctionWithThrowable;
import static org.junit.Assert.assertEquals;
public class ToIntFunctionWithThrowableUnwrapTest {

    private class CustomException extends Exception {}

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            return castToIntFunctionWithThrowable((v1) -> {
                throw expected;
            }).applyAsInt(null);
        }, CustomException.class);
    }
}
