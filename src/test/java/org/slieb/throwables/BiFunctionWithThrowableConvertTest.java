package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.BiFunctionWithThrowable.asBiFunctionWithThrowable;
public class BiFunctionWithThrowableConvertTest {
 @Test(expected = RuntimeException.class)
 public void testThrowRuntimeException() {
    asBiFunctionWithThrowable((v1, v2) -> {
      throw new RuntimeException("expected error");
    }).apply(null, null);
 }

 @Test(expected = Error.class)
 public void testThrowError() {
    asBiFunctionWithThrowable((v1, v2) -> {
      throw new Error("expected error");
    }).apply(null, null);
 }

 @Test
 public void testAnnotatedWithFunctionalInterface() {
    BiFunctionWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
 }

 @Test
 public void testNormalOperation() {
    asBiFunctionWithThrowable((v1, v2) -> {
 return null;
    }).apply(null, null);
 }

}
