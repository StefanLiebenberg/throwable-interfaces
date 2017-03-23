package org.slieb.throwables;
import org.junit.Test;
import java.io.IOException;
import static org.slieb.throwables.ToDoubleFunctionWithThrowable.rethrowToDoubleFunction;
@java.lang.SuppressWarnings({"CodeBlock2Expr"})
public class ToDoubleFunctionWithThrowableRethrowTest {


  @Test
  public void testThrowCheckedException() {
    IOException expected = new IOException("EXPECTED ERROR");
    try {
      rethrowToDoubleFunction((v1) -> {
        throw expected;
      }).applyAsDouble(null);
    } catch (IOException actual) {
      org.junit.Assert.assertEquals(expected, actual);
    }
  }


  @Test
  public void testNormalOperation() {
    try {
      rethrowToDoubleFunction((v1) -> {
        if(false) throw new IOException();
        return 0;
      }).applyAsDouble(null);
    } catch (IOException ignored) {
      org.junit.Assert.fail("");
    }
  }


}
