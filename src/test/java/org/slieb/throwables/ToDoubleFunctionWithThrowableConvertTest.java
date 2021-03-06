package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.ToDoubleFunctionWithThrowable.asToDoubleFunctionWithThrowable;
public class ToDoubleFunctionWithThrowableConvertTest {
 @Test(expected = RuntimeException.class)
 public void testThrowRuntimeException() {
    asToDoubleFunctionWithThrowable((v1) -> {
      throw new RuntimeException("expected error");
    }).applyAsDouble(null);
 }

 @Test(expected = Error.class)
 public void testThrowError() {
    asToDoubleFunctionWithThrowable((v1) -> {
      throw new Error("expected error");
    }).applyAsDouble(null);
 }

 @Test
 public void testAnnotatedWithFunctionalInterface() {
    ToDoubleFunctionWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
 }

 @Test
 public void testNormalOperation() {
    asToDoubleFunctionWithThrowable((v1) -> {
 return 0;
    }).applyAsDouble(null);
 }

}
