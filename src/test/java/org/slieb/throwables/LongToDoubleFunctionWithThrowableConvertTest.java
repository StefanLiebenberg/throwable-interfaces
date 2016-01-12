package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.LongToDoubleFunctionWithThrowable.asLongToDoubleFunctionWithThrowable;
public class LongToDoubleFunctionWithThrowableConvertTest {
 @Test(expected = RuntimeException.class)
 public void testThrowRuntimeException() {
    asLongToDoubleFunctionWithThrowable((v1) -> {
      throw new RuntimeException("expected error");
    }).applyAsDouble(0);
 }

 @Test(expected = Error.class)
 public void testThrowError() {
    asLongToDoubleFunctionWithThrowable((v1) -> {
      throw new Error("expected error");
    }).applyAsDouble(0);
 }

 @Test
 public void testAnnotatedWithFunctionalInterface() {
    LongToDoubleFunctionWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
 }

 @Test
 public void testNormalOperation() {
    asLongToDoubleFunctionWithThrowable((v1) -> {
 return 0;
    }).applyAsDouble(0);
 }

}
