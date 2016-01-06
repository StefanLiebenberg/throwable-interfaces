package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.ToDoubleBiFunctionWithThrowable.castToDoubleBiFunctionWithThrowable;
public class ToDoubleBiFunctionWithThrowableTest {
 @Test(expected = SuppressedException.class)
 public void testThrowCheckedException() {
    castToDoubleBiFunctionWithThrowable((v1, v2) -> {
      throw new Exception("expected error");
    }).applyAsDouble(null, null);
 }

 @Test(expected = RuntimeException.class)
 public void testThrowRuntimeException() {
    castToDoubleBiFunctionWithThrowable((v1, v2) -> {
      throw new RuntimeException("expected error");
    }).applyAsDouble(null, null);
 }

 @Test(expected = Error.class)
 public void testThrowError() {
    castToDoubleBiFunctionWithThrowable((v1, v2) -> {
      throw new Error("expected error");
    }).applyAsDouble(null, null);
 }

 @Test(expected = Throwable.class)
 public void testThrowThrowable() {
    castToDoubleBiFunctionWithThrowable((v1, v2) -> {
       throw new Throwable("expected throwable");
    }).applyAsDouble(null, null);
 }

 @Test
 public void testAnnotatedWithFunctionalInterface() {
    ToDoubleBiFunctionWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
 }

 @Test
 public void testNormalOperation() {
    castToDoubleBiFunctionWithThrowable((v1, v2) -> {
 return 0;
    }).applyAsDouble(null, null);
 }

}
