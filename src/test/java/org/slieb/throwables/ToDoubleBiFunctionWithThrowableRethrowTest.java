package org.slieb.throwables;
import org.junit.Test;
import java.io.IOException;
import static org.slieb.throwables.ToDoubleBiFunctionWithThrowable.rethrowToDoubleBiFunction;
@java.lang.SuppressWarnings({"CodeBlock2Expr"})
public class ToDoubleBiFunctionWithThrowableRethrowTest {


  @Test
  public void testThrowCheckedException() {
    IOException expected = new IOException("EXPECTED ERROR");
    try {
      rethrowToDoubleBiFunction((v1, v2) -> {
        throw expected;
      }).applyAsDouble(null, null);
    } catch (IOException actual) {
      org.junit.Assert.assertEquals(expected, actual);
    }
  }


  @Test
  public void testNormalOperation() {
    try {
      rethrowToDoubleBiFunction((v1, v2) -> {
        if(false) throw new IOException();
        return 0;
      }).applyAsDouble(null, null);
    } catch (IOException ignored) {
      org.junit.Assert.fail("");
    }
  }


}
