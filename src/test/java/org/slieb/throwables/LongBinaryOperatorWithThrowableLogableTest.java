package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.LongBinaryOperatorWithThrowable.castLongBinaryOperatorWithThrowable;
public class LongBinaryOperatorWithThrowableLogableTest {



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
    castLongBinaryOperatorWithThrowable((v1, v2) -> {
      throw expected;
    }).withLogging().applyAsLong(0, 0);
        } catch (Exception ignored) {}
        org.junit.Assert.assertEquals(expected, tHandler.getLastRecord().getThrown());
 }

 @Test
 public void testNormalOperation() {
    castLongBinaryOperatorWithThrowable((v1, v2) -> {
 return 0;
    }).withLogging().applyAsLong(0, 0);
 }

}
