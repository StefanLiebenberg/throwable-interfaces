package org.slieb.throwables;
import org.junit.Test;
import java.io.IOException;
import static org.slieb.throwables.LongPredicateWithThrowable.rethrowLongPredicate;
@java.lang.SuppressWarnings({"CodeBlock2Expr"})
public class LongPredicateWithThrowableRethrowTest {


  @Test
  public void testThrowCheckedException() {
    IOException expected = new IOException("EXPECTED ERROR");
    try {
      rethrowLongPredicate((v1) -> {
        throw expected;
      }).test(0);
    } catch (IOException actual) {
      org.junit.Assert.assertEquals(expected, actual);
    }
  }


  @Test
  public void testNormalOperation() {
    try {
      rethrowLongPredicate((v1) -> {
        if(false) throw new IOException();
        return false;
      }).test(0);
    } catch (IOException ignored) {
      org.junit.Assert.fail("");
    }
  }


}
