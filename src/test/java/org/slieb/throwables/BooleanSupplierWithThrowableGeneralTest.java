package org.slieb.throwables;

import org.junit.Test;
import java.util.concurrent.atomic.AtomicReference;
import static org.slieb.throwables.BooleanSupplierWithThrowable.castBooleanSupplierWithThrowable;
import static org.junit.Assert.assertEquals;
public class BooleanSupplierWithThrowableGeneralTest {
 @Test
 public void testOnException() {
        AtomicReference<java.lang.Throwable> reference = new AtomicReference<>();
        java.lang.Exception expected = new java.lang.Exception("expected");
        try {
        castBooleanSupplierWithThrowable(() -> {
      throw expected;
    }).onException(reference::set).getAsBoolean();
        } catch (Throwable ignored) {}
        assertEquals(expected, reference.get());
 }

}
