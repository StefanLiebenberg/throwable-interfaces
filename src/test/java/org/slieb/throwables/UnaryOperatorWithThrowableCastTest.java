package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.UnaryOperatorWithThrowable.castUnaryOperatorWithThrowable;
public class UnaryOperatorWithThrowableCastTest {
 @Test(expected = SuppressedException.class)
 public void testThrowCheckedException() {
    castUnaryOperatorWithThrowable((v1) -> {
      throw new Exception("expected error");
    }).apply(null);
 }

 @Test(expected = RuntimeException.class)
 public void testThrowRuntimeException() {
    castUnaryOperatorWithThrowable((v1) -> {
      throw new RuntimeException("expected error");
    }).apply(null);
 }

 @Test(expected = Error.class)
 public void testThrowError() {
    castUnaryOperatorWithThrowable((v1) -> {
      throw new Error("expected error");
    }).apply(null);
 }

 @Test(expected = Throwable.class)
 public void testThrowThrowable() {
    castUnaryOperatorWithThrowable((v1) -> {
       throw new Throwable("expected throwable");
    }).apply(null);
 }

 @Test
 public void testAnnotatedWithFunctionalInterface() {
    UnaryOperatorWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
 }

 @Test
 public void testNormalOperation() {
    castUnaryOperatorWithThrowable((v1) -> {
 return null;
    }).apply(null);
 }

}
