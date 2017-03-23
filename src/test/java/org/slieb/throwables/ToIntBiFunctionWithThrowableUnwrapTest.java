package org.slieb.throwables;

import org.junit.Test;
import java.util.concurrent.atomic.AtomicReference;
import static org.slieb.throwables.ToIntBiFunctionWithThrowable.castToIntBiFunctionWithThrowable;
import static org.junit.Assert.assertEquals;
public class ToIntBiFunctionWithThrowableUnwrapTest {

    private class CustomException extends Exception {}

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            return castToIntBiFunctionWithThrowable((v1, v2) -> {
                throw expected;
            }).applyAsInt(null, null);
        }, CustomException.class);
    }
}
