package org.slieb.throwables;

import org.junit.Test;
import java.util.concurrent.atomic.AtomicReference;
import static org.slieb.throwables.LongSupplierWithThrowable.castLongSupplierWithThrowable;
import static org.junit.Assert.assertEquals;
public class LongSupplierWithThrowableUnwrapTest {

    private class CustomException extends Exception {}

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            return castLongSupplierWithThrowable(() -> {
                throw expected;
            }).getAsLong();
        }, CustomException.class);
    }
}
