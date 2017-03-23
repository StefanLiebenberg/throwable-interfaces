package org.slieb.throwables;
import org.junit.Test;
import java.io.IOException;
import static org.slieb.throwables.PredicateWithThrowable.rethrowPredicate;
@java.lang.SuppressWarnings({"CodeBlock2Expr"})
public class PredicateWithThrowableRethrowTest {


  @Test
  public void testThrowCheckedException() {
    IOException expected = new IOException("EXPECTED ERROR");
    IOException actual = null;
    try {
      rethrowPredicate((v1) -> {
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
      rethrowPredicate((v1) -> {
        if(false) throw new IOException();
        return false;
      }).test(null);
    } catch (IOException ignored) {
      org.junit.Assert.fail("");
    }
  }


}
