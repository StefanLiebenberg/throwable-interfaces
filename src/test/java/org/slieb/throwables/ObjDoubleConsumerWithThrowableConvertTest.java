package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.ObjDoubleConsumerWithThrowable.asObjDoubleConsumerWithThrowable;
public class ObjDoubleConsumerWithThrowableConvertTest {
 @Test(expected = RuntimeException.class)
 public void testThrowRuntimeException() {
    asObjDoubleConsumerWithThrowable((v1, v2) -> {
      throw new RuntimeException("expected error");
    }).accept(null, 0);
 }

 @Test(expected = Error.class)
 public void testThrowError() {
    asObjDoubleConsumerWithThrowable((v1, v2) -> {
      throw new Error("expected error");
    }).accept(null, 0);
 }

 @Test
 public void testAnnotatedWithFunctionalInterface() {
    ObjDoubleConsumerWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
 }

 @Test
 public void testNormalOperation() {
    asObjDoubleConsumerWithThrowable((v1, v2) -> {
    }).accept(null, 0);
 }

}
