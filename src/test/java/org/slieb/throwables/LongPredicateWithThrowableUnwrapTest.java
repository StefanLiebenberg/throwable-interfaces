package org.slieb.throwables;

import org.junit.Test;
import java.util.concurrent.atomic.AtomicReference;
import static org.slieb.throwables.LongPredicateWithThrowable.castLongPredicateWithThrowable;
import static org.junit.Assert.assertEquals;
public class LongPredicateWithThrowableUnwrapTest {

    private class CustomException extends Exception {}

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            return castLongPredicateWithThrowable((v1) -> {
                throw expected;
            }).test(0);
        }, CustomException.class);
    }
}
