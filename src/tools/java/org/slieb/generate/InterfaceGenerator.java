package org.slieb.generate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slieb.throwables.SuppressedException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.slieb.generate.TypeResolver.getBasicTypeFor;
import static org.slieb.generate.TypeResolver.getFunctionalMethod;
import static org.slieb.generate.TypeResolver.getNullTypeFor;

/**
 * This class generates a bunch of interfaces based on some crazy reflecting of the utilities in java.util.function.*
 */
public class InterfaceGenerator {

    private final String packageName;

    private final File directory, testDirectory;

    private InterfaceGenerator(String packageName, File directory, File testDirectory) {
        this.packageName = packageName;
        this.directory = directory;
        this.testDirectory = testDirectory;
    }

    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws IOException {

        InterfaceGenerator interfaceGenerator = new InterfaceGenerator("org.slieb.throwables", new File("src/main/java"), new File("src/test/java"));
        interfaceGenerator.generate(java.util.function.BiConsumer.class);
        interfaceGenerator.generate(java.util.function.BiFunction.class);
        interfaceGenerator.generate(java.util.function.BinaryOperator.class);
        interfaceGenerator.generate(java.util.function.BooleanSupplier.class);
        interfaceGenerator.generate(java.util.function.Consumer.class);
        interfaceGenerator.generate(java.util.function.DoubleBinaryOperator.class);
        interfaceGenerator.generate(java.util.function.DoubleConsumer.class);
        interfaceGenerator.generate(java.util.function.DoubleFunction.class);
        interfaceGenerator.generate(java.util.function.DoublePredicate.class);
        interfaceGenerator.generate(java.util.function.DoubleSupplier.class);
        interfaceGenerator.generate(java.util.function.DoubleToIntFunction.class);
        interfaceGenerator.generate(java.util.function.DoubleToLongFunction.class);
        interfaceGenerator.generate(java.util.function.DoubleUnaryOperator.class);
        interfaceGenerator.generate(java.util.function.Function.class);
        interfaceGenerator.generate(java.util.function.IntBinaryOperator.class);
        interfaceGenerator.generate(java.util.function.IntConsumer.class);
        interfaceGenerator.generate(java.util.function.IntPredicate.class);
        interfaceGenerator.generate(java.util.function.IntSupplier.class);
        interfaceGenerator.generate(java.util.function.IntFunction.class);
        interfaceGenerator.generate(java.util.function.LongBinaryOperator.class);
        interfaceGenerator.generate(java.util.function.LongConsumer.class);
        interfaceGenerator.generate(java.util.function.LongFunction.class);
        interfaceGenerator.generate(java.util.function.LongPredicate.class);
        interfaceGenerator.generate(java.util.function.LongSupplier.class);
        interfaceGenerator.generate(java.util.function.LongToDoubleFunction.class);
        interfaceGenerator.generate(java.util.function.LongToIntFunction.class);
        interfaceGenerator.generate(java.util.function.LongUnaryOperator.class);
        interfaceGenerator.generate(java.util.function.ObjDoubleConsumer.class);
        interfaceGenerator.generate(java.util.function.ObjIntConsumer.class);
        interfaceGenerator.generate(java.util.function.ObjLongConsumer.class);
        interfaceGenerator.generate(java.util.function.Predicate.class);
        interfaceGenerator.generate(java.util.function.Supplier.class);
        interfaceGenerator.generate(java.util.function.ToDoubleBiFunction.class);
        interfaceGenerator.generate(java.util.function.ToDoubleFunction.class);
        interfaceGenerator.generate(java.util.function.ToIntBiFunction.class);
        interfaceGenerator.generate(java.util.function.ToIntFunction.class);
        interfaceGenerator.generate(java.util.function.ToLongBiFunction.class);
        interfaceGenerator.generate(java.util.function.ToLongFunction.class);
        interfaceGenerator.generate(UnaryOperator.class);
        interfaceGenerator.generate(Runnable.class);
    }

    private void generate(Class<?> funcInterface) {
        try {
            FunctionalInterfaceNode node = createNode(funcInterface);
            String className = funcInterface.getSimpleName() + "WithThrowable";
            generateImplementation(className, node);
            generateCastTest(className, className + "CastTest", node);
            generateConvertTest(funcInterface, className, className + "ConvertTest");
            generateIgnoresTest(funcInterface, className, className + "IgnoresTest");
            generateLogableTest(className, className + "LogableTest", node);
            generateGeneralTest(funcInterface, className, className + "GeneralTest");
            generateUnwrapTest(funcInterface, className, className + "UnwrapTest");
            generateRethrowTest(className, className + "RethrowTest", node);
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
    }

    private FunctionalInterfaceNode createNode(final Class<?> funcInterface) {
        boolean isDeprecated = funcInterface.isAnnotationPresent(Deprecated.class);
        return new FunctionalInterfaceNode(funcInterface, isDeprecated);
    }

    private void generateImplementation(String className, final FunctionalInterfaceNode node) throws IOException {
        generateFile(directory, packageName, className, getImplementationContent(className, node));
    }

    private void generateCastTest(String className, String testName, FunctionalInterfaceNode node) throws IOException {
        generateFile(testDirectory, packageName, testName, getCastTestContent(className, testName, node));
    }

    private void generateConvertTest(Class<?> funcInterface, String className, String testName) throws IOException {
        generateFile(testDirectory, packageName, testName, getConvertTestContent(funcInterface, className, testName));
    }

    private void generateIgnoresTest(Class<?> funcInterface, String className, String testName) throws IOException {
        generateFile(testDirectory, packageName, testName, getIgnoresTestContent(funcInterface, className, testName));
    }

    private void generateLogableTest(String className, String testName, FunctionalInterfaceNode node) throws IOException {
        generateFile(testDirectory, packageName, testName, getLoggableTestContent(className, testName, node));
    }

    private void generateGeneralTest(Class<?> funcInterface, String className, String testName) throws IOException {
        generateFile(testDirectory, packageName, testName, getGeneralTestContent(funcInterface, className, testName));
    }

    private void generateUnwrapTest(Class<?> funcInterface, String className, String testName) throws IOException {
        generateFile(testDirectory, packageName, testName, getUnwrapTestContent(funcInterface, className, testName));
    }

    private void generateRethrowTest(String className, String testName, FunctionalInterfaceNode node) throws IOException {
        generateFile(testDirectory, packageName, testName, getRethrowTestContent(className, testName, node));
    }

    private void generateFile(File directory, String pkg, String fileName, String content) throws IOException {
        File outputFile = getOutputFile(directory, pkg, fileName);
        if (outputFile.getParentFile().exists() || outputFile.getParentFile().mkdirs()) {
            try (FileWriter writer = new FileWriter(outputFile)) {
                writer.write(content);
            }
        }
    }

    private File getOutputFile(File directory, String pkg, String className) {
        return new File(new File(directory, pkg.replaceAll("\\.", "/")), className + ".java");
    }

    private String getCastTestContent(String className, String testName, FunctionalInterfaceNode node) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("package ").append(packageName).append(";\n");
        stringBuilder.append("import org.junit.Test;\n");

        stringBuilder.append("import static ").append(packageName).append(".").append(className).append(".cast").append(className).append(";\n");

        stringBuilder.append("public class ").append(testName);
        stringBuilder.append(" {\n");

        Method method = getFunctionalMethod(node.getImplementationClass());
        String params = getMethodParams(node.getImplementationClass(), method, false);

        stringBuilder.append(" @Test(expected = SuppressedException.class)\n");
        stringBuilder.append(" public void testThrowCheckedException() {\n");

        stringBuilder.append("    cast").append(className).append("(").append(params).append(" -> {\n");
        stringBuilder.append("      throw new Exception(\"expected error\");\n");
        stringBuilder.append("    }).").append(getMethodCall(node.getImplementationClass(), method)).append(";\n");
        stringBuilder.append(" }\n\n");

        stringBuilder.append(" @Test(expected = RuntimeException.class)\n");
        stringBuilder.append(" public void testThrowRuntimeException() {\n");

        stringBuilder.append("    cast").append(className).append("(").append(params).append(" -> {\n");
        stringBuilder.append("      throw new RuntimeException(\"expected error\");\n");
        stringBuilder.append("    }).").append(getMethodCall(node.getImplementationClass(), method)).append(";\n");
        stringBuilder.append(" }\n\n");

        stringBuilder.append(" @Test(expected = Error.class)\n");
        stringBuilder.append(" public void testThrowError() {\n");

        stringBuilder.append("    cast").append(className).append("(").append(params).append(" -> {\n");
        stringBuilder.append("      throw new Error(\"expected error\");\n");
        stringBuilder.append("    }).").append(getMethodCall(node.getImplementationClass(), method)).append(";\n");
        stringBuilder.append(" }\n\n");

        stringBuilder.append(" @Test(expected = Throwable.class)\n");
        stringBuilder.append(" public void testThrowThrowable() {\n");

        stringBuilder.append("    cast").append(className).append("(").append(params).append(" -> {\n");
        stringBuilder.append("       throw new Throwable(\"expected throwable\");\n");
        stringBuilder.append("    }).").append(getMethodCall(node.getImplementationClass(), method)).append(";\n");
        stringBuilder.append(" }\n\n");

        stringBuilder.append(" @Test\n");
        stringBuilder.append(" public void testAnnotatedWithFunctionalInterface() {\n");
        stringBuilder.append("    ").append(className).append(".class.isAnnotationPresent(FunctionalInterface.class);\n");
        stringBuilder.append(" }\n\n");

        stringBuilder.append(" @Test\n");
        stringBuilder.append(" public void testNormalOperation() {\n");
        stringBuilder.append("    cast").append(className).append("(").append(params).append(" -> {\n");

        final Class<?> returnType = method.getReturnType();
        if (!returnType.equals(Void.TYPE)) {
            stringBuilder.append(" return ").append(TypeResolver.getNullTypeFor(returnType)).append(";\n");
        }
        stringBuilder.append("    }).").append(getMethodCall(node.getImplementationClass(), method)).append(";\n");
        stringBuilder.append(" }\n\n");

        stringBuilder.append("}\n");

        return stringBuilder.toString();
    }

