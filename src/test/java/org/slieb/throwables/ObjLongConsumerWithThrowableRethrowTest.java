package org.slieb.throwables;
import org.junit.Test;
import java.io.IOException;
import static org.slieb.throwables.ObjLongConsumerWithThrowable.aObjLongConsumerThatUnSafelyThrowsUncheckedThrowable;
@java.lang.SuppressWarnings({"CodeBlock2Expr"})
public class ObjLongConsumerWithThrowableRethrowTest {


  @Test
  public void testThrowCheckedException() {
    IOException expected = new IOException("EXPECTED ERROR");
    IOException actual = null;
    try {
      aObjLongConsumerThatUnSafelyThrowsUncheckedThrowable((v1, v2) -> {
        throw expected;
      }).accept(null, 0);
      org.junit.Assert.fail("Exception should have been thrown");
    } catch (IOException e) {
      actual=e;
    }
    org.junit.Assert.assertEquals(expected, actual);
  }


  @Test
  public void testNormalOperation() {
    try {
      aObjLongConsumerThatUnSafelyThrowsUncheckedThrowable((v1, v2) -> {
        if(false) throw new IOException();
      }).accept(null, 0);
    } catch (IOException ignored) {
      org.junit.Assert.fail("");
    }
  }


}
