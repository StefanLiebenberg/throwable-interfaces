package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.BiConsumerWithThrowable.asBiConsumerWithThrowable;
public class BiConsumerWithThrowableConvertTest {
 @Test(expected = RuntimeException.class)
 public void testThrowRuntimeException() {
    asBiConsumerWithThrowable((v1, v2) -> {
      throw new RuntimeException("expected error");
    }).accept(null, null);
 }

 @Test(expected = Error.class)
 public void testThrowError() {
    asBiConsumerWithThrowable((v1, v2) -> {
      throw new Error("expected error");
    }).accept(null, null);
 }

 @Test
 public void testAnnotatedWithFunctionalInterface() {
    BiConsumerWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
 }

 @Test
 public void testNormalOperation() {
    asBiConsumerWithThrowable((v1, v2) -> {
    }).accept(null, null);
 }

}