    private String getConvertTestContent(Class<?> funcInterface, String className, String testName) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("package ").append(packageName).append(";\n");
        stringBuilder.append("import org.junit.Test;\n");

        stringBuilder.append("import static ").append(packageName).append(".").append(className).append(".as").append(className).append(";\n");

        stringBuilder.append("public class ").append(testName);
        stringBuilder.append(" {\n");

        Method method = getFunctionalMethod(funcInterface);
        String params = getMethodParams(funcInterface, method, false);

        stringBuilder.append(" @Test(expected = RuntimeException.class)\n");
        stringBuilder.append(" public void testThrowRuntimeException() {\n");

        stringBuilder.append("    as").append(className).append("(").append(params).append(" -> {\n");
        stringBuilder.append("      throw new RuntimeException(\"expected error\");\n");
        stringBuilder.append("    }).").append(getMethodCall(funcInterface, method)).append(";\n");
        stringBuilder.append(" }\n\n");

        stringBuilder.append(" @Test(expected = Error.class)\n");
        stringBuilder.append(" public void testThrowError() {\n");

        stringBuilder.append("    as").append(className).append("(").append(params).append(" -> {\n");
        stringBuilder.append("      throw new Error(\"expected error\");\n");
        stringBuilder.append("    }).").append(getMethodCall(funcInterface, method)).append(";\n");
        stringBuilder.append(" }\n\n");

        stringBuilder.append(" @Test\n");
        stringBuilder.append(" public void testAnnotatedWithFunctionalInterface() {\n");
        stringBuilder.append("    ").append(className).append(".class.isAnnotationPresent(FunctionalInterface.class);\n");
        stringBuilder.append(" }\n\n");

        stringBuilder.append(" @Test\n");
        stringBuilder.append(" public void testNormalOperation() {\n");
        stringBuilder.append("    as").append(className).append("(").append(params).append(" -> {\n");

        final Class<?> returnType = method.getReturnType();
        if (!returnType.equals(Void.TYPE)) {
            stringBuilder.append(" return ").append(TypeResolver.getNullTypeFor(returnType)).append(";\n");
        }
        stringBuilder.append("    }).").append(getMethodCall(funcInterface, method)).append(";\n");
        stringBuilder.append(" }\n\n");

        stringBuilder.append("}\n");

