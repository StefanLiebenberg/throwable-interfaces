package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.LongUnaryOperatorWithThrowable.asLongUnaryOperatorWithThrowable;
public class LongUnaryOperatorWithThrowableConvertTest {
 @Test(expected = RuntimeException.class)
 public void testThrowRuntimeException() {
    asLongUnaryOperatorWithThrowable((v1) -> {
      throw new RuntimeException("expected error");
    }).applyAsLong(0);
 }

 @Test(expected = Error.class)
 public void testThrowError() {
    asLongUnaryOperatorWithThrowable((v1) -> {
      throw new Error("expected error");
    }).applyAsLong(0);
 }

 @Test
 public void testAnnotatedWithFunctionalInterface() {
    LongUnaryOperatorWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
 }

 @Test
 public void testNormalOperation() {
    asLongUnaryOperatorWithThrowable((v1) -> {
 return 0;
    }).applyAsLong(0);
 }

}
