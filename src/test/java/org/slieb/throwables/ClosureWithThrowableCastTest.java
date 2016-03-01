package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.ClosureWithThrowable.castClosureWithThrowable;
public class ClosureWithThrowableCastTest {
 @Test(expected = SuppressedException.class)
 public void testThrowCheckedException() {
    castClosureWithThrowable(() -> {
      throw new Exception("expected error");
    }).call();
 }

 @Test(expected = RuntimeException.class)
 public void testThrowRuntimeException() {
    castClosureWithThrowable(() -> {
      throw new RuntimeException("expected error");
    }).call();
 }

 @Test(expected = Error.class)
 public void testThrowError() {
    castClosureWithThrowable(() -> {
      throw new Error("expected error");
    }).call();
 }

 @Test(expected = Throwable.class)
 public void testThrowThrowable() {
    castClosureWithThrowable(() -> {
       throw new Throwable("expected throwable");
    }).call();
 }

 @Test
 public void testAnnotatedWithFunctionalInterface() {
    ClosureWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
 }

 @Test
 public void testNormalOperation() {
    castClosureWithThrowable(() -> {
    }).call();
 }

}
