package org.slieb.throwables;
import org.junit.*;
import static org.slieb.throwables.DoubleFunctionWithThrowable.castDoubleFunctionWithThrowable;
public class DoubleFunctionWithThrowableTest {
 @Test(expected = SuppressedException.class)
 public void testThrowCheckedException() {
    castDoubleFunctionWithThrowable((v1) -> {
      throw new Exception("expected error");
    }).apply(0);
 }

 @Test(expected = RuntimeException.class)
 public void testThrowRuntimeException() {
    castDoubleFunctionWithThrowable((v1) -> {
      throw new RuntimeException("expected error");
    }).apply(0);
 }

 @Test(expected = Error.class)
 public void testThrowError() {
    castDoubleFunctionWithThrowable((v1) -> {
      throw new Error("expected error");
    }).apply(0);
 }

 @Test(expected = Throwable.class)
 public void testThrowThrowable() {
    castDoubleFunctionWithThrowable((v1) -> {
       throw new Throwable("expected throwable");
    }).apply(0);
 }

 @Test
 public void testAnnotatedWithFunctionalInterface() {
    DoubleFunctionWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
 }

 @Test
 public void testNormalOperation() {
    castDoubleFunctionWithThrowable((v1) -> {
 return null;
    }).apply(0);
 }

}
