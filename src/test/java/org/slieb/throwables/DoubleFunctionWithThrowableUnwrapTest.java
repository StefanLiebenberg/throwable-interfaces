package org.slieb.throwables;

import org.junit.Test;
import java.util.concurrent.atomic.AtomicReference;
import static org.slieb.throwables.DoubleFunctionWithThrowable.castDoubleFunctionWithThrowable;
import static org.junit.Assert.assertEquals;
public class DoubleFunctionWithThrowableUnwrapTest {

    private class CustomException extends Exception {}

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            return castDoubleFunctionWithThrowable((v1) -> {
                throw expected;
            }).apply(0);
        }, CustomException.class);
    }
}
