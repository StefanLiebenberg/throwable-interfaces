package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.SupplierWithThrowable.castSupplierWithThrowable;
public class SupplierWithThrowableIgnoresTest {
 @Test(expected = RuntimeException.class)
 public void testThrowRuntimeException() {
    castSupplierWithThrowable(() -> {
      throw new RuntimeException("expected error");
    }).get();
 }

 @Test(expected = Error.class)
 public void testThrowError() {
    castSupplierWithThrowable(() -> {
      throw new Error("expected error");
    }).get();
 }

 @Test(expected = Throwable.class)
 public void testThrowThrowable() {
    castSupplierWithThrowable(() -> {
       throw new Throwable("expected throwable");
    }).get();
 }

 @Test
 public void testAnnotatedWithFunctionalInterface() {
    SupplierWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
 }

 @Test
 public void testNormalOperation() {
    castSupplierWithThrowable(() -> {
 return null;
    }).get();
 }

}
