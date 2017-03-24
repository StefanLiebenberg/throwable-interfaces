package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.ObjIntConsumerWithThrowable.asObjIntConsumerWithThrowable;
public class ObjIntConsumerWithThrowableConvertTest {
 @Test(expected = RuntimeException.class)
 public void testThrowRuntimeException() {
    asObjIntConsumerWithThrowable((v1, v2) -> {
      throw new RuntimeException("expected error");
    }).accept(null, 0);
 }

 @Test(expected = Error.class)
 public void testThrowError() {
    asObjIntConsumerWithThrowable((v1, v2) -> {
      throw new Error("expected error");
    }).accept(null, 0);
 }

 @Test
 public void testAnnotatedWithFunctionalInterface() {
    ObjIntConsumerWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
 }

 @Test
 public void testNormalOperation() {
    asObjIntConsumerWithThrowable((v1, v2) -> {
    }).accept(null, 0);
 }

}
