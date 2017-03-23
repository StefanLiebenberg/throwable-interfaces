package org.slieb.throwables;
import org.junit.Test;
import java.io.IOException;
import static org.slieb.throwables.PredicateWithThrowable.aPredicateThatUnSafelyThrowsUncheckedThrowable;
@java.lang.SuppressWarnings({"CodeBlock2Expr"})
public class PredicateWithThrowableRethrowTest {


  @Test
  public void testThrowCheckedException() {
    IOException expected = new IOException("EXPECTED ERROR");
    IOException actual = null;
    try {
      aPredicateThatUnSafelyThrowsUncheckedThrowable((v1) -> {
        throw expected;
      }).test(null);
      org.junit.Assert.fail("Exception should have been thrown");
    } catch (IOException e) {
      actual=e;
    }
    org.junit.Assert.assertEquals(expected, actual);
  }


  @Test
  public void testNormalOperation() {
    try {
      aPredicateThatUnSafelyThrowsUncheckedThrowable((v1) -> {
        if(false) throw new IOException();
        return false;
      }).test(null);
    } catch (IOException ignored) {
      org.junit.Assert.fail("");
    }
  }


}
