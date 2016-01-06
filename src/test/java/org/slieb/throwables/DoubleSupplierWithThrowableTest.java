package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.DoubleSupplierWithThrowable.castDoubleSupplierWithThrowable;
public class DoubleSupplierWithThrowableTest {
 @Test(expected = SuppressedException.class)
 public void testThrowCheckedException() {
    castDoubleSupplierWithThrowable(() -> {
      throw new Exception("expected error");
    }).getAsDouble();
 }

 @Test(expected = RuntimeException.class)
 public void testThrowRuntimeException() {
    castDoubleSupplierWithThrowable(() -> {
      throw new RuntimeException("expected error");
    }).getAsDouble();
 }

 @Test(expected = Error.class)
 public void testThrowError() {
    castDoubleSupplierWithThrowable(() -> {
      throw new Error("expected error");
    }).getAsDouble();
 }

 @Test(expected = Throwable.class)
 public void testThrowThrowable() {
    castDoubleSupplierWithThrowable(() -> {
       throw new Throwable("expected throwable");
    }).getAsDouble();
 }

 @Test
 public void testAnnotatedWithFunctionalInterface() {
    DoubleSupplierWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
 }

 @Test
 public void testNormalOperation() {
    castDoubleSupplierWithThrowable(() -> {
 return 0;
    }).getAsDouble();
 }

}
