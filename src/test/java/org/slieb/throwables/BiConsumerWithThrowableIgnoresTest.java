package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.BiConsumerWithThrowable.castBiConsumerWithThrowable;
public class BiConsumerWithThrowableIgnoresTest {
 @Test(expected = Exception.class)
 public void testThrowExceptionWithNoIgnores() {
    castBiConsumerWithThrowable((v1, v2) -> {
      throw new Exception("expected error");
    }).thatIgnores().accept(null, null);
 }

 @Test
 public void testThrowCheckedWithIgnore() {
    castBiConsumerWithThrowable((v1, v2) -> {
      throw new Exception("expected error");
    }).thatIgnores(Exception.class).accept(null, null);
 }

 @Test
 public void testThrowIgnoreThrowables() {
    castBiConsumerWithThrowable((v1, v2) -> {
      throw new Exception("expected error");
    }).thatIgnoresThrowables().accept(null, null);
 }

 @Test(expected = RuntimeException.class)
 public void testThrowRuntimeException() {
    castBiConsumerWithThrowable((v1, v2) -> {
      throw new RuntimeException("expected error");
    }).accept(null, null);
 }

 @Test(expected = Error.class)
 public void testThrowError() {
    castBiConsumerWithThrowable((v1, v2) -> {
      throw new Error("expected error");
    }).accept(null, null);
 }

 @Test(expected = Throwable.class)
 public void testThrowThrowable() {
    castBiConsumerWithThrowable((v1, v2) -> {
       throw new Throwable("expected throwable");
    }).accept(null, null);
 }

 @Test
 public void testAnnotatedWithFunctionalInterface() {
    BiConsumerWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
 }

 @Test
 public void testNormalOperation() {
    castBiConsumerWithThrowable((v1, v2) -> {
    }).accept(null, null);
 }

}
