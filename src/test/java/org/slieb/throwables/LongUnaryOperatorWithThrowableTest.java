package org.slieb.throwables;
import org.junit.*;
import static org.slieb.throwables.LongUnaryOperatorWithThrowable.castLongUnaryOperatorWithThrowable;
public class LongUnaryOperatorWithThrowableTest {
 @Test(expected = SuppressedException.class)
 public void testThrowCheckedException() {
    castLongUnaryOperatorWithThrowable((v1) -> {
      throw new Exception("expected error");
    }).applyAsLong(0);
 }

 @Test(expected = RuntimeException.class)
 public void testThrowRuntimeException() {
    castLongUnaryOperatorWithThrowable((v1) -> {
      throw new RuntimeException("expected error");
    }).applyAsLong(0);
 }

 @Test(expected = Error.class)
 public void testThrowError() {
    castLongUnaryOperatorWithThrowable((v1) -> {
      throw new Error("expected error");
    }).applyAsLong(0);
 }

 @Test(expected = Throwable.class)
 public void testThrowThrowable() {
    castLongUnaryOperatorWithThrowable((v1) -> {
       throw new Throwable("expected throwable");
    }).applyAsLong(0);
 }

 @Test
 public void testAnnotatedWithFunctionalInterface() {
    LongUnaryOperatorWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
 }

 @Test
 public void testNormalOperation() {
    castLongUnaryOperatorWithThrowable((v1) -> {
 return 0;
    }).applyAsLong(0);
 }

}
