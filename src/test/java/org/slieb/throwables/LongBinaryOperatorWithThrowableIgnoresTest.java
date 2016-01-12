package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.LongBinaryOperatorWithThrowable.castLongBinaryOperatorWithThrowable;
public class LongBinaryOperatorWithThrowableIgnoresTest {
 @Test(expected = RuntimeException.class)
 public void testThrowRuntimeException() {
    castLongBinaryOperatorWithThrowable((v1, v2) -> {
      throw new RuntimeException("expected error");
    }).applyAsLong(0, 0);
 }

 @Test(expected = Error.class)
 public void testThrowError() {
    castLongBinaryOperatorWithThrowable((v1, v2) -> {
      throw new Error("expected error");
    }).applyAsLong(0, 0);
 }

 @Test(expected = Throwable.class)
 public void testThrowThrowable() {
    castLongBinaryOperatorWithThrowable((v1, v2) -> {
       throw new Throwable("expected throwable");
    }).applyAsLong(0, 0);
 }

 @Test
 public void testAnnotatedWithFunctionalInterface() {
    LongBinaryOperatorWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
 }

 @Test
 public void testNormalOperation() {
    castLongBinaryOperatorWithThrowable((v1, v2) -> {
 return 0;
    }).applyAsLong(0, 0);
 }

}
