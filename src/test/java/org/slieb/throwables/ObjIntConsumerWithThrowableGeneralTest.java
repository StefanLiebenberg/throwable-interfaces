package org.slieb.throwables;

import org.junit.Test;
import java.util.concurrent.atomic.AtomicReference;
import static org.slieb.throwables.ObjIntConsumerWithThrowable.castObjIntConsumerWithThrowable;
import static org.junit.Assert.assertEquals;
public class ObjIntConsumerWithThrowableGeneralTest {
 @Test
 public void testThrowsNothing() {
        castObjIntConsumerWithThrowable((v1, v2) -> {
      throw new Exception("expect exception");
    }).thatThrowsNothing().accept(null, 0);
 }

 @Test
 public void testThrowsNothingNormalOperation() {
    castObjIntConsumerWithThrowable((v1, v2) -> {}).thatThrowsNothing().accept(null, 0);
 }

 @Test
 public void testOnException() {
        AtomicReference<java.lang.Throwable> reference = new AtomicReference<>();
        java.lang.Exception expected = new java.lang.Exception("expected");
        try {
        castObjIntConsumerWithThrowable((v1, v2) -> {
      throw expected;
    }).onException(reference::set).accept(null, 0);
        } catch (Throwable ignored) {}
        assertEquals(expected, reference.get());
 }

}
