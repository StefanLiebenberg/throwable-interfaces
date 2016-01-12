package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.DoublePredicateWithThrowable.castDoublePredicateWithThrowable;
public class DoublePredicateWithThrowableIgnoresTest {
 @Test(expected = RuntimeException.class)
 public void testThrowRuntimeException() {
    castDoublePredicateWithThrowable((v1) -> {
      throw new RuntimeException("expected error");
    }).test(0);
 }

 @Test(expected = Error.class)
 public void testThrowError() {
    castDoublePredicateWithThrowable((v1) -> {
      throw new Error("expected error");
    }).test(0);
 }

 @Test(expected = Throwable.class)
 public void testThrowThrowable() {
    castDoublePredicateWithThrowable((v1) -> {
       throw new Throwable("expected throwable");
    }).test(0);
 }

 @Test
 public void testAnnotatedWithFunctionalInterface() {
    DoublePredicateWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
 }

 @Test
 public void testNormalOperation() {
    castDoublePredicateWithThrowable((v1) -> {
 return false;
    }).test(0);
 }

}
