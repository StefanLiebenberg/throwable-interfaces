package org.slieb.throwables;
import org.junit.*;
import static org.slieb.throwables.BinaryOperatorWithThrowable.castBinaryOperatorWithThrowable;
public class BinaryOperatorWithThrowableTest {
 @Test(expected = SuppressedException.class)
 public void testThrowCheckedException() {
    castBinaryOperatorWithThrowable((v1, v2) -> {
      throw new Exception("expected error");
    }).apply(null, null);
 }

 @Test(expected = RuntimeException.class)
 public void testThrowRuntimeException() {
    castBinaryOperatorWithThrowable((v1, v2) -> {
      throw new RuntimeException("expected error");
    }).apply(null, null);
 }

 @Test(expected = Error.class)
 public void testThrowError() {
    castBinaryOperatorWithThrowable((v1, v2) -> {
      throw new Error("expected error");
    }).apply(null, null);
 }

 @Test(expected = Throwable.class)
 public void testThrowThrowable() {
    castBinaryOperatorWithThrowable((v1, v2) -> {
       throw new Throwable("expected throwable");
    }).apply(null, null);
 }

 @Test
 public void testAnnotatedWithFunctionalInterface() {
    BinaryOperatorWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
 }

 @Test
 public void testNormalOperation() {
    castBinaryOperatorWithThrowable((v1, v2) -> {
 return null;
    }).apply(null, null);
 }

}
