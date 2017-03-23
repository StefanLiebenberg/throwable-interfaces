package org.slieb.throwables;
import org.junit.Test;
import java.io.IOException;
import static org.slieb.throwables.DoubleSupplierWithThrowable.rethrowDoubleSupplier;
@java.lang.SuppressWarnings({"CodeBlock2Expr"})
public class DoubleSupplierWithThrowableRethrowTest {


  @Test
  public void testThrowCheckedException() {
    IOException expected = new IOException("EXPECTED ERROR");
    try {
      rethrowDoubleSupplier(() -> {
        throw expected;
      }).getAsDouble();
    } catch (IOException actual) {
      org.junit.Assert.assertEquals(expected, actual);
    }
  }


  @Test
  public void testNormalOperation() {
    try {
      rethrowDoubleSupplier(() -> {
        if(false) throw new IOException();
        return 0;
      }).getAsDouble();
    } catch (IOException ignored) {
      org.junit.Assert.fail("");
    }
  }


}
