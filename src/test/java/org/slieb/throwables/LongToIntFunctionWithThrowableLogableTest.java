package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.LongToIntFunctionWithThrowable.castLongToIntFunctionWithThrowable;
public class LongToIntFunctionWithThrowableLogableTest {



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
    castLongToIntFunctionWithThrowable((v1) -> {
      throw expected;
    }).withLogging().applyAsInt(0);
        } catch (Exception ignored) {}
        org.junit.Assert.assertEquals(expected, tHandler.getLastRecord().getThrown());
 }

 @Test
 public void testNormalOperation() {
    castLongToIntFunctionWithThrowable((v1) -> {
 return 0;
    }).withLogging().applyAsInt(0);
 }

}
