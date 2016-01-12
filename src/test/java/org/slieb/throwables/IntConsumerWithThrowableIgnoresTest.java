package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.IntConsumerWithThrowable.castIntConsumerWithThrowable;
public class IntConsumerWithThrowableIgnoresTest {
 @Test(expected = Exception.class)
 public void testThrowExceptionWithNoIgnores() {
    castIntConsumerWithThrowable((v1) -> {
      throw new Exception("expected error");
    }).thatIgnores().accept(0);
 }

 @Test
 public void testThrowCheckedWithIgnore() {
    castIntConsumerWithThrowable((v1) -> {
      throw new Exception("expected error");
    }).thatIgnores(Exception.class).accept(0);
 }

 @Test
 public void testThrowIgnoreThrowables() {
    castIntConsumerWithThrowable((v1) -> {
      throw new Exception("expected error");
    }).thatIgnoresThrowables().accept(0);
 }

 @Test(expected = RuntimeException.class)
 public void testThrowRuntimeException() {
    castIntConsumerWithThrowable((v1) -> {
      throw new RuntimeException("expected error");
    }).accept(0);
 }

 @Test(expected = Error.class)
 public void testThrowError() {
    castIntConsumerWithThrowable((v1) -> {
      throw new Error("expected error");
    }).accept(0);
 }

 @Test(expected = Throwable.class)
 public void testThrowThrowable() {
    castIntConsumerWithThrowable((v1) -> {
       throw new Throwable("expected throwable");
    }).accept(0);
 }

 @Test
 public void testAnnotatedWithFunctionalInterface() {
    IntConsumerWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
 }

 @Test
 public void testNormalOperation() {
    castIntConsumerWithThrowable((v1) -> {
    }).accept(0);
 }

}
