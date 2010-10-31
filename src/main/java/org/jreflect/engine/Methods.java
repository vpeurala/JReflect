package org.jreflect.engine;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Methods {
    public static List<Method> allMethodsOfClassAndSuperclasses(
            final Class<?> targetClass) {
        final List<Method> methods = new ArrayList<Method>();
        Class<?> currentClass = targetClass;
        while (currentClass != null) {
            methods.addAll(Arrays.<Method> asList(currentClass
                    .getDeclaredMethods()));
            currentClass = currentClass.getSuperclass();
        }
        return methods;
    }
}
