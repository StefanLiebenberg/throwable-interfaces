package org.slieb.generate;

import com.sun.xml.internal.ws.util.StringUtils;

public class Naming {

    public static String methodThatUnsafelyThrowsUnchecked() {
        return "thatUnSafelyThrowsUncheckedThrowable";
    }

    public static String methodThatUnsafelyThrowsUnchecked(Class classobj) {
        return String.format("a%s%s", classobj.getSimpleName(), StringUtils.capitalize(methodThatUnsafelyThrowsUnchecked()));
    }
}
