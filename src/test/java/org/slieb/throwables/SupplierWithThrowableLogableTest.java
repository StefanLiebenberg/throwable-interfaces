package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.SupplierWithThrowable.castSupplierWithThrowable;

@java.lang.SuppressWarnings({"CodeBlock2Expr"})
public class SupplierWithThrowableLogableTest {

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
            castSupplierWithThrowable(() -> {
                throw expected;
            }).withLogging().get();
        } catch (Exception ignored) {}
        org.junit.Assert.assertEquals(expected, tHandler.getLastRecord().getThrown());
    }

    @Test
    public void testNormalOperation() {
        castSupplierWithThrowable(() -> {
            return null;
        }).withLogging().get();
    }
}
