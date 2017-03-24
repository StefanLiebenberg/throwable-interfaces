package org.slieb.throwables;

import org.junit.Test;
import java.util.concurrent.atomic.AtomicReference;
import static org.slieb.throwables.ToDoubleFunctionWithThrowable.castToDoubleFunctionWithThrowable;
import static org.junit.Assert.assertEquals;
public class ToDoubleFunctionWithThrowableGeneralTest {
 @Test
 public void testOnException() {
        AtomicReference<java.lang.Throwable> reference = new AtomicReference<>();
        java.lang.Exception expected = new java.lang.Exception("expected");
        try {
        castToDoubleFunctionWithThrowable((v1) -> {
      throw expected;
    }).onException(reference::set).applyAsDouble(null);
        } catch (Throwable ignored) {}
        assertEquals(expected, reference.get());
 }

}
