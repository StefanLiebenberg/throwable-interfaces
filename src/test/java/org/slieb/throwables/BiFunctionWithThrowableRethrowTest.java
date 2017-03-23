package org.slieb.throwables;
import org.junit.Test;
import java.io.IOException;
import static org.slieb.throwables.BiFunctionWithThrowable.rethrowBiFunction;
@java.lang.SuppressWarnings({"CodeBlock2Expr"})
public class BiFunctionWithThrowableRethrowTest {


  @Test
  public void testThrowCheckedException() {
    IOException expected = new IOException("EXPECTED ERROR");
    try {
      rethrowBiFunction((v1, v2) -> {
        throw expected;
      }).apply(null, null);
    } catch (IOException actual) {
      org.junit.Assert.assertEquals(expected, actual);
    }
  }


  @Test
  public void testNormalOperation() {
    try {
      rethrowBiFunction((v1, v2) -> {
        if(false) throw new IOException();
        return null;
      }).apply(null, null);
    } catch (IOException ignored) {
      org.junit.Assert.fail("");
    }
  }


}