        return stringBuilder.toString();
    }

    private String getIgnoresTestContent(Class<?> funcInterface, String className, String testName) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("package ").append(packageName).append(";\n");
        stringBuilder.append("import org.junit.Test;\n");

        stringBuilder.append("import static ").append(packageName).append(".").append(className).append(".cast").append(className).append(";\n");

        stringBuilder.append("public class ").append(testName);
        stringBuilder.append(" {\n");

        Method method = getFunctionalMethod(funcInterface);
        String params = getMethodParams(funcInterface, method, false);

        Type returnType = TypeResolver.resolveType(funcInterface, method.getGenericReturnType());
        boolean hasReturnType = !returnType.equals(Void.TYPE);

        if (!hasReturnType) {

            stringBuilder.append(" @Test\n");
            stringBuilder.append(" public void testThrowExceptionWithNoIgnores() {\n");

            stringBuilder.append("    cast").append(className).append("(").append(params).append(" -> {\n");
            stringBuilder.append("      throw new Exception(\"expected error\");\n");
            stringBuilder.append("    }).thatThrowsNothing().").append(getMethodCall(funcInterface, method)).append(";\n");
            stringBuilder.append(" }\n\n");
        }

        stringBuilder.append("}\n");

        return stringBuilder.toString();
    }

    private String getLoggableTestContent(String className, String testName, FunctionalInterfaceNode node) {
        final IndentStringBuilder isb = new IndentStringBuilder();

        isb.append("package ").append(packageName).append(";\n");
        isb.append("import org.junit.Test;\n");

        isb.append("import static ").append(packageName).append(".").append(className).append(".cast").append(className).append(";\n");

        isb.newline();
        //        isb.annotate(SuppressWarnings.class, "{\"WeakerAccess\", \"deprecation\"}");
        isb.annotate(SuppressWarnings.class, "{\"CodeBlock2Expr\"}");
        isb.indent().append("public class ").append(testName).append(" {").newline();
        isb.incrementIndent();

        isb.newlines(1);
        isb.indent().append("private ThrownHandler tHandler;").newline();
        isb.newlines(1);
        isb.indent().append("private java.util.logging.Logger globalLogger;").newline();

        Method method = getFunctionalMethod(node.getImplementationClass());
        String params = getMethodParams(node.getImplementationClass(), method, false);

        isb.newlines(2);
        isb.indent().append("@org.junit.Before").newline();
        isb.indent().append("public void setup() {").newline();
        isb.incrementIndent();
        isb.indent().append("tHandler = new ThrownHandler();").newline();
        isb.indent().append("globalLogger = java.util.logging.Logger.getLogger(\"\");").newline();
        isb.indent().append("globalLogger.addHandler(tHandler);").newline();
        isb.decrementIndent();
        isb.indent().append("}").newline();

        isb.newlines(2);
        isb.indent().append("@org.junit.After").newline();
        isb.indent().append("public void teardown() {").newline();
        isb.incrementIndent();
        isb.indent().append("globalLogger.removeHandler(tHandler);").newline();
        isb.decrementIndent();
        isb.indent().append("}").newline();

        isb.newlines(2);
        isb.indent().append("@Test\n");
        isb.indent().append("public void testThrowCheckedException() {\n");
        isb.incrementIndent();
        isb.indent().append("Exception expected = new Exception(\"EXPECTED ERROR\");").newline();
        isb.indent().append("try {").newline();
        isb.incrementIndent();
        isb.indent().append("cast").append(className).append("(").append(params).append(" -> {\n");
        isb.incrementIndent();
        isb.indent().append("throw expected;\n");
        isb.decrementIndent();
        isb.indent().append("}).withLogging().").append(getMethodCall(node.getImplementationClass(), method)).append(";\n");
        isb.decrementIndent();
        isb.indent().append("} catch (Exception ignored) {}").newline();
        isb.indent().append("org.junit.Assert.assertEquals(expected, tHandler.getLastRecord().getThrown());").newline();
        isb.decrementIndent();
        isb.indent().append("}").newline();

        isb.newlines(2);
        isb.indent().append("@Test").newline();
        isb.indent().append("public void testNormalOperation() {\n");
        isb.incrementIndent();
        isb.indent().append("cast").append(className).append("(").append(params).append(" -> {");
        final Class<?> returnType = method.getReturnType();
        if (!returnType.equals(Void.TYPE)) {
            isb.newline().incrementIndent();
            isb.indent().append("return ").append(TypeResolver.getNullTypeFor(returnType)).append(";\n");
            isb.decrementIndent().indent();
        }
        isb.append("}).withLogging().").append(getMethodCall(node.getImplementationClass(), method)).append(";\n");
        isb.decrementIndent();
        isb.indent().append("}").newline();

        isb.newlines(2);
        isb.decrementIndent();
        isb.indent().append("}").newline();

        return isb.toString();
    }

    private String getGeneralTestContent(Class<?> funcInterface, String className, String testName) {
        IndentStringBuilder isb = new IndentStringBuilder();
        isb.appendPackage(packageName);

        isb.append("import org.junit.Test;\n");

        isb.appendImports(AtomicReference.class);

        isb.append("import static ").append(packageName).append(".").append(className).append(".cast").append(className).append(";\n");
        isb.append("import static org.junit.Assert.assertEquals;\n");

        isb.append("public class ").append(testName);
        isb.append(" {\n");

        Method method = getFunctionalMethod(funcInterface);
        String params = getMethodParams(funcInterface, method, false);

        Type returnType = TypeResolver.resolveType(funcInterface, method.getGenericReturnType());
        boolean hasReturnType = !returnType.equals(Void.TYPE);
        boolean returnTypeIsPrimitive = TypeResolver.isTypePrimitive(returnType);
        if (hasReturnType) {
            if (!returnTypeIsPrimitive) {

                isb.append(" @Test\n");
                isb.append(" public void testReturnTypeException() {\n");
                isb.indents(8).append("Object expected = new Object();").newline();
                isb.indents(8).append("Object result = cast").append(className).append("(").append(params).append(" -> {\n");
                isb.append("      throw new Exception(\"expect exception\");\n");
                isb.append("    }).thatReturnsOnCatch(expected).").append(getMethodCall(funcInterface, method)).append(";\n");
                isb.indents(8).append("assertEquals(expected, result);").newline();
                isb.append(" }\n\n");

                isb.append(" @Test\n");
                isb.append(" public void testNormalOperation() {\n");
                isb.append("    Object expected = ").append(getBasicTypeFor(returnType)).append(";").newline();
                isb.append("    Object result = cast")
                        .append(className)
                        .append("(")
                        .append(params)
                        .append(" -> expected).thatReturnsOnCatch(")
                        .append(getNullTypeFor(returnType))
                        .append(").")
                        .append(getMethodCall(funcInterface, method))
                        .append(";\n");
                isb.append("    assertEquals(expected, result);\n");
                isb.append(" }\n\n");
            } else {

                if (returnType.equals(Boolean.TYPE)) {
                    isb.append(" @Test\n");
                    isb.append(" public void testReturnTypeExceptionWithTrue() {\n");
                    isb.indents(8).append("boolean expected = true;").newline();
                    isb.indents(8).append("boolean result = cast").append(className).append("(").append(params).append(" -> {\n");
                    isb.append("      throw new Exception(\"expect exception\");\n");
                    isb.append("    }).thatReturnsOnCatch(expected).").append(getMethodCall(funcInterface, method)).append(";\n");
                    isb.indents(8).append("assertEquals(expected, result);").newline();
                    isb.append(" }\n\n");

                    isb.append(" @Test\n");
                    isb.append(" public void testReturnTypeExceptionWithFalse() {\n");
                    isb.indents(8).append("boolean expected = false;").newline();
                    isb.indents(8).append("boolean result = cast").append(className).append("(").append(params).append(" -> {\n");
                    isb.append("      throw new Exception(\"expect exception\");\n");
                    isb.append("    }).thatReturnsOnCatch(expected).").append(getMethodCall(funcInterface, method)).append(";\n");
                    isb.indents(8).append("assertEquals(expected, result);").newline();
                    isb.append(" }\n\n");

                    isb.append(" @Test\n");
                    isb.append(" public void testNormalOperation() {\n");
                    isb.append("    boolean expected = true").append(";").newline();
                    isb.append("    boolean result = cast")
                            .append(className)
                            .append("(")
                            .append(params)
                            .append(" -> expected).thatReturnsOnCatch(false).")
                            .append(getMethodCall(funcInterface, method))
                            .append(";\n");
                    isb.append("    assertEquals(expected, result);\n");
                    isb.append(" }\n\n");
                }
            }
        }

        if (!hasReturnType) {
            isb.append(" @Test\n");
            isb.append(" public void testThrowsNothing() {\n");
            isb.indents(8).append("cast").append(className).append("(").append(params).append(" -> {\n");
            isb.append("      throw new Exception(\"expect exception\");\n");
            isb.append("    }).thatThrowsNothing().").append(getMethodCall(funcInterface, method)).append(";\n");
            isb.append(" }\n\n");

            isb.append(" @Test\n");
            isb.append(" public void testThrowsNothingNormalOperation() {\n");
            isb.append("    cast")
                    .append(className)
                    .append("(")
                    .append(params)
                    .append(" -> {}).thatThrowsNothing().")
                    .append(getMethodCall(funcInterface, method))
                    .append(";\n");
            isb.append(" }\n\n");
        }

        isb.append(" @Test\n");
        isb.append(" public void testOnException() {\n");
        isb.indents(8)
                .appendClass(AtomicReference.class)
                .append("<")
                .appendClass(Throwable.class)
                .append("> reference = new ")
                .appendClass(AtomicReference.class)
                .append("<>();")
                .newline();
        isb.indents(8).appendClass(Exception.class).append(" expected = new ").appendClass(Exception.class).append("(\"expected\");").newline();
        isb.indents(8).append("try {").newline();
        isb.indents(8).append("cast").append(className).append("(").append(params).append(" -> {\n");
        isb.append("      throw expected;\n");
        isb.append("    }).onException(reference::set).").append(getMethodCall(funcInterface, method)).append(";\n");
        isb.indents(8).append("} catch (Throwable ignored) {}").newline();
        isb.indents(8).append("assertEquals(expected, reference.get());").newline();

        isb.append(" }\n\n");

        isb.append("}\n");

        return isb.toString();
    }

    private String getUnwrapTestContent(Class<?> funcInterface, String className, String testName) {
        IndentStringBuilder isb = new IndentStringBuilder();
        isb.appendPackage(packageName);

        isb.append("import org.junit.Test;\n");

        isb.appendImports(AtomicReference.class);

        isb.append("import static ").append(packageName).append(".").append(className).append(".cast").append(className).append(";\n");
        isb.append("import static org.junit.Assert.assertEquals;\n");

        isb.append("public class ").append(testName);
        isb.append(" {\n");

        Method method = getFunctionalMethod(funcInterface);
        String params = getMethodParams(funcInterface, method, false);

        Type returnType = TypeResolver.resolveType(funcInterface, method.getGenericReturnType());
        boolean hasReturnType = !returnType.equals(Void.TYPE);
        //        boolean returnTypeIsPrimitive = TypeResolver.isTypePrimitive(returnType);

        isb.setIndent(4);
        isb.newlines(1);
        isb.indent().append("private class CustomException extends Exception {}").newline();

        if (hasReturnType) {
            isb.newlines(1);
            isb.setIndent(4);
            isb.indent().append("@Test(expected = CustomException.class)").newline();
            isb.indent().append("public void testUnwrap() throws CustomException {").newline();
            isb.setIndent(8);
            isb.indent().append("CustomException expected = new CustomException();").newline();
            isb.indent().append("SuppressedException.unwrapSuppressedException(() -> {").newline();
            isb.setIndent(12);
            isb.indent().append("return cast").append(className).append("(").append(params).append(" -> {").newline();
            isb.setIndent(16);
            isb.indent().append("throw expected;").newline();
            isb.setIndent(12);
            isb.indent().append("}).").append(getMethodCall(funcInterface, method)).append(";").newline();
            isb.setIndent(8);
            isb.indent().append("}, CustomException.class);").newline();
            isb.setIndent(4);
            isb.indent().append("}").newline();
        } else {
            isb.newlines(1);
            isb.setIndent(4);
            isb.indent().append("@Test(expected = CustomException.class)").newline();
            isb.indent().append("public void testUnwrap() throws CustomException {").newline();
            isb.setIndent(8);
            isb.indent().append("CustomException expected = new CustomException();").newline();
            isb.indent().append("SuppressedException.unwrapSuppressedException(() -> {").newline();
            isb.setIndent(12);
            isb.indent().append("cast").append(className).append("(").append(params).append(" -> {").newline();
            isb.setIndent(16);
            isb.indent().append("throw expected;").newline();
            isb.setIndent(12);
            isb.indent().append("}).").append(getMethodCall(funcInterface, method)).append(";").newline();
            isb.setIndent(8);
            isb.indent().append("}, CustomException.class);").newline();
            isb.setIndent(4);
            isb.indent().append("}").newline();
        }

        isb.append("}\n");

        return isb.toString();
    }

    private String getRethrowTestContent(String className, String testName, FunctionalInterfaceNode node) {
        final IndentStringBuilder isb = new IndentStringBuilder();

        isb.append("package ").append(packageName).append(";\n");
        isb.append("import org.junit.Test;\n");
        isb.appendImport(IOException.class);

        isb.append("import static ")
                .append(packageName)
                .append(".")
                .append(className)
                .append(".")
                .append(Naming.methodThatUnsafelyThrowsUnchecked(node.getImplementationClass()))
                .append(";\n");

        //        isb.annotate(SuppressWarnings.class, "{\"WeakerAccess\", \"deprecation\"}");
        isb.annotate(SuppressWarnings.class, "{\"CodeBlock2Expr\"}");
        isb.indent().append("public class ").append(testName).append(" {").newline();
        isb.incrementIndent();

        Method method = getFunctionalMethod(node.getImplementationClass());
        String params = getMethodParams(node.getImplementationClass(), method, false);

        isb.newlines(2);
        isb.indent().append("@Test\n");
        isb.indent().append("public void testThrowCheckedException() {\n");
        isb.incrementIndent();
        isb.indent().append("IOException expected = new IOException(\"EXPECTED ERROR\");").newline();
        isb.indent().append("IOException actual = null;").newline();
        isb.indent().append("try {").newline();
        isb.incrementIndent();
        isb.indent().append(Naming.methodThatUnsafelyThrowsUnchecked(node.getImplementationClass())).append("(").append(params).append(" -> {\n");
        isb.incrementIndent();
        isb.indent().append("throw expected;\n");
        isb.decrementIndent();
        isb.indent().append("}).").append(getMethodCall(node.getImplementationClass(), method)).append(";\n");
        isb.indent().append("org.junit.Assert.fail(\"Exception should have been thrown\");").newline();
        isb.decrementIndent();
        isb.indent().append("} catch (IOException e) {").newline();
        isb.incrementIndent();
        isb.indent().append("actual=e;").newline();
        isb.decrementIndent();
        isb.indent().append("}").newline();
        isb.indent().append("org.junit.Assert.assertEquals(expected, actual);").newline();
        isb.decrementIndent();
        isb.indent().append("}").newline();

        isb.newlines(2);
        isb.indent().append("@Test").newline();
        isb.indent().append("public void testNormalOperation() {\n");
        isb.incrementIndent();
        isb.indent().append("try {").newline();
        isb.incrementIndent();
        isb.indent().append(Naming.methodThatUnsafelyThrowsUnchecked(node.getImplementationClass())).append("(").append(params).append(" -> {\n");
        isb.incrementIndent();
        isb.indent().append("if(false) throw new IOException();").newline();
        final Class<?> returnType = method.getReturnType();
        if (!returnType.equals(Void.TYPE)) {
            isb.indent().append("return ").append(TypeResolver.getNullTypeFor(returnType)).append(";\n");
        }
        isb.decrementIndent();
        isb.indent().append("}).").append(getMethodCall(node.getImplementationClass(), method)).append(";\n");
        isb.decrementIndent();
        isb.indent().append("} catch (IOException ignored) {").newline();
        isb.incrementIndent();
        isb.indent().append("org.junit.Assert.fail(\"\");").newline();
        isb.decrementIndent();
        isb.indent().append("}").newline();
        isb.decrementIndent();
        isb.indent().append("}").newline();

        isb.newlines(2);
        isb.decrementIndent();
        isb.indent().append("}").newline();

        return isb.toString();
    }

    private String getMethodCall(Class funcClass, Method method) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(method.getName());
        stringBuilder.append("(");
        Iterator<Type> iterator = Arrays.stream(method.getGenericParameterTypes()).map(t -> TypeResolver.resolveType(funcClass, t)).iterator();
        while (iterator.hasNext()) {
            Type nxt = iterator.next();
            stringBuilder.append(TypeResolver.getNullTypeFor(nxt));
            if (iterator.hasNext()) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append(")");

        return stringBuilder.toString();
    }

    private String getImplementationContent(String className, FunctionalInterfaceNode node) {

        final List<String> generics = getGenerics(node.getImplementationClass());
        final IndentStringBuilder isb = new IndentStringBuilder();

        isb.appendPackage(packageName);
        isb.appendImports(LoggerFactory.class, Logger.class, Consumer.class, Throwable.class, node.getImplementationClass(), SuppressedException.class,
                          FunctionalInterface.class, SuppressWarnings.class);

        if (node.isDeprecated()) {
            isb.appendImport(Deprecated.class);
        }

        isb.append("/**").newline();
        isb.append(" * Generated from ").appendClass(node.getImplementationClass()).append("\n");
        isb.append(" * Extends ").append(node.getImplementationClass().getName()).append(" to allow for a checked exception.\n");
        isb.append(" *\n");
        generics.forEach(gen -> isb.append(" * @param <").append(gen).append("> some generic flag\n"));
        isb.append(" * @param <E> The extension\n");
        isb.append(" */\n");
        isb.annotate(FunctionalInterface.class);
        if (node.isDeprecated()) {
            isb.annotate(SuppressWarnings.class, "{\"WeakerAccess\", \"deprecation\"}");
        } else {
            isb.annotate(SuppressWarnings.class, "{\"WeakerAccess\"}");
        }
        if (node.isDeprecated()) {
            isb.annotate(Deprecated.class);
        }

        isb.append("public interface ").append(className);
        isb.append(generateGenerics(generics, true, true));

        isb.append(" extends ").appendClass(node.getImplementationClass());
        if (!generics.isEmpty()) {
            isb.append(generateGenerics(generics, false, false));
        }
        isb.append(" {").newline();

        isb.newlines(1);
        isb.setIndent(4);
        String objectName = className.toLowerCase();
        isb.append("    /**\n");
        isb.append("     * Utility method to mark lambdas of type ").append(className).append("\n");
        isb.append("     *\n");
        isb.append("     * @param ").append(objectName).append(" The interface instance\n");
        generics.forEach(gen -> isb.append("     * @param <")
                .append(gen)
                .append("> Generic that corresponds to the same generic on ")
                .appendClass(node.getImplementationClass())
                .append("  \n"));
        isb.append("     * @param <E> The type this interface is allowed to throw\n");
        isb.append("     * @return the cast interface\n");
        isb.append("     */\n");
        isb.append("    static ")
                .append(generateGenerics(generics, true, true))
                .append(" ")
                .append(className)
                .append(generateGenerics(generics, true, false))
                .append(" cast")
                .append(className)
                .append("(final ")
                .append(className)
                .append(generateGenerics(generics, true, false))
                .append(" ")
                .append(objectName)
                .append(") {\n")
                .append("        return ")
                .append(objectName)
                .append(";\n")
                .append("    }");
        isb.newlines(2);

        isb.append("    /**\n");
        isb.append("     * Utility method to unwrap lambdas of type ")
                .appendClass(node.getImplementationClass())
                .append(" and withUncheckedThrowable any Exception\n");
        isb.append("     *\n");
        isb.append("     * @param ").append(objectName).append(" The interface instance\n");
        generics.forEach(gen -> isb.append("     * @param <")
                .append(gen)
                .append("> Generic that corresponds to the same generic on ")
                .appendClass(node.getImplementationClass())
                .append("  \n"));
        isb.append("     * @param <E> The type this interface is allowed to throw\n");
        isb.append("     * @throws E the original Exception from ").append(objectName).newline();
        isb.append("     * @return the cast interface\n");
        isb.append("     */\n");
        isb.append("    static ").append(generateGenerics(generics, true, true)).append(" ").append(node.getImplementationClass().getSimpleName());
        if (!generics.isEmpty()) {
            isb.append(generateGenerics(generics, false, false));
        }
        isb.append(" ").append(Naming.methodThatUnsafelyThrowsUnchecked(node.getImplementationClass()))
                .append("(final ")
                .append(className)
                .append(generateGenerics(generics, true, false))
                .append(" ")
                .append(objectName)
                .append(") throws E {\n")
                .append("        return ")
                .append(objectName)
                .append(".")
                .append(Naming.methodThatUnsafelyThrowsUnchecked())
                .append("();\n")
                .append("    }");
        isb.newlines(2);

        Method method = getFunctionalMethod(node.getImplementationClass());
        String methodName = method.getName();

        String variableName = node.getImplementationClass().getSimpleName().toLowerCase();
        isb.append("    /**\n");
        isb.append("     * Utility method to convert ").append(className).append("\n");
        isb.append("     * @param ").append(variableName).append(" The interface instance\n");
        generics.forEach(gen -> isb.append("     * @param <")
                .append(gen)
                .append("> Generic that corresponds to the same generic on ")
                .append(node.getImplementationClass().getSimpleName())
                .append("  \n"));
        isb.append("     * @param <E> The type this interface is allowed to throw\n");
        isb.append("     * @return the cast interface\n");
        isb.append("     */\n");
        isb.append("    static ")
                .append(generateGenerics(generics, true, true))
                .append(" ")
                .append(className)
                .append(generateGenerics(generics, true, false))
                .append(" as")
                .append(className)
                .append("(final ")
                .appendClass(node.getImplementationClass());
        if (!generics.isEmpty()) {
            isb.append(generateGenerics(generics, false, false));
        }

        isb.append(" ")
                .append(variableName)
                .append(") {\n")
                .append("        return ")
                .append(variableName)
                .append("::")
                .append(methodName)
                .append(";\n")
                .append("    }\n");

        Type returnType = TypeResolver.resolveType(node.getImplementationClass(), method.getGenericReturnType());
        boolean hasReturnType = !returnType.equals(Void.TYPE);
        String returnTypeName = hasReturnType ? returnType.getTypeName() : "void";
        isb.append("\n");

        isb.append("    /**\n");
        isb.append("     * Overridden method of ")
                .append(className)
                .append(" that will call ")
                .append(methodName)
                .append("WithThrowable, but catching any exceptions.\n");
        isb.append("     *\n");
        for (int i = 0; i < method.getGenericParameterTypes().length; i++) {
            isb.append("     * @param v").append(i + 1).append(" parameter to overridden method\n");
        }
        if (hasReturnType) {
            isb.append("     * @return the value\n");
        }
        isb.append("     */\n");
        isb.append("    @Override\n");
        isb.append("    default ").append(returnTypeName).append(" ");
        isb.append(methodName).append(getMethodParams(node.getImplementationClass(), method, true)).append(" {\n");
        isb.append("        try {\n");
        isb.append("            ");
        if (hasReturnType) {
            isb.append("return ");
        }
        isb.append(methodName).append("WithThrowable");
        isb.append(getMethodParams(node.getImplementationClass(), method, false)).append(";\n");
        isb.append("        } catch (final RuntimeException | Error exception) {\n");
        isb.append("            throw exception;\n");
        isb.append("        } catch (final Throwable throwable) {\n");
        isb.append("            throw new ").appendClass(SuppressedException.class).append("(throwable);\n");
        isb.append("        }\n");

        isb.append("    }\n");

        isb.append("\n");

        isb.append("    /**\n");
        isb.append("     * Functional method that will throw exceptions.\n");
        isb.append("     *\n");
        for (int i = 0; i < method.getGenericParameterTypes().length; i++) {
            isb.append("     * @param v").append(i + 1).append(" parameter to overridden method\n");
        }

        if (hasReturnType) {
            isb.append("     * @return the value\n");
        }
        isb.append("     * @throws E some exception\n");

        isb.append("     */\n");
        isb.append("    ").append(returnTypeName).append(" ").append(methodName).append("WithThrowable");
        isb.append(getMethodParams(node.getImplementationClass(), method, true)).append(" throws E;\n");

        if (hasReturnType) {
            boolean returnTypeIsPrimitive = TypeResolver.isTypePrimitive(returnType);
            if (returnTypeIsPrimitive) {
                boolean returnTypeInGenerics = generics.stream().anyMatch(returnTypeName::equals);

                Class<?> optionalReturnType = TypeResolver.getOptionalTypeForPrimitive(returnType);
                Class<?> optionalFunctionType = TypeResolver.getOptionalFunctionTypeFor(node.getImplementationClass());
                if (optionalReturnType != null && optionalFunctionType != null) {
                    isb.newlines(2).setIndent(4);
                    isb.indent().append("/**").newline();
                    isb.indent()
                            .append(" * @return An interface that will wrap the result in an optional, and return an empty optional when an exception occurs.")
                            .newline();
                    isb.indent().append(" */").newline();
                    List<String> genericsWithOptionalReturn = (returnTypeInGenerics ? generics.stream()
                            .map(gen -> gen.equals(returnTypeName) ? optionalReturnType.getTypeName() : gen) : Stream.concat(generics.stream(), Stream.of(
                            optionalReturnType.getName()))).collect(toList());
                    isb.indent().append("default ").appendClass(optionalFunctionType);
                    if (!generics.isEmpty()) {
                        isb.append(generateGenerics(genericsWithOptionalReturn, false, false));
                    }
                    isb.indent().append(" thatReturnsOptional() {\n");
                    isb.indent().append("  return ").append(getMethodParams(node.getImplementationClass(), method, false));
                    isb.indent().append(" -> {\n");
                    isb.indent().append("    try {\n");
                    isb.indent()
                            .append("      return ")
                            .appendClass(optionalReturnType)
                            .append(".of(")
                            .append(methodName)
                            .append("WithThrowable")
                            .append(getMethodParams(node.getImplementationClass(), method, false))
                            .append(");\n");
                    isb.indent().append("    } catch(Throwable throwable) {\n");
                    isb.indent().append("      return ").appendClass(optionalReturnType).append(".empty();\n");
                    isb.indent().append("    }\n");
                    isb.indent().append("  };\n");
                    isb.indent().append("}\n");
                }

                // thatReturnsOnCatch for primitives
                isb.newlines(2).setIndent(4);
                isb.indent().append("/**").newline();
                isb.indent().append(" * @param defaultReturnValue A value to return if any throwable is caught.").newline();
                isb.indent().append(" * @return An interface that returns a default value if any exception occurs.").newline();
                isb.indent().append(" */").newline();
                isb.indent().append("default ").appendClass(node.getImplementationClass());
                if (!generics.isEmpty()) {
                    isb.append(generateGenerics(generics, false, false));
                }
                isb.append(" thatReturnsOnCatch(final ").append(returnTypeName).append(" defaultReturnValue) {\n");
                isb.indent().append("  return ").append(getMethodParams(node.getImplementationClass(), method, true));
                isb.append(" -> {\n");
                isb.indent().append("    try {\n");
                isb.indent()
                        .append("      return ")
                        .append(methodName)
                        .append("WithThrowable")
                        .append(getMethodParams(node.getImplementationClass(), method, false))
                        .append(";\n");
                isb.indent().append("    } catch(final ").appendClass(Throwable.class).append(" throwable) {\n");
                isb.indent().append("      return defaultReturnValue;\n");
                isb.indent().append("    }\n");
                isb.indent().append("  };\n");
                isb.indent().append("}\n");

                // withUncheckedThrowable for primitives
                isb.newlines(2).setIndent(4);
                isb.indent().append("/**").newline();
                isb.indent().append(" * @throws E if an exception E has been thrown, it is rethrown by this method").newline();
                isb.indent().append(" * @return An interface that is only returned if no exception has been thrown.").newline();
                isb.indent().append(" */").newline();
                isb.indent().append("default ").appendClass(node.getImplementationClass());
                if (!generics.isEmpty()) {
                    isb.append(generateGenerics(generics, false, false));
                }
                isb.append(" ").append(Naming.methodThatUnsafelyThrowsUnchecked()).append("() throws E {\n");
                isb.indent().append("  return ").append(getMethodParams(node.getImplementationClass(), method, true));
                isb.append(" -> {\n");
                isb.indent().append("    try {\n");
                isb.indent()
                        .append("      return ")
                        .append(methodName)
                        .append("WithThrowable")
                        .append(getMethodParams(node.getImplementationClass(), method, false))
                        .append(";\n");
                isb.indent().append("    } catch(final ").appendClass(Throwable.class).append(" throwable) {\n");
                isb.indent().append("       SuppressedException.throwUnsafelyAsUnchecked(throwable);\n");
                isb.indent().append("       return ").append(TypeResolver.getNullTypeFor(returnType)).append(";");
                isb.indent().append("    }\n");
                isb.indent().append("  };\n");
                isb.indent().append("}\n");
            } else {
                List<String> genericsWithOptionalReturn = generics.stream()
                        .map(gen -> gen.equals(returnTypeName) ? isb.getClassContent(Optional.class) + "<" + gen + ">" : gen)
                        .collect(toList());
                boolean mixedTypes = Arrays.asList(method.getGenericParameterTypes())
                        .stream()
                        .map(t -> TypeResolver.resolveType(node.getImplementationClass(), t))
                        .anyMatch(returnType::equals);

                if (!mixedTypes) {

                    isb.newlines(2).setIndent(4);
                    isb.indent().append("/**").newline();
                    isb.indent()
                            .append(" * @return An interface that will wrap the result in an optional, and return an empty optional when an exception occurs.")
                            .newline();
                    isb.indent().append(" */").newline();
                    isb.indent().append("default ").appendClass(node.getImplementationClass());
                    if (!generics.isEmpty()) {
                        isb.append(generateGenerics(genericsWithOptionalReturn, false, false));
                    }
                    isb.indent().append("thatReturnsOptional() {\n");
                    isb.indent().append("  return ").append(getMethodParams(node.getImplementationClass(), method, true));
                    isb.indent().append(" -> {\n");
                    isb.indent().append("    try {\n");
                    isb.indent()
                            .append("      return ")
                            .appendClass(Optional.class)
                            .append(".ofNullable(")
                            .append(methodName)
                            .append("WithThrowable")
                            .append(getMethodParams(node.getImplementationClass(), method, false))
                            .append(");\n");
                    isb.indent().append("    } catch(Throwable throwable) {\n");
                    isb.indent().append("      return ").appendClass(Optional.class).append(".empty();\n");
                    isb.indent().append("    }\n");
                    isb.indent().append("  };\n");
                    isb.indent().append("}\n");
                }

                // thatReturnsOnCatch
                isb.newlines(2).setIndent(4);
                isb.indent().append("/**").newline();
                isb.indent().append(" * @param defaultReturnValue A value to return if any throwable is caught.").newline();
                isb.indent().append(" * @return An interface that returns a default value if any exception occurs.").newline();
                isb.indent().append(" */").newline();
                isb.indent().append("default ").appendClass(node.getImplementationClass());
                if (!generics.isEmpty()) {
                    isb.append(generateGenerics(generics, false, false));
                }
                isb.append(" thatReturnsOnCatch(final ").append(returnTypeName).append(" defaultReturnValue) {\n");
                isb.indent().append("  return ").append(getMethodParams(node.getImplementationClass(), method, true));
                isb.append(" -> {\n");
                isb.indent().append("    try {\n");
                isb.indent()
                        .append("      return ")
                        .append(methodName)
                        .append("WithThrowable")
                        .append(getMethodParams(node.getImplementationClass(), method, false))
                        .append(";\n");
                isb.indent().append("    } catch(final ").appendClass(Throwable.class).append(" throwable) {\n");
                isb.indent().append("      return defaultReturnValue;\n");
                isb.indent().append("    }\n");
                isb.indent().append("  };\n");
                isb.indent().append("}\n");

                // withUncheckedThrowable
                isb.newlines(2).setIndent(4);
                isb.indent().append("/**").newline();
                isb.indent().append(" * @throws E if an exception E has been thrown, it is rethrown by this method").newline();
                isb.indent().append(" * @return An interface that is only returned if no exception has been thrown.").newline();
                isb.indent().append(" */").newline();
                isb.indent().append("default ").appendClass(node.getImplementationClass());
                if (!generics.isEmpty()) {
                    isb.append(generateGenerics(generics, false, false));
                }
                isb.append(" ").append(Naming.methodThatUnsafelyThrowsUnchecked()).append("() throws E {\n");
                isb.indent().append("  return ").append(getMethodParams(node.getImplementationClass(), method, true));
                isb.append(" -> {\n");
                isb.indent().append("    try {\n");
                isb.indent()
                        .append("      return ")
                        .append(methodName)
                        .append("WithThrowable")
                        .append(getMethodParams(node.getImplementationClass(), method, false))
                        .append(";\n");
                isb.indent().append("    } catch(final ").appendClass(Throwable.class).append(" throwable) {\n");
                isb.indent().append("       SuppressedException.throwUnsafelyAsUnchecked(throwable);\n");
                isb.indent().append("       return ").append(TypeResolver.getNullTypeFor(returnType)).append(";\n");
                isb.indent().append("    }\n");
                isb.indent().append("  };\n");
                isb.indent().append("}\n");
            }
        } else {

            isb.newlines(2).setIndent(4);
            isb.indent().append("/**").newline();
            isb.indent().append(" * @return An interface that completely ignores exceptions. Consider using this method withLogging() as well.").newline();
            isb.indent().append(" */").newline();
            isb.indent().append("default ").appendClass(node.getImplementationClass());
            if (!generics.isEmpty()) {
                isb.append(generateGenerics(generics, false, false));
            }
            isb.append(" thatThrowsNothing() {").newline();

            isb.setIndent(8);
            isb.indent().append("return ").append(getMethodParams(node.getImplementationClass(), method, true)).append(" -> {").newline();
            isb.setIndent(12);
            isb.indent().append("try {").newline();
            isb.setIndent(16);
            isb.indent().append(methodName).append("WithThrowable").append(getMethodParams(node.getImplementationClass(), method, false)).append(";").newline();
            isb.setIndent(12);
            isb.indent().append("} catch(Throwable ignored) {}").newline();
            isb.setIndent(8);
            isb.indent().append("};").newline();
            isb.setIndent(4);
            isb.indent().append("}").newline();

            //withUncheckedThrowable
            isb.newlines(2).setIndent(4);
            isb.indent().append("/**").newline();
            isb.indent().append(" * @throws E if an exception E has been thrown, it is rethrown by this method").newline();
            isb.indent().append(" * @return An interface that is only returned if no exception has been thrown.").newline();
            isb.indent().append(" */").newline();
            isb.indent().append("default ").appendClass(node.getImplementationClass());
            if (!generics.isEmpty()) {
                isb.append(generateGenerics(generics, false, false));
            }

            isb.append(" ").append(Naming.methodThatUnsafelyThrowsUnchecked()).append("() throws E {").newline();

            isb.setIndent(8);
            isb.indent().append("return ").append(getMethodParams(node.getImplementationClass(), method, true)).append(" -> {").newline();
            isb.setIndent(12);
            isb.indent().append("try {").newline();
            isb.setIndent(16);
            isb.indent().append(methodName).append("WithThrowable").append(getMethodParams(node.getImplementationClass(), method, false)).append(";").newline();
            isb.setIndent(12);
            isb.indent().append("} catch(final ").appendClass(Throwable.class).append(" throwable) {").newline();
            isb.setIndent(16);
            isb.indent().append("SuppressedException.throwUnsafelyAsUnchecked(throwable);").newline();
            isb.setIndent(12);
            isb.indent().append("}").newline();
            isb.setIndent(8);
            isb.indent().append("};").newline();
            isb.setIndent(4);
            isb.indent().append("}").newline();
        }

        printWithLoggingMethod(isb, node.getImplementationClass(), className);
        printOnException(isb, node.getImplementationClass(), className);

        isb.append("}\n");

        return isb.toString();
    }

    private void printOnException(IndentStringBuilder isb, Class<?> funcInterface, String className) {

        final Method method = getFunctionalMethod(funcInterface);
        final List<String> generics = getGenerics(funcInterface);
        final String methodName = method.getName();
        final Type returnType = TypeResolver.resolveType(funcInterface, method.getGenericReturnType());
        final boolean hasReturnType = !returnType.equals(Void.TYPE);

        isb.newlines(2);
        isb.indents(4).openComment().newline();
        isb.indents(4).appendParam("consumer", "An exception consumer.").newline();
        isb.indents(4).appendReturn("An interface that will log all exceptions to given logger").newline();
        isb.indents(4).closeComment().newline();
        isb.indent().append("@SuppressWarnings(\"Duplicates\")").newline();
        isb.indents(4)
                .append("default")
                .append(" ")
                .append(className)
                .append(generateGenerics(generics, true, false))
                .append(" onException(final ")
                .appendClass(Consumer.class)
                .append("<")
                .appendClass(Throwable.class)
                .append(">")
                .append(" consumer) {")
                .newline();
        isb.indents(8).append("return ").append(getMethodParams(funcInterface, method, true)).append(" -> {").newline();
        isb.indents(12).append("try {").newline();
        isb.indents(16);
        if (hasReturnType) {
            isb.append("return ");
        }
        isb.append(methodName).append("WithThrowable").append(getMethodParams(funcInterface, method, false)).append(";").newline();
        isb.indents(12).append("} catch (final Throwable throwable) {").newline();
        isb.indents(16).append("consumer.accept(throwable);").newline();
        isb.indents(16).append("throw throwable;").newline();
        isb.indents(12).append("}").newline();
        isb.indents(8).append("};").newline();
        isb.indents(4).append("}").newline();

        boolean hasParams = method.getParameterCount() > 0;
        if (hasParams) {

            isb.newlines(2);
            isb.indents(4).openComment().newline();
            isb.indents(4).appendParam("consumer", "An exception consumer.").newline();
            isb.indents(4).appendReturn("An interface that will log all exceptions to given logger").newline();
            isb.indents(4).closeComment().newline();
            isb.indent().append("@SuppressWarnings(\"Duplicates\")").newline();
            isb.indents(4)
                    .append("default")
                    .append(" ")
                    .append(className)
                    .append(generateGenerics(generics, true, false))
                    .append(" onException(final ")
                    .appendClass(BiConsumer.class)
                    .append("<")
                    .appendClass(Throwable.class)
                    .append(", ")
                    .append("Object[]")
                    .append(">")
                    .append(" consumer) {")
                    .newline();
            isb.indents(8).append("return ").append(getMethodParams(funcInterface, method, true)).append(" -> {").newline();
            isb.indents(12).append("try {").newline();
            isb.indents(16);
            if (hasReturnType) {
                isb.append("return ");
            }
            isb.append(methodName).append("WithThrowable").append(getMethodParams(funcInterface, method, false)).append(";").newline();
            isb.indents(12).append("} catch (final Throwable throwable) {").newline();
            isb.indents(16)
                    .append("consumer.accept(throwable, new Object[]{")
                    .append(getMethodParamsStream(funcInterface, method, false).collect(Collectors.joining(", ")))
                    .append("});")
                    .newline();
            isb.indents(16).append("throw throwable;").newline();
            isb.indents(12).append("}").newline();
            isb.indents(8).append("};").newline();
            isb.indents(4).append("}").newline();
        }
    }

    private void printWithLoggingMethod(IndentStringBuilder isb, Class<?> funcInterface, String className) {
        final Method method = getFunctionalMethod(funcInterface);
        final List<String> generics = getGenerics(funcInterface);
        final String methodName = method.getName();
        final Type returnType = TypeResolver.resolveType(funcInterface, method.getGenericReturnType());
        final boolean hasReturnType = !returnType.equals(Void.TYPE);

        isb.setIndent(4);
        isb.newlines(2);
        isb.indent().append("/**").newline();
        isb.indent().append(" * @param logger The logger to log exceptions on").newline();
        isb.indent().append(" * @param message A message to use for logging exceptions").newline();
        isb.indent().append(" * @return An interface that will log all exceptions to given logger").newline();
        isb.indent().append(" */").newline();
        isb.indent().append("@SuppressWarnings(\"Duplicates\")").newline();
        isb.indent()
                .append("default")
                .append(" ")
                .append(className)
                .append(generateGenerics(generics, true, false))
                .append(" withLogging(final ")
                .appendClass(Logger.class)
                .append(" logger, final String message) {")
                .newline();
        isb.setIndent(8);

        isb.indent().append("return ").append(getMethodParams(funcInterface, method, true));
        isb.append(" -> {").newline();

        isb.setIndent(12);
        isb.indent().append("try {").newline();

        isb.setIndent(16);
        isb.indent();
        if (hasReturnType) {
            isb.append("return ");
        }
        isb.append(methodName).append("WithThrowable").append(getMethodParams(funcInterface, method, false)).append(";").newline();

        List<String> errorLogObjects = getMethodParamsStream(funcInterface, method, false).collect(toList());

        Stream.Builder<String> errorArgsBuilder = Stream.builder();
        errorArgsBuilder.add("message");
        errorLogObjects.forEach(errorArgsBuilder);
        errorArgsBuilder.add("throwable");

        List<String> errorArgs = errorArgsBuilder.build().collect(toList());

        isb.indents(12).append("} catch (final Throwable throwable) {").newline();
        isb.setIndent(16);
        isb.indent().append("logger.error").append(getParamBrackets(errorArgs)).append(";").newline();
        isb.indent().append("throw throwable;").newline();

        isb.setIndent(12);
        isb.indent().append("}").newline();
        isb.indents(8).append("};").newline();
        isb.indents(4).append("}").newline();

        isb.newlines(2);
        isb.setIndent(4);
        isb.indent().append("/**").newline();
        isb.indent().append(" * Will log WARNING level exceptions on logger if they occur within the interface").newline();
        isb.indent().append(" * @param logger The logger instance to log exceptions on").newline();
        isb.indent().append(" * @return An interface that will log exceptions on given logger").newline();
        isb.indent().append(" */").newline();
        isb.indent()
                .append("default")
                .append(" ")
                .append(className)
                .append(generateGenerics(generics, true, false))
                .append(" withLogging(final ")
                .appendClass(Logger.class)
                .append(" logger) {")
                .newline();
        isb.indents(8).append("return withLogging(logger, \"").append(getErrorMessage(className, errorLogObjects)).append("\");").newline();
        isb.indent().append("}").newline();

        isb.newlines(2);
        isb.setIndent(4);
        isb.indent().append("/**").newline();
        isb.indent().append(" * Will log WARNING level exceptions on logger if they occur within the interface").newline();
        isb.indent().append(" * @return An interface that will log exceptions on global logger").newline();
        isb.indent().append(" */").newline();
        isb.indent().append("default ").append(className).append(generateGenerics(generics, true, false)).append(" withLogging() {").newline();
        isb.indents(8).append("return withLogging(").appendClass(LoggerFactory.class).append(".getLogger(getClass()));\n");
        isb.indent().append("}\n");

        isb.append("\n");
    }

    private String getParamBrackets(List<String> arguments) {
        final StringBuilder stringBuilder = new StringBuilder();
        final Iterator<String> iterator = arguments.iterator();
        stringBuilder.append("(");
        while (iterator.hasNext()) {
            stringBuilder.append(iterator.next());
            if (iterator.hasNext()) {
                stringBuilder.append(", ");
            }
        }
        return stringBuilder.append(")").toString();
    }

    private String getMethodParams(Class<?> funcClass, Method method, boolean includeTypes) {
        return getParamBrackets(getMethodParamsStream(funcClass, method, includeTypes).collect(toList()));
    }

    private Stream<String> getMethodParamsStream(Class<?> funcClass, Method method, boolean includeTypes) {
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        return Arrays.stream(method.getGenericParameterTypes())
                .map(t -> TypeResolver.resolveType(funcClass, t))
                .map(resolvedType -> (includeTypes) ? "final " + resolvedType.getTypeName() + " " : "")
                .map(prefix -> prefix + "v" + atomicInteger.incrementAndGet());
    }

    private String getErrorMessage(String className, final List<String> errorLogObjects) {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Exception in ");
        stringBuilder.append(className);
        if (!errorLogObjects.isEmpty()) {
            if (errorLogObjects.size() > 1) {
                stringBuilder.append(" with the arguments [");
            } else {
                stringBuilder.append(" with the argument [");
            }
            stringBuilder.append(errorLogObjects.stream().map(obj -> "{}").collect(Collectors.joining(", ")));
            stringBuilder.append("]");
        }
        return stringBuilder.toString();
    }

    private String generateGenerics(List<String> generics, boolean includeException, boolean includeThrowableExtends) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<");
        Iterator<String> iterator = generics.iterator();
        while (iterator.hasNext()) {
            stringBuilder.append(iterator.next());
            if (iterator.hasNext() || includeException) {
                stringBuilder.append(", ");
            }
        }
        if (includeException) {
            stringBuilder.append("E");
            if (includeThrowableExtends) {
                stringBuilder.append(" extends Throwable");
            }
        }
        stringBuilder.append(">");
        return stringBuilder.toString();
    }

    private List<String> getGenerics(Class<?> funcInterface) {
        return Arrays.stream(funcInterface.getTypeParameters()).map(TypeVariable::getName).collect(toList());
    }
}
