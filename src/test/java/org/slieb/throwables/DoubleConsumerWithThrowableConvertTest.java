package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.DoubleConsumerWithThrowable.asDoubleConsumerWithThrowable;
public class DoubleConsumerWithThrowableConvertTest {
 @Test(expected = RuntimeException.class)
 public void testThrowRuntimeException() {
    asDoubleConsumerWithThrowable((v1) -> {
      throw new RuntimeException("expected error");
    }).accept(0);
 }

 @Test(expected = Error.class)
 public void testThrowError() {
    asDoubleConsumerWithThrowable((v1) -> {
      throw new Error("expected error");
    }).accept(0);
 }

 @Test
 public void testAnnotatedWithFunctionalInterface() {
    DoubleConsumerWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
 }

 @Test
 public void testNormalOperation() {
    asDoubleConsumerWithThrowable((v1) -> {
    }).accept(0);
 }

}
