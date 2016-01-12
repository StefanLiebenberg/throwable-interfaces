package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.IntBinaryOperatorWithThrowable.castIntBinaryOperatorWithThrowable;
public class IntBinaryOperatorWithThrowableLogableTest {



    private ThrownHandler tHandler;
    private java.util.logging.Logger globalLogger;



    @org.junit.Before
    public void setup() {
        tHandler = new ThrownHandler();
        globalLogger = java.util.logging.Logger.getGlobal();
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
    castIntBinaryOperatorWithThrowable((v1, v2) -> {
      throw expected;
    }).withLogging().applyAsInt(0, 0);
        } catch (Exception ignored) {}
        org.junit.Assert.assertEquals(expected, tHandler.getLastRecord().getThrown());
 }

 @Test
 public void testNormalOperation() {
    castIntBinaryOperatorWithThrowable((v1, v2) -> {
 return 0;
    }).withLogging().applyAsInt(0, 0);
 }

}
