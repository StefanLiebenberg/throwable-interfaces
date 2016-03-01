package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.ClosureWithThrowable.asClosureWithThrowable;
public class ClosureWithThrowableConvertTest {
 @Test(expected = RuntimeException.class)
 public void testThrowRuntimeException() {
    asClosureWithThrowable(() -> {
      throw new RuntimeException("expected error");
    }).call();
 }

 @Test(expected = Error.class)
 public void testThrowError() {
    asClosureWithThrowable(() -> {
      throw new Error("expected error");
    }).call();
 }

 @Test
 public void testAnnotatedWithFunctionalInterface() {
    ClosureWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
 }

 @Test
 public void testNormalOperation() {
    asClosureWithThrowable(() -> {
    }).call();
 }

}
