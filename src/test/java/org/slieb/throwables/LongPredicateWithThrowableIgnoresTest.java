package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.LongPredicateWithThrowable.castLongPredicateWithThrowable;
public class LongPredicateWithThrowableIgnoresTest {
 @Test(expected = RuntimeException.class)
 public void testThrowRuntimeException() {
    castLongPredicateWithThrowable((v1) -> {
      throw new RuntimeException("expected error");
    }).test(0);
 }

 @Test(expected = Error.class)
 public void testThrowError() {
    castLongPredicateWithThrowable((v1) -> {
      throw new Error("expected error");
    }).test(0);
 }

 @Test(expected = Throwable.class)
 public void testThrowThrowable() {
    castLongPredicateWithThrowable((v1) -> {
       throw new Throwable("expected throwable");
    }).test(0);
 }

 @Test
 public void testAnnotatedWithFunctionalInterface() {
    LongPredicateWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
 }

 @Test
 public void testNormalOperation() {
    castLongPredicateWithThrowable((v1) -> {
 return false;
    }).test(0);
 }

}
