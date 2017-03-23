package org.slieb.throwables;

import org.junit.Test;
import java.util.concurrent.atomic.AtomicReference;
import static org.slieb.throwables.BiConsumerWithThrowable.castBiConsumerWithThrowable;
import static org.junit.Assert.assertEquals;
public class BiConsumerWithThrowableGeneralTest {
 @Test
 public void testThrowsNothing() {
        castBiConsumerWithThrowable((v1, v2) -> {
      throw new Exception("expect exception");
    }).thatThrowsNothing().accept(null, null);
 }

 @Test
 public void testThrowsNothingNormalOperation() {
    castBiConsumerWithThrowable((v1, v2) -> {}).thatThrowsNothing().accept(null, null);
 }

 @Test
 public void testOnException() {
        AtomicReference<java.lang.Throwable> reference = new AtomicReference<>();
        java.lang.Exception expected = new java.lang.Exception("expected");
        try {
        castBiConsumerWithThrowable((v1, v2) -> {
      throw expected;
    }).onException(reference::set).accept(null, null);
        } catch (Throwable ignored) {}
        assertEquals(expected, reference.get());
 }

}
