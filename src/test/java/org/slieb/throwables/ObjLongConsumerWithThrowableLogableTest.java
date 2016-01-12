package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.ObjLongConsumerWithThrowable.castObjLongConsumerWithThrowable;
public class ObjLongConsumerWithThrowableLogableTest {



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
    castObjLongConsumerWithThrowable((v1, v2) -> {
      throw expected;
    }).withLogging().accept(null, 0);
        } catch (Exception ignored) {}
        org.junit.Assert.assertEquals(expected, tHandler.getLastRecord().getThrown());
 }

 @Test
 public void testNormalOperation() {
    castObjLongConsumerWithThrowable((v1, v2) -> {
    }).withLogging().accept(null, 0);
 }

}
