package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.ConsumerWithThrowable.castConsumerWithThrowable;

@java.lang.SuppressWarnings({"WeakerAccess", "deprecation"})
public class ConsumerWithThrowableLogableTest {

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
            castConsumerWithThrowable((v1) -> {
                throw expected;
            }).withLogging().accept(null);
        } catch (Exception ignored) {}
        org.junit.Assert.assertEquals(expected, tHandler.getLastRecord().getThrown());
    }

    @Test
    public void testNormalOperation() {
        castConsumerWithThrowable((v1) -> {}).withLogging().accept(null);
    }
}
