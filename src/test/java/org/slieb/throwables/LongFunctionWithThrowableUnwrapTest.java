package org.slieb.throwables;

import org.junit.Test;
import java.util.concurrent.atomic.AtomicReference;
import static org.slieb.throwables.LongFunctionWithThrowable.castLongFunctionWithThrowable;
import static org.junit.Assert.assertEquals;
public class LongFunctionWithThrowableUnwrapTest {

    private class CustomException extends Exception {}

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            return castLongFunctionWithThrowable((v1) -> {
                throw expected;
            }).apply(0);
        }, CustomException.class);
    }
}
