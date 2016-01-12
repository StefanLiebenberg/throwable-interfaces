package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.IntBinaryOperatorWithThrowable.castIntBinaryOperatorWithThrowable;
public class IntBinaryOperatorWithThrowableIgnoresTest {
 @Test(expected = RuntimeException.class)
 public void testThrowRuntimeException() {
    castIntBinaryOperatorWithThrowable((v1, v2) -> {
      throw new RuntimeException("expected error");
    }).applyAsInt(0, 0);
 }

 @Test(expected = Error.class)
 public void testThrowError() {
    castIntBinaryOperatorWithThrowable((v1, v2) -> {
      throw new Error("expected error");
    }).applyAsInt(0, 0);
 }

 @Test(expected = Throwable.class)
 public void testThrowThrowable() {
    castIntBinaryOperatorWithThrowable((v1, v2) -> {
       throw new Throwable("expected throwable");
    }).applyAsInt(0, 0);
 }

 @Test
 public void testAnnotatedWithFunctionalInterface() {
    IntBinaryOperatorWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
 }

 @Test
 public void testNormalOperation() {
    castIntBinaryOperatorWithThrowable((v1, v2) -> {
 return 0;
    }).applyAsInt(0, 0);
 }

}
