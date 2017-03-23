package org.slieb.throwables;

import org.junit.Test;
import java.util.concurrent.atomic.AtomicReference;
import static org.slieb.throwables.LongConsumerWithThrowable.castLongConsumerWithThrowable;
import static org.junit.Assert.assertEquals;
public class LongConsumerWithThrowableGeneralTest {
 @Test
 public void testThrowsNothing() {
        castLongConsumerWithThrowable((v1) -> {
      throw new Exception("expect exception");
    }).thatThrowsNothing().accept(0);
 }

 @Test
 public void testThrowsNothingNormalOperation() {
    castLongConsumerWithThrowable((v1) -> {}).thatThrowsNothing().accept(0);
 }

 @Test
 public void testOnException() {
        AtomicReference<java.lang.Throwable> reference = new AtomicReference<>();
        java.lang.Exception expected = new java.lang.Exception("expected");
        try {
        castLongConsumerWithThrowable((v1) -> {
      throw expected;
    }).onException(reference::set).accept(0);
        } catch (Throwable ignored) {}
        assertEquals(expected, reference.get());
 }

}
