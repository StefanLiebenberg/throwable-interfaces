package org.slieb.throwables;

import org.junit.Test;
import java.util.concurrent.atomic.AtomicReference;
import static org.slieb.throwables.PredicateWithThrowable.castPredicateWithThrowable;
import static org.junit.Assert.assertEquals;
public class PredicateWithThrowableUnwrapTest {

    private class CustomException extends Exception {}

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            return castPredicateWithThrowable((v1) -> {
                throw expected;
            }).test(null);
        }, CustomException.class);
    }
}
