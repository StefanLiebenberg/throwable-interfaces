package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.ObjLongConsumerWithThrowable.castObjLongConsumerWithThrowable;
public class ObjLongConsumerWithThrowableTest {
 @Test(expected = SuppressedException.class)
 public void testThrowCheckedException() {
    castObjLongConsumerWithThrowable((v1, v2) -> {
      throw new Exception("expected error");
    }).accept(null, 0);
 }

 @Test(expected = RuntimeException.class)
 public void testThrowRuntimeException() {
    castObjLongConsumerWithThrowable((v1, v2) -> {
      throw new RuntimeException("expected error");
    }).accept(null, 0);
 }

 @Test(expected = Error.class)
 public void testThrowError() {
    castObjLongConsumerWithThrowable((v1, v2) -> {
      throw new Error("expected error");
    }).accept(null, 0);
 }

 @Test(expected = Throwable.class)
 public void testThrowThrowable() {
    castObjLongConsumerWithThrowable((v1, v2) -> {
       throw new Throwable("expected throwable");
    }).accept(null, 0);
 }

 @Test
 public void testAnnotatedWithFunctionalInterface() {
    ObjLongConsumerWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
 }

 @Test
 public void testNormalOperation() {
    castObjLongConsumerWithThrowable((v1, v2) -> {
    }).accept(null, 0);
 }

}
