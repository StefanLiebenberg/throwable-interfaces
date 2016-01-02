package org.slieb.throwables;


import org.junit.Test;

@SuppressWarnings("unused")
public interface FunctionInterfaceTestInterface {

    @Test(expected = SuppressedException.class)
    void testThrowCheckedException();

    @Test(expected = RuntimeException.class)
    void testThrowRuntimeException();

    @Test(expected = Error.class)
    void testThrowError();

    @Test(expected = Throwable.class)
    void testThrowThrowable();

    @Test
    void testNormalOperation();

    @Test
    void testAnnotatedWithFunctionalInterface();

    
}
