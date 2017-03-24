package org.slieb.throwables;

import org.junit.Test;
import java.util.concurrent.atomic.AtomicReference;
import static org.slieb.throwables.RunnableWithThrowable.castRunnableWithThrowable;
import static org.junit.Assert.assertEquals;
public class RunnableWithThrowableGeneralTest {
 @Test
 public void testThrowsNothing() {
        castRunnableWithThrowable(() -> {
      throw new Exception("expect exception");
    }).thatThrowsNothing().run();
 }

 @Test
 public void testThrowsNothingNormalOperation() {
    castRunnableWithThrowable(() -> {}).thatThrowsNothing().run();
 }

 @Test
 public void testOnException() {
        AtomicReference<java.lang.Throwable> reference = new AtomicReference<>();
        java.lang.Exception expected = new java.lang.Exception("expected");
        try {
        castRunnableWithThrowable(() -> {
      throw expected;
    }).onException(reference::set).run();
        } catch (Throwable ignored) {}
        assertEquals(expected, reference.get());
 }

}
