package org.slieb.throwables;

import org.junit.Test;
import java.util.concurrent.atomic.AtomicReference;
import static org.slieb.throwables.IntSupplierWithThrowable.castIntSupplierWithThrowable;
import static org.junit.Assert.assertEquals;
public class IntSupplierWithThrowableUnwrapTest {

    private class CustomException extends Exception {}

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            return castIntSupplierWithThrowable(() -> {
                throw expected;
            }).getAsInt();
        }, CustomException.class);
    }
}
