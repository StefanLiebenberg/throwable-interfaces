package org.slieb.throwables;

import org.junit.Test;
import java.util.concurrent.atomic.AtomicReference;
import static org.slieb.throwables.ToLongBiFunctionWithThrowable.castToLongBiFunctionWithThrowable;
import static org.junit.Assert.assertEquals;
public class ToLongBiFunctionWithThrowableUnwrapTest {

    private class CustomException extends Exception {}

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            return castToLongBiFunctionWithThrowable((v1, v2) -> {
                throw expected;
            }).applyAsLong(null, null);
        }, CustomException.class);
    }
}
