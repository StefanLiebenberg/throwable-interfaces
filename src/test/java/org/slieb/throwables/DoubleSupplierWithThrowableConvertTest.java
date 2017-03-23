package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.DoubleSupplierWithThrowable.asDoubleSupplierWithThrowable;
public class DoubleSupplierWithThrowableConvertTest {
 @Test(expected = RuntimeException.class)
 public void testThrowRuntimeException() {
    asDoubleSupplierWithThrowable(() -> {
      throw new RuntimeException("expected error");
    }).getAsDouble();
 }

 @Test(expected = Error.class)
 public void testThrowError() {
    asDoubleSupplierWithThrowable(() -> {
      throw new Error("expected error");
    }).getAsDouble();
 }

 @Test
 public void testAnnotatedWithFunctionalInterface() {
    DoubleSupplierWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
 }

 @Test
 public void testNormalOperation() {
    asDoubleSupplierWithThrowable(() -> {
 return 0;
    }).getAsDouble();
 }

}
