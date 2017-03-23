package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.IntSupplierWithThrowable.asIntSupplierWithThrowable;
public class IntSupplierWithThrowableConvertTest {
 @Test(expected = RuntimeException.class)
 public void testThrowRuntimeException() {
    asIntSupplierWithThrowable(() -> {
      throw new RuntimeException("expected error");
    }).getAsInt();
 }

 @Test(expected = Error.class)
 public void testThrowError() {
    asIntSupplierWithThrowable(() -> {
      throw new Error("expected error");
    }).getAsInt();
 }

 @Test
 public void testAnnotatedWithFunctionalInterface() {
    IntSupplierWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
 }

 @Test
 public void testNormalOperation() {
    asIntSupplierWithThrowable(() -> {
 return 0;
    }).getAsInt();
 }

}
