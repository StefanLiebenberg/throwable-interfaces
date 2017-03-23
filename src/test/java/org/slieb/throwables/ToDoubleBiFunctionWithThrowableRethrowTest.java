package org.slieb.throwables;
import org.junit.Test;
import java.io.IOException;
import static org.slieb.throwables.ToDoubleBiFunctionWithThrowable.aToDoubleBiFunctionThatUnSafelyThrowsUncheckedThrowable;
@java.lang.SuppressWarnings({"CodeBlock2Expr"})
public class ToDoubleBiFunctionWithThrowableRethrowTest {


  @Test
  public void testThrowCheckedException() {
    IOException expected = new IOException("EXPECTED ERROR");
    IOException actual = null;
    try {
      aToDoubleBiFunctionThatUnSafelyThrowsUncheckedThrowable((v1, v2) -> {
        throw expected;
      }).applyAsDouble(null, null);
      org.junit.Assert.fail("Exception should have been thrown");
    } catch (IOException e) {
      actual=e;
    }
    org.junit.Assert.assertEquals(expected, actual);
  }


  @Test
  public void testNormalOperation() {
    try {
      aToDoubleBiFunctionThatUnSafelyThrowsUncheckedThrowable((v1, v2) -> {
        if(false) throw new IOException();
        return 0;
      }).applyAsDouble(null, null);
    } catch (IOException ignored) {
      org.junit.Assert.fail("");
    }
  }


}
