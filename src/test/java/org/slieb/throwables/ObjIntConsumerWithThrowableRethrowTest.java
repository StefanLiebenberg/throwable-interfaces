package org.slieb.throwables;
import org.junit.Test;
import java.io.IOException;
import static org.slieb.throwables.ObjIntConsumerWithThrowable.rethrowObjIntConsumer;
@java.lang.SuppressWarnings({"CodeBlock2Expr"})
public class ObjIntConsumerWithThrowableRethrowTest {


  @Test
  public void testThrowCheckedException() {
    IOException expected = new IOException("EXPECTED ERROR");
    try {
      rethrowObjIntConsumer((v1, v2) -> {
        throw expected;
      }).accept(null, 0);
    } catch (IOException actual) {
      org.junit.Assert.assertEquals(expected, actual);
    }
  }


  @Test
  public void testNormalOperation() {
    try {
      rethrowObjIntConsumer((v1, v2) -> {
        if(false) throw new IOException();
      }).accept(null, 0);
    } catch (IOException ignored) {
      org.junit.Assert.fail("");
    }
  }


}
