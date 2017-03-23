package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.DoubleSupplierWithThrowable.castDoubleSupplierWithThrowable;
@java.lang.SuppressWarnings({"CodeBlock2Expr"})
public class DoubleSupplierWithThrowableLogableTest {

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
      castDoubleSupplierWithThrowable(() -> {
        throw expected;
      }).withLogging().getAsDouble();
    } catch (Exception ignored) {}
    org.junit.Assert.assertEquals(expected, tHandler.getLastRecord().getThrown());
  }


  @Test
  public void testNormalOperation() {
    castDoubleSupplierWithThrowable(() -> {
      return 0;
    }).withLogging().getAsDouble();
  }


}
