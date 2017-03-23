package org.slieb.throwables;

import org.junit.Test;
import java.util.concurrent.atomic.AtomicReference;
import static org.slieb.throwables.BiFunctionWithThrowable.castBiFunctionWithThrowable;
import static org.junit.Assert.assertEquals;
public class BiFunctionWithThrowableUnwrapTest {

    private class CustomException extends Exception {}

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            return castBiFunctionWithThrowable((v1, v2) -> {
                throw expected;
            }).apply(null, null);
        }, CustomException.class);
    }
}
