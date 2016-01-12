package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.DoublePredicateWithThrowable.castDoublePredicateWithThrowable;
public class DoublePredicateWithThrowableLogableTest {



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
    castDoublePredicateWithThrowable((v1) -> {
      throw expected;
    }).withLogging().test(0);
        } catch (Exception ignored) {}
        org.junit.Assert.assertEquals(expected, tHandler.getLastRecord().getThrown());
 }

 @Test
 public void testNormalOperation() {
    castDoublePredicateWithThrowable((v1) -> {
 return false;
    }).withLogging().test(0);
 }

}
