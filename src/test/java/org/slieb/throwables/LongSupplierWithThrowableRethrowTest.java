package org.slieb.throwables;
import org.junit.Test;
import java.io.IOException;
import static org.slieb.throwables.LongSupplierWithThrowable.rethrowLongSupplier;
@java.lang.SuppressWarnings({"CodeBlock2Expr"})
public class LongSupplierWithThrowableRethrowTest {


  @Test
  public void testThrowCheckedException() {
    IOException expected = new IOException("EXPECTED ERROR");
    IOException actual = null;
    try {
      rethrowLongSupplier(() -> {
        throw expected;
      }).getAsLong();
      org.junit.Assert.fail("Exception should have been thrown");
    } catch (IOException e) {
      actual=e;
    }
    org.junit.Assert.assertEquals(expected, actual);
  }


  @Test
  public void testNormalOperation() {
    try {
      rethrowLongSupplier(() -> {
        if(false) throw new IOException();
        return 0;
      }).getAsLong();
    } catch (IOException ignored) {
      org.junit.Assert.fail("");
    }
  }


}
