package org.slieb.throwables;

import org.junit.Test;
import java.util.concurrent.atomic.AtomicReference;
import static org.slieb.throwables.ObjDoubleConsumerWithThrowable.castObjDoubleConsumerWithThrowable;
import static org.junit.Assert.assertEquals;
public class ObjDoubleConsumerWithThrowableGeneralTest {
 @Test
 public void testThrowsNothing() {
        castObjDoubleConsumerWithThrowable((v1, v2) -> {
      throw new Exception("expect exception");
    }).thatThrowsNothing().accept(null, 0);
 }

 @Test
 public void testThrowsNothingNormalOperation() {
    castObjDoubleConsumerWithThrowable((v1, v2) -> {}).thatThrowsNothing().accept(null, 0);
 }

 @Test
 public void testOnException() {
        AtomicReference<java.lang.Throwable> reference = new AtomicReference<>();
        java.lang.Exception expected = new java.lang.Exception("expected");
        try {
        castObjDoubleConsumerWithThrowable((v1, v2) -> {
      throw expected;
    }).onException(reference::set).accept(null, 0);
        } catch (Throwable ignored) {}
        assertEquals(expected, reference.get());
 }

}
