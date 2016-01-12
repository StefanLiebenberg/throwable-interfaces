package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.DoubleBinaryOperatorWithThrowable.asDoubleBinaryOperatorWithThrowable;
public class DoubleBinaryOperatorWithThrowableConvertTest {
 @Test(expected = RuntimeException.class)
 public void testThrowRuntimeException() {
    asDoubleBinaryOperatorWithThrowable((v1, v2) -> {
      throw new RuntimeException("expected error");
    }).applyAsDouble(0, 0);
 }

 @Test(expected = Error.class)
 public void testThrowError() {
    asDoubleBinaryOperatorWithThrowable((v1, v2) -> {
      throw new Error("expected error");
    }).applyAsDouble(0, 0);
 }

 @Test
 public void testAnnotatedWithFunctionalInterface() {
    DoubleBinaryOperatorWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
 }

 @Test
 public void testNormalOperation() {
    asDoubleBinaryOperatorWithThrowable((v1, v2) -> {
 return 0;
    }).applyAsDouble(0, 0);
 }

}
