package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.IntBinaryOperatorWithThrowable.asIntBinaryOperatorWithThrowable;
public class IntBinaryOperatorWithThrowableConvertTest {
 @Test(expected = RuntimeException.class)
 public void testThrowRuntimeException() {
    asIntBinaryOperatorWithThrowable((v1, v2) -> {
      throw new RuntimeException("expected error");
    }).applyAsInt(0, 0);
 }

 @Test(expected = Error.class)
 public void testThrowError() {
    asIntBinaryOperatorWithThrowable((v1, v2) -> {
      throw new Error("expected error");
    }).applyAsInt(0, 0);
 }

 @Test
 public void testAnnotatedWithFunctionalInterface() {
    IntBinaryOperatorWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
 }

 @Test
 public void testNormalOperation() {
    asIntBinaryOperatorWithThrowable((v1, v2) -> {
 return 0;
    }).applyAsInt(0, 0);
 }

}
