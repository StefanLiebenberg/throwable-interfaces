package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.ToDoubleFunctionWithThrowable.castToDoubleFunctionWithThrowable;
public class ToDoubleFunctionWithThrowableTest {
 @Test(expected = SuppressedException.class)
 public void testThrowCheckedException() {
    castToDoubleFunctionWithThrowable((v1) -> {
      throw new Exception("expected error");
    }).applyAsDouble(null);
 }

 @Test(expected = RuntimeException.class)
 public void testThrowRuntimeException() {
    castToDoubleFunctionWithThrowable((v1) -> {
      throw new RuntimeException("expected error");
    }).applyAsDouble(null);
 }

 @Test(expected = Error.class)
 public void testThrowError() {
    castToDoubleFunctionWithThrowable((v1) -> {
      throw new Error("expected error");
    }).applyAsDouble(null);
 }

 @Test(expected = Throwable.class)
 public void testThrowThrowable() {
    castToDoubleFunctionWithThrowable((v1) -> {
       throw new Throwable("expected throwable");
    }).applyAsDouble(null);
 }

 @Test
 public void testAnnotatedWithFunctionalInterface() {
    ToDoubleFunctionWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
 }

 @Test
 public void testNormalOperation() {
    castToDoubleFunctionWithThrowable((v1) -> {
 return 0;
    }).applyAsDouble(null);
 }

}
