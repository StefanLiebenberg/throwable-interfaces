package org.slieb.throwables;
import org.junit.Test;
import java.io.IOException;
import static org.slieb.throwables.ObjLongConsumerWithThrowable.rethrowObjLongConsumer;
@java.lang.SuppressWarnings({"CodeBlock2Expr"})
public class ObjLongConsumerWithThrowableRethrowTest {


  @Test
  public void testThrowCheckedException() {
    IOException expected = new IOException("EXPECTED ERROR");
    try {
      rethrowObjLongConsumer((v1, v2) -> {
        throw expected;
      }).accept(null, 0);
    } catch (IOException actual) {
      org.junit.Assert.assertEquals(expected, actual);
    }
  }


  @Test
  public void testNormalOperation() {
    try {
      rethrowObjLongConsumer((v1, v2) -> {
        if(false) throw new IOException();
      }).accept(null, 0);
    } catch (IOException ignored) {
      org.junit.Assert.fail("");
    }
  }


}
