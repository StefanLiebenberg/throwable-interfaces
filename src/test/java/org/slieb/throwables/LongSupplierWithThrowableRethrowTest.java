package org.slieb.throwables;
import org.junit.Test;
import java.io.IOException;
import static org.slieb.throwables.LongSupplierWithThrowable.rethrowLongSupplier;
@java.lang.SuppressWarnings({"CodeBlock2Expr"})
public class LongSupplierWithThrowableRethrowTest {


  @Test
  public void testThrowCheckedException() {
    IOException expected = new IOException("EXPECTED ERROR");
    try {
      rethrowLongSupplier(() -> {
        throw expected;
      }).getAsLong();
    } catch (IOException actual) {
      org.junit.Assert.assertEquals(expected, actual);
    }
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
