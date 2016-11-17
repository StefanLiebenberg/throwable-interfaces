package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.DoubleToLongFunctionWithThrowable.castDoubleToLongFunctionWithThrowable;

@java.lang.SuppressWarnings({"WeakerAccess", "deprecation"})
public class DoubleToLongFunctionWithThrowableLogableTest {

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
            castDoubleToLongFunctionWithThrowable((v1) -> {
                throw expected;
            }).withLogging().applyAsLong(0);
        } catch (Exception ignored) {}
        org.junit.Assert.assertEquals(expected, tHandler.getLastRecord().getThrown());
    }

    @Test
    public void testNormalOperation() {
        castDoubleToLongFunctionWithThrowable((v1) -> {
            return 0;
        }).withLogging().applyAsLong(0);
    }
}
