package org.slieb.throwables;

import org.junit.Test;
import java.util.concurrent.atomic.AtomicReference;
import static org.slieb.throwables.ToLongFunctionWithThrowable.castToLongFunctionWithThrowable;
import static org.junit.Assert.assertEquals;
public class ToLongFunctionWithThrowableUnwrapTest {

    private class CustomException extends Exception {}

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            return castToLongFunctionWithThrowable((v1) -> {
                throw expected;
            }).applyAsLong(null);
        }, CustomException.class);
    }
}
