package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.IntSupplierWithThrowable.castIntSupplierWithThrowable;
public class IntSupplierWithThrowableIgnoresTest {
 @Test(expected = RuntimeException.class)
 public void testThrowRuntimeException() {
    castIntSupplierWithThrowable(() -> {
      throw new RuntimeException("expected error");
    }).getAsInt();
 }

 @Test(expected = Error.class)
 public void testThrowError() {
    castIntSupplierWithThrowable(() -> {
      throw new Error("expected error");
    }).getAsInt();
 }

 @Test(expected = Throwable.class)
 public void testThrowThrowable() {
    castIntSupplierWithThrowable(() -> {
       throw new Throwable("expected throwable");
    }).getAsInt();
 }

 @Test
 public void testAnnotatedWithFunctionalInterface() {
    IntSupplierWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
 }

 @Test
 public void testNormalOperation() {
    castIntSupplierWithThrowable(() -> {
 return 0;
    }).getAsInt();
 }

}
