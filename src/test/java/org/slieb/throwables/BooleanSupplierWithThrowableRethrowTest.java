package org.slieb.throwables;
import org.junit.Test;
import java.io.IOException;
import static org.slieb.throwables.BooleanSupplierWithThrowable.rethrowBooleanSupplier;
@java.lang.SuppressWarnings({"CodeBlock2Expr"})
public class BooleanSupplierWithThrowableRethrowTest {


  @Test
  public void testThrowCheckedException() {
    IOException expected = new IOException("EXPECTED ERROR");
    IOException actual = null;
    try {
      rethrowBooleanSupplier(() -> {
        throw expected;
      }).getAsBoolean();
      org.junit.Assert.fail("Exception should have been thrown");
    } catch (IOException e) {
      actual=e;
    }
    org.junit.Assert.assertEquals(expected, actual);
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
