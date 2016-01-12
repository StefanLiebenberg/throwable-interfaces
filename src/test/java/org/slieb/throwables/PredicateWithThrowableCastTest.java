package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.PredicateWithThrowable.castPredicateWithThrowable;
public class PredicateWithThrowableCastTest {
 @Test(expected = SuppressedException.class)
 public void testThrowCheckedException() {
    castPredicateWithThrowable((v1) -> {
      throw new Exception("expected error");
    }).test(null);
 }

 @Test(expected = RuntimeException.class)
 public void testThrowRuntimeException() {
    castPredicateWithThrowable((v1) -> {
      throw new RuntimeException("expected error");
    }).test(null);
 }

 @Test(expected = Error.class)
 public void testThrowError() {
    castPredicateWithThrowable((v1) -> {
      throw new Error("expected error");
    }).test(null);
 }

 @Test(expected = Throwable.class)
 public void testThrowThrowable() {
    castPredicateWithThrowable((v1) -> {
       throw new Throwable("expected throwable");
    }).test(null);
 }

 @Test
 public void testAnnotatedWithFunctionalInterface() {
    PredicateWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
 }

 @Test
 public void testNormalOperation() {
    castPredicateWithThrowable((v1) -> {
 return false;
    }).test(null);
 }

}
