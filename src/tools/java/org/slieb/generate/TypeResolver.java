package org.slieb.generate;

import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.ToDoubleBiFunction;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntBiFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongBiFunction;
import java.util.function.ToLongFunction;
import java.util.stream.Stream;

public class TypeResolver {

    public static boolean isTypePrimitive(Type type) {
        return type instanceof Class && ((Class) type).isPrimitive();
    }

    public static String getNullTypeFor(Type nxt) {
        if (isTypePrimitive(nxt)) {
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

    public static String getBasicTypeFor(Type type) {
        if (isTypePrimitive(type)) {
            switch (((Class) type).getName()) {
                case "double":
                case "int":
                case "long":
                    return "1";
                case "boolean":
                    return "true";
                default:
                    return "new Object()";
            }
        }
        return "new Object()";
    }

    public static Type resolveType(Class<?> funcInterface, Type genericReturnType) {
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

    public static Method getFunctionalMethod(Class<?> funcInterface) {
        return Arrays.stream(funcInterface.getMethods()).filter(m -> Modifier.isAbstract(m.getModifiers())).findFirst().get();
    }

    public static Class<?> getOptionalTypeForPrimitive(final Type returnType) {
        if (returnType.equals(int.class)) {
            return OptionalInt.class;
        } else if (returnType.equals(double.class)) {
            return OptionalDouble.class;
        } else if (returnType.equals(long.class)) {
            return OptionalLong.class;
        } else {
            return null;
        }
    }

    public static Class<?> getOptionalFunctionTypeFor(final Class<?> funcInterface) {
        if (Stream.of(ToIntFunction.class, ToLongFunction.class, ToDoubleFunction.class).anyMatch(funcInterface::equals)) {
            return Function.class;
        } else if (Stream.of(ToIntBiFunction.class, ToLongBiFunction.class, ToDoubleBiFunction.class).anyMatch(funcInterface::equals)) {
            return BiFunction.class;
        }
        return null;
    }
}
