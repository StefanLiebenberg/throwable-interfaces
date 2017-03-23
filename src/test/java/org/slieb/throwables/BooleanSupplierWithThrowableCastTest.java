package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.BooleanSupplierWithThrowable.castBooleanSupplierWithThrowable;
public class BooleanSupplierWithThrowableCastTest {
 @Test(expected = SuppressedException.class)
 public void testThrowCheckedException() {
    castBooleanSupplierWithThrowable(() -> {
      throw new Exception("expected error");
    }).getAsBoolean();
 }

 @Test(expected = RuntimeException.class)
 public void testThrowRuntimeException() {
    castBooleanSupplierWithThrowable(() -> {
      throw new RuntimeException("expected error");
    }).getAsBoolean();
 }

 @Test(expected = Error.class)
 public void testThrowError() {
    castBooleanSupplierWithThrowable(() -> {
      throw new Error("expected error");
    }).getAsBoolean();
 }

 @Test(expected = Throwable.class)
 public void testThrowThrowable() {
    castBooleanSupplierWithThrowable(() -> {
       throw new Throwable("expected throwable");
    }).getAsBoolean();
 }

 @Test
 public void testAnnotatedWithFunctionalInterface() {
    BooleanSupplierWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
 }

 @Test
 public void testNormalOperation() {
    castBooleanSupplierWithThrowable(() -> {
 return false;
    }).getAsBoolean();
 }

}
