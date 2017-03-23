package org.slieb.throwables;

import org.junit.Test;
import java.util.concurrent.atomic.AtomicReference;
import static org.slieb.throwables.BiFunctionWithThrowable.castBiFunctionWithThrowable;
import static org.junit.Assert.assertEquals;
public class BiFunctionWithThrowableGeneralTest {
 @Test
 public void testReturnTypeException() {
        Object expected = new Object();
        Object result = castBiFunctionWithThrowable((v1, v2) -> {
      throw new Exception("expect exception");
    }).thatReturnsOnCatch(expected).apply(null, null);
        assertEquals(expected, result);
 }

 @Test
 public void testNormalOperation() {
    Object expected = new Object();
    Object result = castBiFunctionWithThrowable((v1, v2) -> expected).thatReturnsOnCatch(null).apply(null, null);
    assertEquals(expected, result);
 }

 @Test
 public void testOnException() {
        AtomicReference<java.lang.Throwable> reference = new AtomicReference<>();
        java.lang.Exception expected = new java.lang.Exception("expected");
        try {
        castBiFunctionWithThrowable((v1, v2) -> {
      throw expected;
    }).onException(reference::set).apply(null, null);
        } catch (Throwable ignored) {}
        assertEquals(expected, reference.get());
 }

}
