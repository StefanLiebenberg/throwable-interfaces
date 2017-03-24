package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.DoubleConsumerWithThrowable.castDoubleConsumerWithThrowable;
public class DoubleConsumerWithThrowableCastTest {
 @Test(expected = SuppressedException.class)
 public void testThrowCheckedException() {
    castDoubleConsumerWithThrowable((v1) -> {
      throw new Exception("expected error");
    }).accept(0);
 }

 @Test(expected = RuntimeException.class)
 public void testThrowRuntimeException() {
    castDoubleConsumerWithThrowable((v1) -> {
      throw new RuntimeException("expected error");
    }).accept(0);
 }

 @Test(expected = Error.class)
 public void testThrowError() {
    castDoubleConsumerWithThrowable((v1) -> {
      throw new Error("expected error");
    }).accept(0);
 }

 @Test(expected = Throwable.class)
 public void testThrowThrowable() {
    castDoubleConsumerWithThrowable((v1) -> {
       throw new Throwable("expected throwable");
    }).accept(0);
 }

 @Test
 public void testAnnotatedWithFunctionalInterface() {
    DoubleConsumerWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
 }

 @Test
 public void testNormalOperation() {
    castDoubleConsumerWithThrowable((v1) -> {
    }).accept(0);
 }

}
