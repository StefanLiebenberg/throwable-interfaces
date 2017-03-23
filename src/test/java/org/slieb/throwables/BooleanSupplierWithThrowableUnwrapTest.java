package org.slieb.throwables;

import org.junit.Test;
import java.util.concurrent.atomic.AtomicReference;
import static org.slieb.throwables.BooleanSupplierWithThrowable.castBooleanSupplierWithThrowable;
import static org.junit.Assert.assertEquals;
public class BooleanSupplierWithThrowableUnwrapTest {

    private class CustomException extends Exception {}

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            return castBooleanSupplierWithThrowable(() -> {
                throw expected;
            }).getAsBoolean();
        }, CustomException.class);
    }
}
