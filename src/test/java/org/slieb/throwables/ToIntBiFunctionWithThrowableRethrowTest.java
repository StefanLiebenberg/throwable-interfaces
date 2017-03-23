package org.slieb.throwables;
import org.junit.Test;
import java.io.IOException;
import static org.slieb.throwables.ToIntBiFunctionWithThrowable.rethrowToIntBiFunction;
@java.lang.SuppressWarnings({"CodeBlock2Expr"})
public class ToIntBiFunctionWithThrowableRethrowTest {


  @Test
  public void testThrowCheckedException() {
    IOException expected = new IOException("EXPECTED ERROR");
    try {
      rethrowToIntBiFunction((v1, v2) -> {
        throw expected;
      }).applyAsInt(null, null);
    } catch (IOException actual) {
      org.junit.Assert.assertEquals(expected, actual);
    }
  }


  @Test
  public void testNormalOperation() {
    try {
      rethrowToIntBiFunction((v1, v2) -> {
        if(false) throw new IOException();
        return 0;
      }).applyAsInt(null, null);
    } catch (IOException ignored) {
      org.junit.Assert.fail("");
    }
  }


}
