package org.slieb.throwables;

import org.junit.Test;
import java.util.concurrent.atomic.AtomicReference;
import static org.slieb.throwables.ToIntFunctionWithThrowable.castToIntFunctionWithThrowable;
import static org.junit.Assert.assertEquals;
public class ToIntFunctionWithThrowableGeneralTest {
 @Test
 public void testOnException() {
        AtomicReference<java.lang.Throwable> reference = new AtomicReference<>();
        java.lang.Exception expected = new java.lang.Exception("expected");
        try {
        castToIntFunctionWithThrowable((v1) -> {
      throw expected;
    }).onException(reference::set).applyAsInt(null);
        } catch (Throwable ignored) {}
        assertEquals(expected, reference.get());
 }

}
