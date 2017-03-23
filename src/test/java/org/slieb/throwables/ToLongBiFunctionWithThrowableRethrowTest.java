package org.slieb.throwables;
import org.junit.Test;
import java.io.IOException;
import static org.slieb.throwables.ToLongBiFunctionWithThrowable.rethrowToLongBiFunction;
@java.lang.SuppressWarnings({"CodeBlock2Expr"})
public class ToLongBiFunctionWithThrowableRethrowTest {


  @Test
  public void testThrowCheckedException() {
    IOException expected = new IOException("EXPECTED ERROR");
    try {
      rethrowToLongBiFunction((v1, v2) -> {
        throw expected;
      }).applyAsLong(null, null);
    } catch (IOException actual) {
      org.junit.Assert.assertEquals(expected, actual);
    }
  }


  @Test
  public void testNormalOperation() {
    try {
      rethrowToLongBiFunction((v1, v2) -> {
        if(false) throw new IOException();
        return 0;
      }).applyAsLong(null, null);
    } catch (IOException ignored) {
      org.junit.Assert.fail("");
    }
  }


}
