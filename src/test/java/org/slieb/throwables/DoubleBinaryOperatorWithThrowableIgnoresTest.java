package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.DoubleBinaryOperatorWithThrowable.castDoubleBinaryOperatorWithThrowable;
public class DoubleBinaryOperatorWithThrowableIgnoresTest {
 @Test(expected = RuntimeException.class)
 public void testThrowRuntimeException() {
    castDoubleBinaryOperatorWithThrowable((v1, v2) -> {
      throw new RuntimeException("expected error");
    }).applyAsDouble(0, 0);
 }

 @Test(expected = Error.class)
 public void testThrowError() {
    castDoubleBinaryOperatorWithThrowable((v1, v2) -> {
      throw new Error("expected error");
    }).applyAsDouble(0, 0);
 }

 @Test(expected = Throwable.class)
 public void testThrowThrowable() {
    castDoubleBinaryOperatorWithThrowable((v1, v2) -> {
       throw new Throwable("expected throwable");
    }).applyAsDouble(0, 0);
 }

 @Test
 public void testAnnotatedWithFunctionalInterface() {
    DoubleBinaryOperatorWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
 }

 @Test
 public void testNormalOperation() {
    castDoubleBinaryOperatorWithThrowable((v1, v2) -> {
 return 0;
    }).applyAsDouble(0, 0);
 }

}
