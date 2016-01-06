package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.DoubleUnaryOperatorWithThrowable.castDoubleUnaryOperatorWithThrowable;
public class DoubleUnaryOperatorWithThrowableTest {
 @Test(expected = SuppressedException.class)
 public void testThrowCheckedException() {
    castDoubleUnaryOperatorWithThrowable((v1) -> {
      throw new Exception("expected error");
    }).applyAsDouble(0);
 }

 @Test(expected = RuntimeException.class)
 public void testThrowRuntimeException() {
    castDoubleUnaryOperatorWithThrowable((v1) -> {
      throw new RuntimeException("expected error");
    }).applyAsDouble(0);
 }

 @Test(expected = Error.class)
 public void testThrowError() {
    castDoubleUnaryOperatorWithThrowable((v1) -> {
      throw new Error("expected error");
    }).applyAsDouble(0);
 }

 @Test(expected = Throwable.class)
 public void testThrowThrowable() {
    castDoubleUnaryOperatorWithThrowable((v1) -> {
       throw new Throwable("expected throwable");
    }).applyAsDouble(0);
 }

 @Test
 public void testAnnotatedWithFunctionalInterface() {
    DoubleUnaryOperatorWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
 }

 @Test
 public void testNormalOperation() {
    castDoubleUnaryOperatorWithThrowable((v1) -> {
 return 0;
    }).applyAsDouble(0);
 }

}
