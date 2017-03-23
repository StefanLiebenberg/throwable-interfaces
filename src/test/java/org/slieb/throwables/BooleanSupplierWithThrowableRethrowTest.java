package org.slieb.throwables;
import org.junit.Test;
import java.io.IOException;
import static org.slieb.throwables.BooleanSupplierWithThrowable.aBooleanSupplierThatUnSafelyThrowsUncheckedThrowable;
@java.lang.SuppressWarnings({"CodeBlock2Expr"})
public class BooleanSupplierWithThrowableRethrowTest {


  @Test
  public void testThrowCheckedException() {
    IOException expected = new IOException("EXPECTED ERROR");
    IOException actual = null;
    try {
      aBooleanSupplierThatUnSafelyThrowsUncheckedThrowable(() -> {
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
      aBooleanSupplierThatUnSafelyThrowsUncheckedThrowable(() -> {
        if(false) throw new IOException();
        return false;
      }).getAsBoolean();
    } catch (IOException ignored) {
      org.junit.Assert.fail("");
    }
  }


}
