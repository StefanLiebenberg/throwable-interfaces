package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.DoubleFunctionWithThrowable.asDoubleFunctionWithThrowable;
public class DoubleFunctionWithThrowableConvertTest {
 @Test(expected = RuntimeException.class)
 public void testThrowRuntimeException() {
    asDoubleFunctionWithThrowable((v1) -> {
      throw new RuntimeException("expected error");
    }).apply(0);
 }

 @Test(expected = Error.class)
 public void testThrowError() {
    asDoubleFunctionWithThrowable((v1) -> {
      throw new Error("expected error");
    }).apply(0);
 }

 @Test
 public void testAnnotatedWithFunctionalInterface() {
    DoubleFunctionWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
 }

 @Test
 public void testNormalOperation() {
    asDoubleFunctionWithThrowable((v1) -> {
 return null;
    }).apply(0);
 }

}
