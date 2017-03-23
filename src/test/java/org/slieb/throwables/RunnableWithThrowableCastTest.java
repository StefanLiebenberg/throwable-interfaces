package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.RunnableWithThrowable.castRunnableWithThrowable;
public class RunnableWithThrowableCastTest {
 @Test(expected = SuppressedException.class)
 public void testThrowCheckedException() {
    castRunnableWithThrowable(() -> {
      throw new Exception("expected error");
    }).run();
 }

 @Test(expected = RuntimeException.class)
 public void testThrowRuntimeException() {
    castRunnableWithThrowable(() -> {
      throw new RuntimeException("expected error");
    }).run();
 }

 @Test(expected = Error.class)
 public void testThrowError() {
    castRunnableWithThrowable(() -> {
      throw new Error("expected error");
    }).run();
 }

 @Test(expected = Throwable.class)
 public void testThrowThrowable() {
    castRunnableWithThrowable(() -> {
       throw new Throwable("expected throwable");
    }).run();
 }

 @Test
 public void testAnnotatedWithFunctionalInterface() {
    RunnableWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
 }

 @Test
 public void testNormalOperation() {
    castRunnableWithThrowable(() -> {
    }).run();
 }

}
