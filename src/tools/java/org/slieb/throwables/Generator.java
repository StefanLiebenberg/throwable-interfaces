package org.slieb.throwables;

import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * This class generates a bunch of interfaces based on some crazy reflecting of the utilities in java.util.function.*
 */
public class Generator {


    private final String packageName;
    private final File directory, testDirectory;

    public Generator(String packageName, File directory, File testDirectory) {
        this.packageName = packageName;
        this.directory = directory;
        this.testDirectory = testDirectory;
    }


    public void generate(Class<?> funcInterface) {
        try {
            String className = funcInterface.getSimpleName() + "WithThrowable";
            String testName = className + "Test";
            generateImplementation(funcInterface, className);
            generateTest(funcInterface, className, testName);
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
    }

    public void generateImplementation(Class<?> funcInterface, String className) throws IOException {
        generateFile(directory, packageName, className, getImplementationContent(funcInterface, className));
    }

    public void generateTest(Class<?> funcInterface, String className, String testName) throws IOException {
        generateFile(testDirectory, packageName, testName, getTestContent(funcInterface, className, testName));
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

    private String getTestContent(Class<?> funcInterface, String className, String testName) {
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
            stringBuilder.append(" return ").append(getNullTypeFor(returnType)).append(";\n");
        }
        stringBuilder.append("    }).").append(getMethodCall(funcInterface, method)).append(";\n");
        stringBuilder.append(" }\n\n");

//        @Test
//        public void testNormalOperation() {

//        }

        stringBuilder.append("}\n");

        return stringBuilder.toString();
    }

    private String getMethodCall(Class funcClass, Method method) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(method.getName());
        stringBuilder.append("(");
        Iterator<Type> iterator = Arrays.stream(method.getGenericParameterTypes())
                .map(t -> resolveType(funcClass, t))
                .iterator();
        while (iterator.hasNext()) {
            Type nxt = iterator.next();
            stringBuilder.append(getNullTypeFor(nxt));
            if (iterator.hasNext()) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append(")");

        return stringBuilder.toString();
    }

    private String getNullTypeFor(Type nxt) {
        if (nxt instanceof Class && ((Class) nxt).isPrimitive()) {
            switch (((Class) nxt).getName()) {
                case "double":
                case "int":
                case "long":
                    return "0";
                case "boolean":
                    return "false";
                default:
                    return "null";
            }
        }
        return "null";
    }


    private String getImplementationContent(Class<?> funcInterface, String className) {
        List<String> generics = getGenerics(funcInterface);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("package ").append(packageName).append(";\n");
        stringBuilder.append("\n");
        stringBuilder.append("/**\n");
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
        stringBuilder.append(" {\n");

        String objectName = className.toLowerCase();
        stringBuilder.append("    /**\n");
        stringBuilder.append("     * Utility method to mark lambdas of type ").append(className).append("\n");
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

        Type returnType = resolveType(funcInterface, method.getGenericReturnType());

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


        stringBuilder.append("}\n");

        return stringBuilder.toString();
    }

    private Type resolveType(Class<?> funcInterface, Type genericReturnType) {
        for (Class<?> interfaceClass : funcInterface.getInterfaces()) {
            final TypeVariable<? extends Class<?>>[] typeParameters = interfaceClass.getTypeParameters();
            for (int i = 0; i < typeParameters.length; i++) {
                for (AnnotatedType annotatedInterface : funcInterface.getAnnotatedInterfaces()) {
                    final Type type = annotatedInterface.getType();
                    if (type instanceof ParameterizedTypeImpl) {
                        if (((ParameterizedTypeImpl) type).getRawType().equals(interfaceClass)) {
                            return ((ParameterizedTypeImpl) type).getActualTypeArguments()[i];
                        }
                    }
                }

            }

        }
        return genericReturnType;
    }

    private String getMethodParams(
            Class<?> funcClass,
            Method method, boolean includeTypes) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(");
        Iterator<Type> iterator = Arrays.stream(method.getGenericParameterTypes())
                .map(t -> resolveType(funcClass, t))
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
                .collect(Collectors.toList());
    }

    public static void main(String[] args) throws IOException {

        Generator generator = new Generator("org.slieb.throwables", new File("src/main/java"), new File("src/test/java"));
        generator.generate(java.util.function.BiConsumer.class);
        generator.generate(java.util.function.BiFunction.class);
        generator.generate(java.util.function.BinaryOperator.class);
        generator.generate(java.util.function.BooleanSupplier.class);
        generator.generate(java.util.function.Consumer.class);
        generator.generate(java.util.function.DoubleBinaryOperator.class);
        generator.generate(java.util.function.DoubleConsumer.class);
        generator.generate(java.util.function.DoubleFunction.class);
        generator.generate(java.util.function.DoublePredicate.class);
        generator.generate(java.util.function.DoubleSupplier.class);
        generator.generate(java.util.function.DoubleToIntFunction.class);
        generator.generate(java.util.function.DoubleToLongFunction.class);
        generator.generate(java.util.function.DoubleUnaryOperator.class);
        generator.generate(java.util.function.Function.class);
        generator.generate(java.util.function.IntBinaryOperator.class);
        generator.generate(java.util.function.IntConsumer.class);
        generator.generate(java.util.function.IntPredicate.class);
        generator.generate(java.util.function.IntSupplier.class);
        generator.generate(java.util.function.LongBinaryOperator.class);
        generator.generate(java.util.function.LongConsumer.class);
        generator.generate(java.util.function.LongFunction.class);
        generator.generate(java.util.function.LongPredicate.class);
        generator.generate(java.util.function.LongSupplier.class);
        generator.generate(java.util.function.LongToDoubleFunction.class);
        generator.generate(java.util.function.LongToIntFunction.class);
        generator.generate(java.util.function.LongUnaryOperator.class);
        generator.generate(java.util.function.ObjDoubleConsumer.class);
        generator.generate(java.util.function.ObjIntConsumer.class);
        generator.generate(java.util.function.ObjLongConsumer.class);
        generator.generate(java.util.function.Predicate.class);
        generator.generate(java.util.function.Supplier.class);
        generator.generate(java.util.function.ToDoubleBiFunction.class);
        generator.generate(java.util.function.ToDoubleFunction.class);
        generator.generate(java.util.function.ToIntBiFunction.class);
        generator.generate(java.util.function.ToIntFunction.class);
        generator.generate(java.util.function.ToLongBiFunction.class);
        generator.generate(java.util.function.ToLongFunction.class);
        generator.generate(java.util.function.UnaryOperator.class);
    }
}
