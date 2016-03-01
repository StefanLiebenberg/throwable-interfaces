package org.slieb.throwables;

import org.junit.Test;
import java.util.concurrent.atomic.AtomicReference;
import static org.slieb.throwables.ClosureWithThrowable.castClosureWithThrowable;
import static org.junit.Assert.assertEquals;
public class ClosureWithThrowableGeneralTest {
 @Test
 public void testThrowsNothing() {
        castClosureWithThrowable(() -> {
      throw new Exception("expect exception");
    }).thatThrowsNothing().call();
 }

 @Test
 public void testThrowsNothingNormalOperation() {
    castClosureWithThrowable(() -> {}).thatThrowsNothing().call();
 }

 @Test
 public void testOnException() {
        AtomicReference<java.lang.Throwable> reference = new AtomicReference<>();
        java.lang.Exception expected = new java.lang.Exception("expected");
        try {
        castClosureWithThrowable(() -> {
      throw expected;
    }).onException(reference::set).call();
        } catch (Throwable ignored) {}
        assertEquals(expected, reference.get());
 }

}
