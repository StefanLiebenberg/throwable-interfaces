package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.LongFunctionWithThrowable.asLongFunctionWithThrowable;
public class LongFunctionWithThrowableConvertTest {
 @Test(expected = RuntimeException.class)
 public void testThrowRuntimeException() {
    asLongFunctionWithThrowable((v1) -> {
      throw new RuntimeException("expected error");
    }).apply(0);
 }

 @Test(expected = Error.class)
 public void testThrowError() {
    asLongFunctionWithThrowable((v1) -> {
      throw new Error("expected error");
    }).apply(0);
 }

 @Test
 public void testAnnotatedWithFunctionalInterface() {
    LongFunctionWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
 }

 @Test
 public void testNormalOperation() {
    asLongFunctionWithThrowable((v1) -> {
 return null;
    }).apply(0);
 }

}
