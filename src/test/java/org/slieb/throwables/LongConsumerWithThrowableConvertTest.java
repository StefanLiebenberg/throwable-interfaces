package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.LongConsumerWithThrowable.asLongConsumerWithThrowable;
public class LongConsumerWithThrowableConvertTest {
 @Test(expected = RuntimeException.class)
 public void testThrowRuntimeException() {
    asLongConsumerWithThrowable((v1) -> {
      throw new RuntimeException("expected error");
    }).accept(0);
 }

 @Test(expected = Error.class)
 public void testThrowError() {
    asLongConsumerWithThrowable((v1) -> {
      throw new Error("expected error");
    }).accept(0);
 }

 @Test
 public void testAnnotatedWithFunctionalInterface() {
    LongConsumerWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
 }

 @Test
 public void testNormalOperation() {
    asLongConsumerWithThrowable((v1) -> {
    }).accept(0);
 }

}
