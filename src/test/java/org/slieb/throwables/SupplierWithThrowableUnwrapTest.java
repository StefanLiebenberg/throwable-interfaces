package org.slieb.throwables;

import org.junit.Test;
import java.util.concurrent.atomic.AtomicReference;
import static org.slieb.throwables.SupplierWithThrowable.castSupplierWithThrowable;
import static org.junit.Assert.assertEquals;
public class SupplierWithThrowableUnwrapTest {

    private class CustomException extends Exception {}

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            return castSupplierWithThrowable(() -> {
                throw expected;
            }).get();
        }, CustomException.class);
    }
}
