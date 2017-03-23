package org.slieb.throwables;
import org.junit.Test;
import java.io.IOException;
import static org.slieb.throwables.IntSupplierWithThrowable.rethrowIntSupplier;
@java.lang.SuppressWarnings({"CodeBlock2Expr"})
public class IntSupplierWithThrowableRethrowTest {


  @Test
  public void testThrowCheckedException() {
    IOException expected = new IOException("EXPECTED ERROR");
    try {
      rethrowIntSupplier(() -> {
        throw expected;
      }).getAsInt();
    } catch (IOException actual) {
      org.junit.Assert.assertEquals(expected, actual);
    }
  }


  @Test
  public void testNormalOperation() {
    try {
      rethrowIntSupplier(() -> {
        if(false) throw new IOException();
        return 0;
      }).getAsInt();
    } catch (IOException ignored) {
      org.junit.Assert.fail("");
    }
  }


}
