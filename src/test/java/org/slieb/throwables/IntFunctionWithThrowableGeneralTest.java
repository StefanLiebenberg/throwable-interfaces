package org.slieb.throwables;

import org.junit.Test;
import java.util.concurrent.atomic.AtomicReference;
import static org.slieb.throwables.IntFunctionWithThrowable.castIntFunctionWithThrowable;
import static org.junit.Assert.assertEquals;
public class IntFunctionWithThrowableGeneralTest {
 @Test
 public void testReturnTypeException() {
        Object expected = new Object();
        Object result = castIntFunctionWithThrowable((v1) -> {
      throw new Exception("expect exception");
    }).thatReturnsOnCatch(expected).apply(0);
        assertEquals(expected, result);
 }

 @Test
 public void testNormalOperation() {
    Object expected = new Object();
    Object result = castIntFunctionWithThrowable((v1) -> expected).thatReturnsOnCatch(null).apply(0);
    assertEquals(expected, result);
 }

 @Test
 public void testOnException() {
        AtomicReference<java.lang.Throwable> reference = new AtomicReference<>();
        java.lang.Exception expected = new java.lang.Exception("expected");
        try {
        castIntFunctionWithThrowable((v1) -> {
      throw expected;
    }).onException(reference::set).apply(0);
        } catch (Throwable ignored) {}
        assertEquals(expected, reference.get());
 }

}
