package org.slieb.throwables;

import org.junit.Test;
import java.util.concurrent.atomic.AtomicReference;
import static org.slieb.throwables.FunctionWithThrowable.castFunctionWithThrowable;
import static org.junit.Assert.assertEquals;
public class FunctionWithThrowableUnwrapTest {

    private class CustomException extends Exception {}

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            return castFunctionWithThrowable((v1) -> {
                throw expected;
            }).apply(null);
        }, CustomException.class);
    }
}
