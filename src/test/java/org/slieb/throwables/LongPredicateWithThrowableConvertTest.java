package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.LongPredicateWithThrowable.asLongPredicateWithThrowable;
public class LongPredicateWithThrowableConvertTest {
 @Test(expected = RuntimeException.class)
 public void testThrowRuntimeException() {
    asLongPredicateWithThrowable((v1) -> {
      throw new RuntimeException("expected error");
    }).test(0);
 }

 @Test(expected = Error.class)
 public void testThrowError() {
    asLongPredicateWithThrowable((v1) -> {
      throw new Error("expected error");
    }).test(0);
 }

 @Test
 public void testAnnotatedWithFunctionalInterface() {
    LongPredicateWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
 }

 @Test
 public void testNormalOperation() {
    asLongPredicateWithThrowable((v1) -> {
 return false;
    }).test(0);
 }

}
