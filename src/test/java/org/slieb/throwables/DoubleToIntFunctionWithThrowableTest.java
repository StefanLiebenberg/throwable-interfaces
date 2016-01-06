package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.DoubleToIntFunctionWithThrowable.castDoubleToIntFunctionWithThrowable;
public class DoubleToIntFunctionWithThrowableTest {
 @Test(expected = SuppressedException.class)
 public void testThrowCheckedException() {
    castDoubleToIntFunctionWithThrowable((v1) -> {
      throw new Exception("expected error");
    }).applyAsInt(0);
 }

 @Test(expected = RuntimeException.class)
 public void testThrowRuntimeException() {
    castDoubleToIntFunctionWithThrowable((v1) -> {
      throw new RuntimeException("expected error");
    }).applyAsInt(0);
 }

 @Test(expected = Error.class)
 public void testThrowError() {
    castDoubleToIntFunctionWithThrowable((v1) -> {
      throw new Error("expected error");
    }).applyAsInt(0);
 }

 @Test(expected = Throwable.class)
 public void testThrowThrowable() {
    castDoubleToIntFunctionWithThrowable((v1) -> {
       throw new Throwable("expected throwable");
    }).applyAsInt(0);
 }

 @Test
 public void testAnnotatedWithFunctionalInterface() {
    DoubleToIntFunctionWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
 }

 @Test
 public void testNormalOperation() {
    castDoubleToIntFunctionWithThrowable((v1) -> {
 return 0;
    }).applyAsInt(0);
 }

}
