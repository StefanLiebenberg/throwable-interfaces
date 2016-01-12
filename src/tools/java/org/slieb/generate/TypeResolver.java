package org.slieb.generate;


import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

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
}
