package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.ObjLongConsumerWithThrowable.castObjLongConsumerWithThrowable;

public class ObjLongConsumerWithThrowableIgnoresTest {

    @Test
    public void testThrowExceptionWithNoIgnores() {
        castObjLongConsumerWithThrowable((v1, v2) -> {
            throw new Exception("expected error");
        }).thatThrowsNothing().accept(null, 0);
    }
}
