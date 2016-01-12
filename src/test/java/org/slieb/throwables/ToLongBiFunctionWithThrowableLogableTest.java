package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.ToLongBiFunctionWithThrowable.castToLongBiFunctionWithThrowable;
public class ToLongBiFunctionWithThrowableLogableTest {



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
    castToLongBiFunctionWithThrowable((v1, v2) -> {
      throw expected;
    }).withLogging().applyAsLong(null, null);
        } catch (Exception ignored) {}
        org.junit.Assert.assertEquals(expected, tHandler.getLastRecord().getThrown());
 }

 @Test
 public void testNormalOperation() {
    castToLongBiFunctionWithThrowable((v1, v2) -> {
 return 0;
    }).withLogging().applyAsLong(null, null);
 }

}
