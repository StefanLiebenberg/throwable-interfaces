package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.ConsumerWithThrowable.castConsumerWithThrowable;
public class ConsumerWithThrowableIgnoresTest {
 @Test(expected = Exception.class)
 public void testThrowExceptionWithNoIgnores() {
    castConsumerWithThrowable((v1) -> {
      throw new Exception("expected error");
    }).thatIgnores().accept(null);
 }

 @Test
 public void testThrowCheckedWithIgnore() {
    castConsumerWithThrowable((v1) -> {
      throw new Exception("expected error");
    }).thatIgnores(Exception.class).accept(null);
 }

 @Test
 public void testThrowIgnoreThrowables() {
    castConsumerWithThrowable((v1) -> {
      throw new Exception("expected error");
    }).thatIgnoresThrowables().accept(null);
 }

 @Test(expected = RuntimeException.class)
 public void testThrowRuntimeException() {
    castConsumerWithThrowable((v1) -> {
      throw new RuntimeException("expected error");
    }).accept(null);
 }

 @Test(expected = Error.class)
 public void testThrowError() {
    castConsumerWithThrowable((v1) -> {
      throw new Error("expected error");
    }).accept(null);
 }

 @Test(expected = Throwable.class)
 public void testThrowThrowable() {
    castConsumerWithThrowable((v1) -> {
       throw new Throwable("expected throwable");
    }).accept(null);
 }

 @Test
 public void testAnnotatedWithFunctionalInterface() {
    ConsumerWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
 }

 @Test
 public void testNormalOperation() {
    castConsumerWithThrowable((v1) -> {
    }).accept(null);
 }

}
