package org.slieb.throwables;
import org.junit.Test;
import java.io.IOException;
import static org.slieb.throwables.BooleanSupplierWithThrowable.rethrowBooleanSupplier;
@java.lang.SuppressWarnings({"CodeBlock2Expr"})
public class BooleanSupplierWithThrowableRethrowTest {


  @Test
  public void testThrowCheckedException() {
    IOException expected = new IOException("EXPECTED ERROR");
    try {
      rethrowBooleanSupplier(() -> {
        throw expected;
      }).getAsBoolean();
    } catch (IOException actual) {
      org.junit.Assert.assertEquals(expected, actual);
    }
  }


  @Test
  public void testNormalOperation() {
    try {
      rethrowBooleanSupplier(() -> {
        if(false) throw new IOException();
        return false;
      }).getAsBoolean();
    } catch (IOException ignored) {
      org.junit.Assert.fail("");
    }
  }


}
