package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.DoubleUnaryOperatorWithThrowable.asDoubleUnaryOperatorWithThrowable;
public class DoubleUnaryOperatorWithThrowableConvertTest {
 @Test(expected = RuntimeException.class)
 public void testThrowRuntimeException() {
    asDoubleUnaryOperatorWithThrowable((v1) -> {
      throw new RuntimeException("expected error");
    }).applyAsDouble(0);
 }

 @Test(expected = Error.class)
 public void testThrowError() {
    asDoubleUnaryOperatorWithThrowable((v1) -> {
      throw new Error("expected error");
    }).applyAsDouble(0);
 }

 @Test
 public void testAnnotatedWithFunctionalInterface() {
    DoubleUnaryOperatorWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
 }

 @Test
 public void testNormalOperation() {
    asDoubleUnaryOperatorWithThrowable((v1) -> {
 return 0;
    }).applyAsDouble(0);
 }

}
