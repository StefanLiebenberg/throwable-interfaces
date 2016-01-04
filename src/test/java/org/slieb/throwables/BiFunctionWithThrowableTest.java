package org.slieb.throwables;
import org.junit.*;
import static org.slieb.throwables.BiFunctionWithThrowable.castBiFunctionWithThrowable;
public class BiFunctionWithThrowableTest {
 @Test(expected = SuppressedException.class)
 public void testThrowCheckedException() {
    castBiFunctionWithThrowable((v1, v2) -> {
      throw new Exception("expected error");
    }).apply(null, null);
 }

 @Test(expected = RuntimeException.class)
 public void testThrowRuntimeException() {
    castBiFunctionWithThrowable((v1, v2) -> {
      throw new RuntimeException("expected error");
    }).apply(null, null);
 }

 @Test(expected = Error.class)
 public void testThrowError() {
    castBiFunctionWithThrowable((v1, v2) -> {
      throw new Error("expected error");
    }).apply(null, null);
 }

 @Test(expected = Throwable.class)
 public void testThrowThrowable() {
    castBiFunctionWithThrowable((v1, v2) -> {
       throw new Throwable("expected throwable");
    }).apply(null, null);
 }

 @Test
 public void testAnnotatedWithFunctionalInterface() {
    BiFunctionWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
 }

 @Test
 public void testNormalOperation() {
    castBiFunctionWithThrowable((v1, v2) -> {
 return null;
    }).apply(null, null);
 }

}
