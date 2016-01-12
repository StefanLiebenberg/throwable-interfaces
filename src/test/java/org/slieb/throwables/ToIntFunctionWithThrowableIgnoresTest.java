package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.ToIntFunctionWithThrowable.castToIntFunctionWithThrowable;
public class ToIntFunctionWithThrowableIgnoresTest {
 @Test(expected = RuntimeException.class)
 public void testThrowRuntimeException() {
    castToIntFunctionWithThrowable((v1) -> {
      throw new RuntimeException("expected error");
    }).applyAsInt(null);
 }

 @Test(expected = Error.class)
 public void testThrowError() {
    castToIntFunctionWithThrowable((v1) -> {
      throw new Error("expected error");
    }).applyAsInt(null);
 }

 @Test(expected = Throwable.class)
 public void testThrowThrowable() {
    castToIntFunctionWithThrowable((v1) -> {
       throw new Throwable("expected throwable");
    }).applyAsInt(null);
 }

 @Test
 public void testAnnotatedWithFunctionalInterface() {
    ToIntFunctionWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
 }

 @Test
 public void testNormalOperation() {
    castToIntFunctionWithThrowable((v1) -> {
 return 0;
    }).applyAsInt(null);
 }

}
