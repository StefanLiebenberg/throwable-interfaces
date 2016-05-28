package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.UnaryOperatorWithThrowable.castUnaryOperatorWithThrowable;
@java.lang.SuppressWarnings({"WeakerAccess", "deprecation"})
public class UnaryOperatorWithThrowableLogableTest {

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
      castUnaryOperatorWithThrowable((v1) -> {
        throw expected;
      }).withLogging().apply(null);
    } catch (Exception ignored) {}
    org.junit.Assert.assertEquals(expected, tHandler.getLastRecord().getThrown());
  }


  @Test
  public void testNormalOperation() {
    castUnaryOperatorWithThrowable((v1) -> {
      return null;
    }).withLogging().apply(null);
  }


}
