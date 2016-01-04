package org.slieb.throwables;
import org.junit.*;
import static org.slieb.throwables.LongToIntFunctionWithThrowable.castLongToIntFunctionWithThrowable;
public class LongToIntFunctionWithThrowableTest {
 @Test(expected = SuppressedException.class)
 public void testThrowCheckedException() {
    castLongToIntFunctionWithThrowable((v1) -> {
      throw new Exception("expected error");
    }).applyAsInt(0);
 }

 @Test(expected = RuntimeException.class)
 public void testThrowRuntimeException() {
    castLongToIntFunctionWithThrowable((v1) -> {
      throw new RuntimeException("expected error");
    }).applyAsInt(0);
 }

 @Test(expected = Error.class)
 public void testThrowError() {
    castLongToIntFunctionWithThrowable((v1) -> {
      throw new Error("expected error");
    }).applyAsInt(0);
 }

 @Test(expected = Throwable.class)
 public void testThrowThrowable() {
    castLongToIntFunctionWithThrowable((v1) -> {
       throw new Throwable("expected throwable");
    }).applyAsInt(0);
 }

 @Test
 public void testAnnotatedWithFunctionalInterface() {
    LongToIntFunctionWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
 }

 @Test
 public void testNormalOperation() {
    castLongToIntFunctionWithThrowable((v1) -> {
 return 0;
    }).applyAsInt(0);
 }

}
