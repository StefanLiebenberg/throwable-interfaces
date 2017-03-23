package org.slieb.throwables;

import org.junit.Test;
import java.util.concurrent.atomic.AtomicReference;
import static org.slieb.throwables.LongBinaryOperatorWithThrowable.castLongBinaryOperatorWithThrowable;
import static org.junit.Assert.assertEquals;
public class LongBinaryOperatorWithThrowableGeneralTest {
 @Test
 public void testOnException() {
        AtomicReference<java.lang.Throwable> reference = new AtomicReference<>();
        java.lang.Exception expected = new java.lang.Exception("expected");
        try {
        castLongBinaryOperatorWithThrowable((v1, v2) -> {
      throw expected;
    }).onException(reference::set).applyAsLong(0, 0);
        } catch (Throwable ignored) {}
        assertEquals(expected, reference.get());
 }

}
