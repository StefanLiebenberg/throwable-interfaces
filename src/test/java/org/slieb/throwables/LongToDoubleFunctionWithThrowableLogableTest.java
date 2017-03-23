package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.LongToDoubleFunctionWithThrowable.castLongToDoubleFunctionWithThrowable;

@java.lang.SuppressWarnings({"CodeBlock2Expr"})
public class LongToDoubleFunctionWithThrowableLogableTest {

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
      castLongToDoubleFunctionWithThrowable((v1) -> {
        throw expected;
      }).withLogging().applyAsDouble(0);
    } catch (Exception ignored) {}
    org.junit.Assert.assertEquals(expected, tHandler.getLastRecord().getThrown());
  }


  @Test
  public void testNormalOperation() {
    castLongToDoubleFunctionWithThrowable((v1) -> {
      return 0;
    }).withLogging().applyAsDouble(0);
  }


}
