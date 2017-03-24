package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.ToLongBiFunctionWithThrowable.asToLongBiFunctionWithThrowable;
public class ToLongBiFunctionWithThrowableConvertTest {
 @Test(expected = RuntimeException.class)
 public void testThrowRuntimeException() {
    asToLongBiFunctionWithThrowable((v1, v2) -> {
      throw new RuntimeException("expected error");
    }).applyAsLong(null, null);
 }

 @Test(expected = Error.class)
 public void testThrowError() {
    asToLongBiFunctionWithThrowable((v1, v2) -> {
      throw new Error("expected error");
    }).applyAsLong(null, null);
 }

 @Test
 public void testAnnotatedWithFunctionalInterface() {
    ToLongBiFunctionWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
 }

 @Test
 public void testNormalOperation() {
    asToLongBiFunctionWithThrowable((v1, v2) -> {
 return 0;
    }).applyAsLong(null, null);
 }

}
