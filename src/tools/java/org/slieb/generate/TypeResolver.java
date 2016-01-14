package org.slieb.generate;


import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.*;
import java.util.Arrays;

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
        return Arrays.stream(funcInterface.getMethods())
                .filter(m -> Modifier.isAbstract(m.getModifiers())).findFirst().get();
    }
}
