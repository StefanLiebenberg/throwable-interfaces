package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.ToLongBiFunctionWithThrowable.castToLongBiFunctionWithThrowable;
public class ToLongBiFunctionWithThrowableIgnoresTest {
 @Test(expected = RuntimeException.class)
 public void testThrowRuntimeException() {
    castToLongBiFunctionWithThrowable((v1, v2) -> {
      throw new RuntimeException("expected error");
    }).applyAsLong(null, null);
 }

 @Test(expected = Error.class)
 public void testThrowError() {
    castToLongBiFunctionWithThrowable((v1, v2) -> {
      throw new Error("expected error");
    }).applyAsLong(null, null);
 }

 @Test(expected = Throwable.class)
 public void testThrowThrowable() {
    castToLongBiFunctionWithThrowable((v1, v2) -> {
       throw new Throwable("expected throwable");
    }).applyAsLong(null, null);
 }

 @Test
 public void testAnnotatedWithFunctionalInterface() {
    ToLongBiFunctionWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
 }

 @Test
 public void testNormalOperation() {
    castToLongBiFunctionWithThrowable((v1, v2) -> {
 return 0;
    }).applyAsLong(null, null);
 }

}
