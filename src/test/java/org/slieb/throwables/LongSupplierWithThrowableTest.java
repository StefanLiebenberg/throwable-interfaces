package org.slieb.throwables;
import org.junit.*;
import static org.slieb.throwables.LongSupplierWithThrowable.castLongSupplierWithThrowable;
public class LongSupplierWithThrowableTest {
 @Test(expected = SuppressedException.class)
 public void testThrowCheckedException() {
    castLongSupplierWithThrowable(() -> {
      throw new Exception("expected error");
    }).getAsLong();
 }

 @Test(expected = RuntimeException.class)
 public void testThrowRuntimeException() {
    castLongSupplierWithThrowable(() -> {
      throw new RuntimeException("expected error");
    }).getAsLong();
 }

 @Test(expected = Error.class)
 public void testThrowError() {
    castLongSupplierWithThrowable(() -> {
      throw new Error("expected error");
    }).getAsLong();
 }

 @Test(expected = Throwable.class)
 public void testThrowThrowable() {
    castLongSupplierWithThrowable(() -> {
       throw new Throwable("expected throwable");
    }).getAsLong();
 }

 @Test
 public void testAnnotatedWithFunctionalInterface() {
    LongSupplierWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
 }

 @Test
 public void testNormalOperation() {
    castLongSupplierWithThrowable(() -> {
 return 0;
    }).getAsLong();
 }

}
