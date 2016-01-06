package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.LongConsumerWithThrowable.castLongConsumerWithThrowable;
public class LongConsumerWithThrowableTest {
 @Test(expected = SuppressedException.class)
 public void testThrowCheckedException() {
    castLongConsumerWithThrowable((v1) -> {
      throw new Exception("expected error");
    }).accept(0);
 }

 @Test(expected = RuntimeException.class)
 public void testThrowRuntimeException() {
    castLongConsumerWithThrowable((v1) -> {
      throw new RuntimeException("expected error");
    }).accept(0);
 }

 @Test(expected = Error.class)
 public void testThrowError() {
    castLongConsumerWithThrowable((v1) -> {
      throw new Error("expected error");
    }).accept(0);
 }

 @Test(expected = Throwable.class)
 public void testThrowThrowable() {
    castLongConsumerWithThrowable((v1) -> {
       throw new Throwable("expected throwable");
    }).accept(0);
 }

 @Test
 public void testAnnotatedWithFunctionalInterface() {
    LongConsumerWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
 }

 @Test
 public void testNormalOperation() {
    castLongConsumerWithThrowable((v1) -> {
    }).accept(0);
 }

}
