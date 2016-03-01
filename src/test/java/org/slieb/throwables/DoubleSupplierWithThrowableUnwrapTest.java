package org.slieb.throwables;

import org.junit.Test;
import java.util.concurrent.atomic.AtomicReference;
import static org.slieb.throwables.DoubleSupplierWithThrowable.castDoubleSupplierWithThrowable;
import static org.junit.Assert.assertEquals;
public class DoubleSupplierWithThrowableUnwrapTest {

    private class CustomException extends Exception {}

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            return castDoubleSupplierWithThrowable(() -> {
                throw expected;
            }).getAsDouble();
        }, CustomException.class);
    }
}
