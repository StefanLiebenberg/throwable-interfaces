package org.slieb.throwables;

import org.junit.Test;
import java.util.concurrent.atomic.AtomicReference;
import static org.slieb.throwables.ConsumerWithThrowable.castConsumerWithThrowable;
import static org.junit.Assert.assertEquals;
public class ConsumerWithThrowableGeneralTest {
 @Test
 public void testThrowsNothing() {
        castConsumerWithThrowable((v1) -> {
      throw new Exception("expect exception");
    }).thatThrowsNothing().accept(null);
 }

 @Test
 public void testThrowsNothingNormalOperation() {
    castConsumerWithThrowable((v1) -> {}).thatThrowsNothing().accept(null);
 }

 @Test
 public void testOnException() {
        AtomicReference<java.lang.Throwable> reference = new AtomicReference<>();
        java.lang.Exception expected = new java.lang.Exception("expected");
        try {
        castConsumerWithThrowable((v1) -> {
      throw expected;
    }).onException(reference::set).accept(null);
        } catch (Throwable ignored) {}
        assertEquals(expected, reference.get());
 }

}
