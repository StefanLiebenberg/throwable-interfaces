package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.ToIntFunctionWithThrowable.castToIntFunctionWithThrowable;
@java.lang.SuppressWarnings({"CodeBlock2Expr"})
public class ToIntFunctionWithThrowableLogableTest {

  private ThrownHandler tHandler;

  private java.util.logging.Logger globalLogger;


  @org.junit.Before
  public void setup() {
    tHandler = new ThrownHandler();
    globalLogger = java.util.logging.Logger.getLogger("");
    globalLogger.addHandler(tHandler);
  }


  @org.junit.After
  public void teardown() {
    globalLogger.removeHandler(tHandler);
  }


  @Test
  public void testThrowCheckedException() {
    Exception expected = new Exception("EXPECTED ERROR");
    try {
      castToIntFunctionWithThrowable((v1) -> {
        throw expected;
      }).withLogging().applyAsInt(null);
    } catch (Exception ignored) {}
    org.junit.Assert.assertEquals(expected, tHandler.getLastRecord().getThrown());
  }


  @Test
  public void testNormalOperation() {
    castToIntFunctionWithThrowable((v1) -> {
      return 0;
    }).withLogging().applyAsInt(null);
  }


}
