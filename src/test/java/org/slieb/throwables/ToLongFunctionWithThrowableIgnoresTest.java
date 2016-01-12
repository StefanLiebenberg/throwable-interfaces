package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.ToLongFunctionWithThrowable.castToLongFunctionWithThrowable;
public class ToLongFunctionWithThrowableIgnoresTest {
 @Test(expected = RuntimeException.class)
 public void testThrowRuntimeException() {
    castToLongFunctionWithThrowable((v1) -> {
      throw new RuntimeException("expected error");
    }).applyAsLong(null);
 }

 @Test(expected = Error.class)
 public void testThrowError() {
    castToLongFunctionWithThrowable((v1) -> {
      throw new Error("expected error");
    }).applyAsLong(null);
 }

 @Test(expected = Throwable.class)
 public void testThrowThrowable() {
    castToLongFunctionWithThrowable((v1) -> {
       throw new Throwable("expected throwable");
    }).applyAsLong(null);
 }

 @Test
 public void testAnnotatedWithFunctionalInterface() {
    ToLongFunctionWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
 }

 @Test
 public void testNormalOperation() {
    castToLongFunctionWithThrowable((v1) -> {
 return 0;
    }).applyAsLong(null);
 }

}
