package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.ObjDoubleConsumerWithThrowable.castObjDoubleConsumerWithThrowable;
public class ObjDoubleConsumerWithThrowableCastTest {
 @Test(expected = SuppressedException.class)
 public void testThrowCheckedException() {
    castObjDoubleConsumerWithThrowable((v1, v2) -> {
      throw new Exception("expected error");
    }).accept(null, 0);
 }

 @Test(expected = RuntimeException.class)
 public void testThrowRuntimeException() {
    castObjDoubleConsumerWithThrowable((v1, v2) -> {
      throw new RuntimeException("expected error");
    }).accept(null, 0);
 }

 @Test(expected = Error.class)
 public void testThrowError() {
    castObjDoubleConsumerWithThrowable((v1, v2) -> {
      throw new Error("expected error");
    }).accept(null, 0);
 }

 @Test(expected = Throwable.class)
 public void testThrowThrowable() {
    castObjDoubleConsumerWithThrowable((v1, v2) -> {
       throw new Throwable("expected throwable");
    }).accept(null, 0);
 }

 @Test
 public void testAnnotatedWithFunctionalInterface() {
    ObjDoubleConsumerWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
 }

 @Test
 public void testNormalOperation() {
    castObjDoubleConsumerWithThrowable((v1, v2) -> {
    }).accept(null, 0);
 }

}
