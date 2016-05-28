package org.slieb.generate;

public class FunctionalInterfaceNode {

    private final Class<?> implementationClass;

    private final boolean isDeprecated;

    public FunctionalInterfaceNode(final Class<?> funcInterface, final boolean isDeprecated) {
        this.implementationClass = funcInterface;
        this.isDeprecated = isDeprecated;
    }

    public boolean isDeprecated() {
        return isDeprecated;
    }

    public Class<?> getImplementationClass() {
        return implementationClass;
    }
}
