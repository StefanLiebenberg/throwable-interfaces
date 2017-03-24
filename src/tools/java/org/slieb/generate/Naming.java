package org.slieb.generate;

import com.sun.xml.internal.ws.util.StringUtils;

@SuppressWarnings("WeakerAccess")
public class Naming {

    /**
     * @return The instance method on a functionalInterface that will do some black magic to rethrow checked exception as unchecked.
     */
    public static String methodThatUnsafelyThrowsUnchecked() {
        return "thatUnsafelyThrowsUnchecked";
    }

    /**
     * @param node The functional interface node
     * @return The static method on a functional interface that will do some black magic to create a function that will rethrow checked exceptions as unchecked.
     */
    public static String methodThatUnsafelyThrowsUnchecked(final FunctionalInterfaceNode node) {
        return String.format("a%s%s", node.getImplementationClass().getSimpleName(), StringUtils.capitalize(methodThatUnsafelyThrowsUnchecked()));
    }
}
