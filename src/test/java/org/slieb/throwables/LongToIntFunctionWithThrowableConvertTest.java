package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.LongToIntFunctionWithThrowable.asLongToIntFunctionWithThrowable;
public class LongToIntFunctionWithThrowableConvertTest {
 @Test(expected = RuntimeException.class)
 public void testThrowRuntimeException() {
    asLongToIntFunctionWithThrowable((v1) -> {
      throw new RuntimeException("expected error");
    }).applyAsInt(0);
 }

 @Test(expected = Error.class)
 public void testThrowError() {
    asLongToIntFunctionWithThrowable((v1) -> {
      throw new Error("expected error");
    }).applyAsInt(0);
 }

 @Test
 public void testAnnotatedWithFunctionalInterface() {
    LongToIntFunctionWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
 }

 @Test
 public void testNormalOperation() {
    asLongToIntFunctionWithThrowable((v1) -> {
 return 0;
    }).applyAsInt(0);
 }

}
