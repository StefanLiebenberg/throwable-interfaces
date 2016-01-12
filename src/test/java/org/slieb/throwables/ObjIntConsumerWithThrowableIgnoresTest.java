package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.ObjIntConsumerWithThrowable.castObjIntConsumerWithThrowable;
public class ObjIntConsumerWithThrowableIgnoresTest {
 @Test(expected = Exception.class)
 public void testThrowExceptionWithNoIgnores() {
    castObjIntConsumerWithThrowable((v1, v2) -> {
      throw new Exception("expected error");
    }).thatIgnores().accept(null, 0);
 }

 @Test
 public void testThrowCheckedWithIgnore() {
    castObjIntConsumerWithThrowable((v1, v2) -> {
      throw new Exception("expected error");
    }).thatIgnores(Exception.class).accept(null, 0);
 }

 @Test
 public void testThrowIgnoreThrowables() {
    castObjIntConsumerWithThrowable((v1, v2) -> {
      throw new Exception("expected error");
    }).thatIgnoresThrowables().accept(null, 0);
 }

 @Test(expected = RuntimeException.class)
 public void testThrowRuntimeException() {
    castObjIntConsumerWithThrowable((v1, v2) -> {
      throw new RuntimeException("expected error");
    }).accept(null, 0);
 }

 @Test(expected = Error.class)
 public void testThrowError() {
    castObjIntConsumerWithThrowable((v1, v2) -> {
      throw new Error("expected error");
    }).accept(null, 0);
 }

 @Test(expected = Throwable.class)
 public void testThrowThrowable() {
    castObjIntConsumerWithThrowable((v1, v2) -> {
       throw new Throwable("expected throwable");
    }).accept(null, 0);
 }

 @Test
 public void testAnnotatedWithFunctionalInterface() {
    ObjIntConsumerWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
 }

 @Test
 public void testNormalOperation() {
    castObjIntConsumerWithThrowable((v1, v2) -> {
    }).accept(null, 0);
 }

}
