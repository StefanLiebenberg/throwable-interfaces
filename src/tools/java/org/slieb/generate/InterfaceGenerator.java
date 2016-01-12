package org.slieb.generate;

import org.slf4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.stream.Collectors.toList;

/**
 * This class generates a bunch of interfaces based on some crazy reflecting of the utilities in java.util.function.*
 */
public class InterfaceGenerator {

    private final String packageName;
    private final File directory, testDirectory;

    public InterfaceGenerator(String packageName, File directory, File testDirectory) {
        this.packageName = packageName;
        this.directory = directory;
        this.testDirectory = testDirectory;
    }


    public void generate(Class<?> funcInterface) {
        try {
            String className = funcInterface.getSimpleName() + "WithThrowable";
            generateImplementation(funcInterface, className);
            generateCastTest(funcInterface, className, className + "CastTest");
            generateConvertTest(funcInterface, className, className + "ConvertTest");
            generateIgnoresTest(funcInterface, className, className + "IgnoresTest");
            generateLogableTest(funcInterface, className, className + "LogableTest");
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
    }

    public void generateImplementation(Class<?> funcInterface, String className) throws IOException {
        generateFile(directory, packageName, className, getImplementationContent(funcInterface, className));
    }

    public void generateCastTest(Class<?> funcInterface, String className, String testName) throws IOException {
        generateFile(testDirectory, packageName, testName, getCastTestContent(funcInterface, className, testName));
    }

    public void generateConvertTest(Class<?> funcInterface, String className, String testName) throws IOException {
        generateFile(testDirectory, packageName, testName, getConvertTestContent(funcInterface, className, testName));
    }


    public void generateIgnoresTest(Class<?> funcInterface, String className, String testName) throws IOException {
        generateFile(testDirectory, packageName, testName, getIgnoresTestContent(funcInterface, className, testName));
    }

    public void generateLogableTest(Class<?> funcInterface, String className, String testName) throws IOException {
        generateFile(testDirectory, packageName, testName, getLoggableTestContent(funcInterface, className, testName));
    }

    public void generateFile(File directory, String pkg, String fileName, String content) throws IOException {
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

    private String getCastTestContent(Class<?> funcInterface, String className, String testName) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("package ").append(packageName).append(";\n");
        stringBuilder.append("import org.junit.Test;\n");

        stringBuilder.append("import static ").append(packageName).append(".").append(className).append(".cast").append(className).append(";\n");

        stringBuilder.append("public class ").append(testName);
        stringBuilder.append(" {\n");

        Method method = getMethod(funcInterface);
        String params = getMethodParams(funcInterface, method, false);

        stringBuilder.append(" @Test(expected = SuppressedException.class)\n");
        stringBuilder.append(" public void testThrowCheckedException() {\n");

        stringBuilder.append("    cast").append(className).append("(").append(params).append(" -> {\n");
        stringBuilder.append("      throw new Exception(\"expected error\");\n");
        stringBuilder.append("    }).").append(getMethodCall(funcInterface, method)).append(";\n");
        stringBuilder.append(" }\n\n");


        stringBuilder.append(" @Test(expected = RuntimeException.class)\n");
        stringBuilder.append(" public void testThrowRuntimeException() {\n");

        stringBuilder.append("    cast").append(className).append("(").append(params).append(" -> {\n");
        stringBuilder.append("      throw new RuntimeException(\"expected error\");\n");
        stringBuilder.append("    }).").append(getMethodCall(funcInterface, method)).append(";\n");
        stringBuilder.append(" }\n\n");


        stringBuilder.append(" @Test(expected = Error.class)\n");
        stringBuilder.append(" public void testThrowError() {\n");

        stringBuilder.append("    cast").append(className).append("(").append(params).append(" -> {\n");
        stringBuilder.append("      throw new Error(\"expected error\");\n");
        stringBuilder.append("    }).").append(getMethodCall(funcInterface, method)).append(";\n");
        stringBuilder.append(" }\n\n");


        stringBuilder.append(" @Test(expected = Throwable.class)\n");
        stringBuilder.append(" public void testThrowThrowable() {\n");

        stringBuilder.append("    cast").append(className).append("(").append(params).append(" -> {\n");
        stringBuilder.append("       throw new Throwable(\"expected throwable\");\n");
        stringBuilder.append("    }).").append(getMethodCall(funcInterface, method)).append(";\n");
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
        stringBuilder.append("    }).").append(getMethodCall(funcInterface, method)).append(";\n");
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

        Method method = getMethod(funcInterface);
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

        Method method = getMethod(funcInterface);
        String params = getMethodParams(funcInterface, method, false);

        Type returnType = TypeResolver.resolveType(funcInterface, method.getGenericReturnType());
        boolean hasReturnType = !returnType.equals(Void.TYPE);

        if (!hasReturnType) {

            stringBuilder.append(" @Test(expected = Exception.class)\n");
            stringBuilder.append(" public void testThrowExceptionWithNoIgnores() {\n");

            stringBuilder.append("    cast").append(className).append("(").append(params).append(" -> {\n");
            stringBuilder.append("      throw new Exception(\"expected error\");\n");
            stringBuilder.append("    }).thatIgnores().").append(getMethodCall(funcInterface, method)).append(";\n");
            stringBuilder.append(" }\n\n");

            stringBuilder.append(" @Test\n");
            stringBuilder.append(" public void testThrowCheckedWithIgnore() {\n");
            stringBuilder.append("    cast").append(className).append("(").append(params).append(" -> {\n");
            stringBuilder.append("      throw new Exception(\"expected error\");\n");
            stringBuilder.append("    }).thatIgnores(Exception.class).").append(getMethodCall(funcInterface, method)).append(";\n");
            stringBuilder.append(" }\n\n");


            stringBuilder.append(" @Test\n");
            stringBuilder.append(" public void testThrowIgnoreThrowables() {\n");
            stringBuilder.append("    cast").append(className).append("(").append(params).append(" -> {\n");
            stringBuilder.append("      throw new Exception(\"expected error\");\n");
            stringBuilder.append("    }).thatIgnoresThrowables().").append(getMethodCall(funcInterface, method)).append(";\n");
            stringBuilder.append(" }\n\n");


        }


        stringBuilder.append(" @Test(expected = RuntimeException.class)\n");
        stringBuilder.append(" public void testThrowRuntimeException() {\n");

        stringBuilder.append("    cast").append(className).append("(").append(params).append(" -> {\n");
        stringBuilder.append("      throw new RuntimeException(\"expected error\");\n");
        stringBuilder.append("    }).").append(getMethodCall(funcInterface, method)).append(";\n");
        stringBuilder.append(" }\n\n");


        stringBuilder.append(" @Test(expected = Error.class)\n");
        stringBuilder.append(" public void testThrowError() {\n");

        stringBuilder.append("    cast").append(className).append("(").append(params).append(" -> {\n");
        stringBuilder.append("      throw new Error(\"expected error\");\n");
        stringBuilder.append("    }).").append(getMethodCall(funcInterface, method)).append(";\n");
        stringBuilder.append(" }\n\n");


        stringBuilder.append(" @Test(expected = Throwable.class)\n");
        stringBuilder.append(" public void testThrowThrowable() {\n");

        stringBuilder.append("    cast").append(className).append("(").append(params).append(" -> {\n");
        stringBuilder.append("       throw new Throwable(\"expected throwable\");\n");
        stringBuilder.append("    }).").append(getMethodCall(funcInterface, method)).append(";\n");
        stringBuilder.append(" }\n\n");


        stringBuilder.append(" @Test\n");
        stringBuilder.append(" public void testAnnotatedWithFunctionalInterface() {\n");
        stringBuilder.append("    ").append(className).append(".class.isAnnotationPresent(FunctionalInterface.class);\n");
        stringBuilder.append(" }\n\n");

        stringBuilder.append(" @Test\n");
        stringBuilder.append(" public void testNormalOperation() {\n");
        stringBuilder.append("    cast").append(className).append("(").append(params).append(" -> {\n");

        if (!returnType.equals(Void.TYPE)) {
            stringBuilder.append(" return ").append(TypeResolver.getNullTypeFor(returnType)).append(";\n");
        }
        stringBuilder.append("    }).").append(getMethodCall(funcInterface, method)).append(";\n");
        stringBuilder.append(" }\n\n");


        stringBuilder.append("}\n");

        return stringBuilder.toString();
    }

    private String getLoggableTestContent(Class<?> funcInterface, String className, String testName) {

        IndentStringBuilder logableTest = new IndentStringBuilder();
        logableTest.append("package ").append(packageName).append(";\n");
        logableTest.append("import org.junit.Test;\n");

        logableTest.append("import static ").append(packageName).append(".").append(className).append(".cast").append(className).append(";\n");

        logableTest.append("public class ").append(testName);
        logableTest.append(" {\n");

        Method method = getMethod(funcInterface);
        String params = getMethodParams(funcInterface, method, false);


        logableTest.setIndent(4);
        logableTest.newlines(3);
        logableTest.indent().append("private ThrownHandler tHandler;").newline();
        logableTest.indent().append("private java.util.logging.Logger globalLogger;").newline();

        logableTest.setIndent(4);
        logableTest.newlines(3);
        logableTest.indent().append("@org.junit.Before").newline();
        logableTest.indent().append("public void setup() {").newline();

        logableTest.setIndent(8);
        logableTest.indent().append("tHandler = new ThrownHandler();").newline();
        logableTest.indent().append("globalLogger = java.util.logging.Logger.getLogger(\"\");").newline();
        logableTest.indent().append("globalLogger.addHandler(tHandler);").newline();

        logableTest.indents(4).append("}").newline();

        logableTest.setIndent(4);
        logableTest.newlines(3);
        logableTest.indent().append("@org.junit.After").newline();
        logableTest.indent().append("public void teardown() {").newline();
        logableTest.setIndent(8);
        logableTest.indent().append("globalLogger.removeHandler(tHandler);").newline();
        logableTest.indents(4).append("}").newline();

        logableTest.append(" @Test\n");
        logableTest.append(" public void testThrowCheckedException() {\n");
        logableTest.indents(8).append("Exception expected = new Exception(\"EXPECTED ERROR\");").newline();
        logableTest.indent().append("try {").newline();
        logableTest.append("    cast").append(className).append("(").append(params).append(" -> {\n");
        logableTest.append("      throw expected;\n");
        logableTest.append("    }).withLogging().").append(getMethodCall(funcInterface, method)).append(";\n");
        logableTest.indent().append("} catch (Exception ignored) {}").newline();
        logableTest.indents(8).append("org.junit.Assert.assertEquals(expected, tHandler.getLastRecord().getThrown());").newline();
        logableTest.append(" }\n\n");

        logableTest.append(" @Test\n");
        logableTest.append(" public void testNormalOperation() {\n");
        logableTest.append("    cast").append(className).append("(").append(params).append(" -> {\n");

        final Class<?> returnType = method.getReturnType();
        if (!returnType.equals(Void.TYPE)) {
            logableTest.append(" return ").append(TypeResolver.getNullTypeFor(returnType)).append(";\n");
        }
        logableTest.append("    }).withLogging().").append(getMethodCall(funcInterface, method)).append(";\n");
        logableTest.append(" }\n\n");


        logableTest.append("}\n");

        return logableTest.toString();
    }

    private String getMethodCall(Class funcClass, Method method) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(method.getName());
        stringBuilder.append("(");
        Iterator<Type> iterator = Arrays.stream(method.getGenericParameterTypes())
                .map(t -> TypeResolver.resolveType(funcClass, t))
                .iterator();
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


    private String getImplementationContent(Class<?> funcInterface, String className) {
        List<String> generics = getGenerics(funcInterface);
        IndentStringBuilder stringBuilder = new IndentStringBuilder();
        stringBuilder.append("package ").append(packageName).append(";")
                .newlines(2);
        stringBuilder.append("/**").newline();
        stringBuilder.append(" * Generated from ").append(funcInterface.getName()).append("\n");
        stringBuilder.append(" * Extends ").append(funcInterface.getName()).append(" to allow for a checked exception.\n");
        stringBuilder.append(" *\n");
        generics.forEach(gen -> stringBuilder.append(" * @param <").append(gen).append("> some generic flag\n"));
        stringBuilder.append(" * @param <E> The extension\n");
        stringBuilder.append(" */\n");
        stringBuilder.append("@FunctionalInterface").append("\n");
        stringBuilder.append("public interface ").append(className);
        stringBuilder.append(generateGenerics(generics, true, true));


        stringBuilder.append(" extends ").append(funcInterface.getName());
        if (!generics.isEmpty()) {
            stringBuilder.append(generateGenerics(generics, false, false));
        }
        stringBuilder.append(" {").newline();

        stringBuilder.newlines(2);
        stringBuilder.setIndent(4);
        String objectName = className.toLowerCase();
        stringBuilder.append("    /**\n");
        stringBuilder.append("     * Utility method to mark lambdas of type ").append(className).append("\n");
        stringBuilder.append("     *\n");
        stringBuilder.append("     * @param ").append(objectName).append(" The interface instance\n");
        generics.forEach(gen -> stringBuilder.append("     * @param <").append(gen).append("> Generic that corresponds to the same generic on ").append(funcInterface.getSimpleName()).append("  \n"));
        stringBuilder.append("     * @param <E> The type this interface is allowed to throw\n");
        stringBuilder.append("     * @return the cast interface\n");
        stringBuilder.append("     */\n");
        stringBuilder.append("    static ")
                .append(generateGenerics(generics, true, true))
                .append(" ").append(className).append(generateGenerics(generics, true, false))
                .append(" cast").append(className)
                .append("(").append(className).append(generateGenerics(generics, true, false)).append(" ").append(objectName).append(") {\n")
                .append("        return ").append(objectName).append(";\n")
                .append("    }\n");

        Method method = getMethod(funcInterface);
        String methodName = method.getName();

        String variableName = funcInterface.getSimpleName().toLowerCase();
        stringBuilder.append("    /**\n");
        stringBuilder.append("     * Utility method to convert ").append(className).append("\n");
        stringBuilder.append("     * @param ").append(variableName).append(" The interface instance\n");
        generics.forEach(gen -> stringBuilder.append("     * @param <").append(gen).append("> Generic that corresponds to the same generic on ").append(funcInterface.getSimpleName()).append("  \n"));
        stringBuilder.append("     * @param <E> The type this interface is allowed to throw\n");
        stringBuilder.append("     * @return the cast interface\n");
        stringBuilder.append("     */\n");
        stringBuilder.append("    static ")
                .append(generateGenerics(generics, true, true))
                .append(" ").append(className).append(generateGenerics(generics, true, false))
                .append(" as").append(className)
                .append("(").append(funcInterface.getName());
        if (!generics.isEmpty()) {
            stringBuilder.append(generateGenerics(generics, false, false));
        }

        stringBuilder.append(" ").append(variableName).append(") {\n")
                .append("        return ").append(variableName).append("::").append(methodName).append(";\n")
                .append("    }\n");


        Type returnType = TypeResolver.resolveType(funcInterface, method.getGenericReturnType());
        boolean hasReturnType = !returnType.equals(Void.TYPE);
        String returnTypeName = hasReturnType ? returnType.getTypeName() : "void";
        stringBuilder.append("\n");

        stringBuilder.append("    /** \n");
        stringBuilder.append("     * Overridden method of ").append(className).append(" that will call ").append(methodName).append("WithThrowable, but catching any exceptions.\n");
        stringBuilder.append("     *\n");
        for (int i = 0; i < method.getGenericParameterTypes().length; i++) {
            stringBuilder.append("     * @param v").append(i + 1).append(" parameter to overridden method\n");
        }
        if (hasReturnType) {
            stringBuilder.append("     * @return the value\n");
        }
        stringBuilder.append("     */\n");
        stringBuilder.append("    @Override\n");
        stringBuilder.append("    default ").append(returnTypeName).append(" ");
        stringBuilder.append(methodName).append(getMethodParams(funcInterface, method, true)).append(" {\n");
        stringBuilder.append("        try {\n");
        stringBuilder.append("            ");
        if (hasReturnType) {
            stringBuilder.append("return ");
        }
        stringBuilder.append(methodName).append("WithThrowable");
        stringBuilder.append(getMethodParams(funcInterface, method, false)).append(";\n");
        stringBuilder.append("        } catch (final RuntimeException | Error exception) {\n");
        stringBuilder.append("            throw exception;\n");
        stringBuilder.append("        } catch (final Throwable throwable) {\n");
        stringBuilder.append("            throw new org.slieb.throwables.SuppressedException(throwable);\n");
        stringBuilder.append("        }\n");

        stringBuilder.append("    }\n");

        stringBuilder.append("\n");

        stringBuilder.append("    /** \n");
        stringBuilder.append("     * Functional method that will throw exceptions.\n");
        stringBuilder.append("     *\n");
        for (int i = 0; i < method.getGenericParameterTypes().length; i++) {
            stringBuilder.append("     * @param v").append(i + 1).append(" parameter to overridden method\n");
        }

        if (hasReturnType) {
            stringBuilder.append("     * @return the value\n");
        }
        stringBuilder.append("     * @throws E some exception\n");


        stringBuilder.append("     */\n");
        stringBuilder.append("    ").append(returnTypeName).append(" ").append(methodName).append("WithThrowable");
        stringBuilder.append(getMethodParams(funcInterface, method, true)).append(" throws E;\n");


        if (hasReturnType) {
            boolean returnTypeIsPrimitive = TypeResolver.isTypePrimitive(returnType);

            if (returnTypeIsPrimitive) {
                System.out.println("todo!");
                // todo, a lot of type resolution needs to happen here.
//                Type optionalReturnType = TypeResolver.getOptionalTypeForPrimitive(returnType);
//                if (optionalReturnType != null) {
//                    List<String> genericsWithOptionalReturn = generics.stream().map(gen -> gen.equals(returnTypeName) ? optionalReturnType.getTypeName() : gen).collect(toList());
//                    stringBuilder.append("default ").append(funcInterface.getName());
//                    if (!generics.isEmpty()) {
//                        stringBuilder.append(generateGenerics(genericsWithOptionalReturn, false, false));
//                    }
//                    stringBuilder.append(" thatReturnsOptional() {\n");
//                    stringBuilder.append("  return ").append(getMethodParams(funcInterface, method, false));
//                    stringBuilder.append(" -> {\n");
//                    stringBuilder.append("    try {\n");
//                    stringBuilder.append("      return java.util.Optional.of(").append(methodName).append("WithThrowable")
//                            .append(getMethodParams(funcInterface, method, false))
//                            .append(");\n");
//                    stringBuilder.append("    } catch(Throwable throwable) {\n");
//                    stringBuilder.append("      return java.util.Optional.empty();\n");
//                    stringBuilder.append("    }\n");
//                    stringBuilder.append("  };\n");
//                    stringBuilder.append("}\n");
//                }
            } else {
                List<String> genericsWithOptionalReturn = generics.stream().map(gen -> gen.equals(returnTypeName) ? "java.util.Optional<" + gen + ">" : gen).collect(toList());
                boolean mixedTypes = Arrays.asList(method.getGenericParameterTypes())
                        .stream().map(t -> TypeResolver.resolveType(funcInterface, t)).anyMatch(returnType::equals);

                if (!mixedTypes) {


                    stringBuilder.newlines(2).setIndent(4);
                    stringBuilder.indent().append("/**").newline();
                    stringBuilder.indent().append(" * @return A interface that will wrap the result in an optional, and return an empty optional when an exception occurs.").newline();
                    stringBuilder.indent().append(" */").newline();
                    stringBuilder.indent().append("default ").append(funcInterface.getName());
                    if (!generics.isEmpty()) {
                        stringBuilder.append(generateGenerics(genericsWithOptionalReturn, false, false));
                    }
                    stringBuilder.indent().append("thatReturnsOptional() {\n");
                    stringBuilder.indent().append("  return ").append(getMethodParams(funcInterface, method, false));
                    stringBuilder.indent().append(" -> {\n");
                    stringBuilder.indent().append("    try {\n");
                    stringBuilder.indent().append("      return java.util.Optional.of(").append(methodName).append("WithThrowable")
                            .append(getMethodParams(funcInterface, method, false))
                            .append(");\n");
                    stringBuilder.indent().append("    } catch(Throwable throwable) {\n");
                    stringBuilder.indent().append("      return java.util.Optional.empty();\n");
                    stringBuilder.indent().append("    }\n");
                    stringBuilder.indent().append("  };\n");
                    stringBuilder.indent().append("}\n");
                }

                stringBuilder.newlines(2).setIndent(4);
                stringBuilder.indent().append("/**").newline();
                stringBuilder.indent().append(" * @return An interface that returns a default value if any exception occurs.").newline();
                stringBuilder.indent().append(" */").newline();
                stringBuilder.indent().append("default ").append(funcInterface.getName());
                if (!generics.isEmpty()) {
                    stringBuilder.append(generateGenerics(generics, false, false));
                }
                stringBuilder.append(" thatReturnsDefaultValue(").append(returnTypeName).append(" defaultReturnValue) {\n");
                stringBuilder.indent().append("  return ").append(getMethodParams(funcInterface, method, false));
                stringBuilder.append(" -> {\n");
                stringBuilder.indent().append("    try {\n");
                stringBuilder.indent().append("      return ").append(methodName).append("WithThrowable")
                        .append(getMethodParams(funcInterface, method, false))
                        .append(";\n");
                stringBuilder.indent().append("    } catch(Throwable throwable) {\n");
                stringBuilder.indent().append("      return defaultReturnValue;\n");
                stringBuilder.indent().append("    }\n");
                stringBuilder.indent().append("  };\n");
                stringBuilder.indent().append("}\n");
            }
        } else {


            stringBuilder.newlines(2).setIndent(4);
            stringBuilder.indent().append("/**").newline();
            stringBuilder.indent().append(" * @return A interface that ignores some exceptions.").newline();
            stringBuilder.indent().append(" */").newline();
            stringBuilder.indent().append("@SuppressWarnings(\"Duplicates\")").newline();
            stringBuilder.indent().append("default ").append(className);
            if (!generics.isEmpty()) {
                stringBuilder.append(generateGenerics(generics, true, false));
            }
            stringBuilder.append(" thatIgnores(Class<? extends Throwable> ... throwableClasses) {").newline();

            stringBuilder.setIndent(8);
            stringBuilder.indent().append("return ").append(getMethodParams(funcInterface, method, false))
                    .append(" -> {").newline();
            stringBuilder.setIndent(12);
            stringBuilder.indent().append("try {").newline();
            stringBuilder.setIndent(16);
            stringBuilder.indent().append(methodName).append("WithThrowable")
                    .append(getMethodParams(funcInterface, method, false))
                    .append(";").newline();
            stringBuilder.setIndent(12);
            stringBuilder.indent().append("} catch(Throwable throwable) {").newline();
            stringBuilder.setIndent(16);
            stringBuilder.indent().append("if(").append(Arrays.class.getCanonicalName()).append(".stream(throwableClasses).noneMatch((Class<? extends Throwable> klass) -> klass.isInstance(throwable))) {").newline();
            stringBuilder.indents(20).append("throw throwable;").newline();
            stringBuilder.indent().append("}").newline();
            stringBuilder.setIndent(12);
            stringBuilder.indent().append("}").newline();
            stringBuilder.setIndent(8);
            stringBuilder.indent().append("};").newline();
            stringBuilder.setIndent(4);
            stringBuilder.indent().append("}").newline();


            stringBuilder.newlines(2).setIndent(4);
            stringBuilder.indent().append("/**").newline();
            stringBuilder.indent().append(" * @return A interface that completely ignores exceptions. Consider using this method withLogging() as well.").newline();
            stringBuilder.indent().append(" */").newline();
            stringBuilder.indent().append("default ").append(funcInterface.getName());
            if (!generics.isEmpty()) {
                stringBuilder.append(generateGenerics(generics, false, false));
            }
            stringBuilder.append(" thatIgnoresThrowables() {").newline();

            stringBuilder.setIndent(8);
            stringBuilder.indent().append("return ").append(getMethodParams(funcInterface, method, false))
                    .append(" -> {").newline();
            stringBuilder.setIndent(12);
            stringBuilder.indent().append("try {").newline();
            stringBuilder.setIndent(16);
            stringBuilder.indent().append(methodName).append("WithThrowable")
                    .append(getMethodParams(funcInterface, method, false))
                    .append(";").newline();
            stringBuilder.setIndent(12);
            stringBuilder.indent().append("} catch(Throwable ignored) {}").newline();
            stringBuilder.setIndent(8);
            stringBuilder.indent().append("};").newline();
            stringBuilder.setIndent(4);
            stringBuilder.indent().append("}").newline();
        }

        stringBuilder.newlines(2);
        stringBuilder.setIndent(4);
        stringBuilder.indent().append("/**").newline();
        stringBuilder.indent().append(" * @param logger The logger to log exceptions on").newline();
        stringBuilder.indent().append(" * @param message A message to use for logging exceptions").newline();
        stringBuilder.indent().append(" * @return An interface that will log all exceptions to given logger").newline();
        stringBuilder.indent().append(" */").newline();
        stringBuilder.indent().append("@SuppressWarnings(\"Duplicates\")").newline();
        stringBuilder.indent().append("default")
                .append(" ").append(className).append(generateGenerics(generics, true, false))
                .append(" withLogging(").append(Logger.class.getCanonicalName()).append(" logger, String message) {")
                .newline();
        stringBuilder.setIndent(8);

        stringBuilder.indent().append("return ").append(getMethodParams(funcInterface, method, false));
        stringBuilder.append(" -> {").newline();

        stringBuilder.setIndent(12);
        stringBuilder.indent().append("try {").newline();

        stringBuilder.setIndent(16);
        stringBuilder.indent();
        if (hasReturnType) {
            stringBuilder.append("return ");
        }
        stringBuilder.append(methodName).append("WithThrowable").append(getMethodParams(funcInterface, method, false))
                .append(";").newline();

        stringBuilder.indents(12).append("} catch (final Throwable throwable) {").newline();
        stringBuilder.setIndent(16);
        stringBuilder.indent().append("logger.error(message, throwable);").newline();
        stringBuilder.indent().append("throw throwable;").newline();

        stringBuilder.setIndent(12);
        stringBuilder.indent().append("}").newline();
        stringBuilder.indents(8).append("};").newline();
        stringBuilder.indents(4).append("}").newline();


        stringBuilder.newlines(2);
        stringBuilder.setIndent(4);
        stringBuilder.indent().append("/**").newline();
        stringBuilder.indent().append(" * Will log WARNING level exceptions on logger if they occur within the interface").newline();
        stringBuilder.indent().append(" * @param logger The logger instance to log exceptions on").newline();
        stringBuilder.indent().append(" * @return An interface that will log exceptions on given logger").newline();
        stringBuilder.indent().append(" */").newline();
        stringBuilder.indent().append("default")
                .append(" ").append(className).append(generateGenerics(generics, true, false))
                .append(" withLogging(").append(Logger.class.getCanonicalName()).append(" logger) {").newline();
        stringBuilder.indents(8).append("return withLogging(logger, \"Exception in ").append(className).append("\");").newline();
        stringBuilder.indent().append("}").newline();

        stringBuilder.newlines(2);
        stringBuilder.setIndent(4);
        stringBuilder.indent().append("/**").newline();
        stringBuilder.indent().append(" * Will log WARNING level exceptions on logger if they occur within the interface").newline();
        stringBuilder.indent().append(" * @return An interface that will log exceptions on global logger").newline();
        stringBuilder.indent().append(" */").newline();
        stringBuilder.indent().append("default ").append(className).append(generateGenerics(generics, true, false))
                .append(" withLogging() {").newline();
        stringBuilder.indents(8).append("return withLogging(org.slf4j.LoggerFactory.getLogger(getClass()));\n");
        stringBuilder.indent().append("}\n");

        stringBuilder.append("\n");


//
//        default BiFunction<T, U, R> thatReturns(R defaultValue) {
//            return (t, u) -> {
//                try {
//                    return applyWithThrowable(t, u);
//                } catch (Throwable ignored) {
//                    return defaultValue;
//                }
//            };
//        }
//

//
//        default BiFunctionWithThrowable<T, U, R, E> withLogging(Logger logger) {
//            return (t, u) -> {
//                try {
//                    return applyWithThrowable(t, u);
//                } catch (Throwable ignored) {
//                    logger.log(Level.WARNING, "exception:", ignored);
//                    throw ignored;
//                }
//            };
//        }


        stringBuilder.append("}\n");

        return stringBuilder.toString();
    }


    private String getMethodParams(
            Class<?> funcClass,
            Method method, boolean includeTypes) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(");
        Iterator<Type> iterator = Arrays.stream(method.getGenericParameterTypes())
                .map(t -> TypeResolver.resolveType(funcClass, t))
                .iterator();
        while (iterator.hasNext()) {
            Type nxt = iterator.next();
            if (includeTypes) {
                stringBuilder.append(nxt.getTypeName()).append(" ");
            }
            stringBuilder.append("v").append(atomicInteger.incrementAndGet());
            if (iterator.hasNext()) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    private Method getMethod(Class<?> funcInterface) {
        return Arrays.stream(funcInterface.getMethods())
                .filter(m -> Modifier.isAbstract(m.getModifiers())).findFirst().get();
    }

    public String generateGenerics(List<String> generics, boolean includeException, boolean includeThrowableExtends) {
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
        return Arrays.stream(funcInterface.getTypeParameters())
                .map(TypeVariable::getName)
                .collect(toList());
    }

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
        interfaceGenerator.generate(java.util.function.UnaryOperator.class);
    }
}
