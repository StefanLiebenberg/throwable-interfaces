package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.IntConsumerWithThrowable.asIntConsumerWithThrowable;
public class IntConsumerWithThrowableConvertTest {
 @Test(expected = RuntimeException.class)
 public void testThrowRuntimeException() {
    asIntConsumerWithThrowable((v1) -> {
      throw new RuntimeException("expected error");
    }).accept(0);
 }

 @Test(expected = Error.class)
 public void testThrowError() {
    asIntConsumerWithThrowable((v1) -> {
      throw new Error("expected error");
    }).accept(0);
 }

 @Test
 public void testAnnotatedWithFunctionalInterface() {
    IntConsumerWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
 }

 @Test
 public void testNormalOperation() {
    asIntConsumerWithThrowable((v1) -> {
    }).accept(0);
 }

}
