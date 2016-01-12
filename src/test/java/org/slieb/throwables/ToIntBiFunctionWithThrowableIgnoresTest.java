package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.ToIntBiFunctionWithThrowable.castToIntBiFunctionWithThrowable;
public class ToIntBiFunctionWithThrowableIgnoresTest {
 @Test(expected = RuntimeException.class)
 public void testThrowRuntimeException() {
    castToIntBiFunctionWithThrowable((v1, v2) -> {
      throw new RuntimeException("expected error");
    }).applyAsInt(null, null);
 }

 @Test(expected = Error.class)
 public void testThrowError() {
    castToIntBiFunctionWithThrowable((v1, v2) -> {
      throw new Error("expected error");
    }).applyAsInt(null, null);
 }

 @Test(expected = Throwable.class)
 public void testThrowThrowable() {
    castToIntBiFunctionWithThrowable((v1, v2) -> {
       throw new Throwable("expected throwable");
    }).applyAsInt(null, null);
 }

 @Test
 public void testAnnotatedWithFunctionalInterface() {
    ToIntBiFunctionWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
 }

 @Test
 public void testNormalOperation() {
    castToIntBiFunctionWithThrowable((v1, v2) -> {
 return 0;
    }).applyAsInt(null, null);
 }

}
