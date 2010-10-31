package org.jreflect.engine;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Methods {
    public static List<Method> allMethodsOfClass(final Class<?> targetClass) {
        final List<Method> methods = new ArrayList<Method>();
        methods.addAll(Arrays.<Method> asList(targetClass.getDeclaredMethods()));
        sortMethods(methods);
        return methods;
    }

    public static List<Method> allMethodsOfClassAndSuperclasses(
            final Class<?> targetClass) {
        final List<Method> methods = new ArrayList<Method>();
        Class<?> currentClass = targetClass;
        while (currentClass != null) {
            methods.addAll(Arrays.<Method> asList(currentClass
                    .getDeclaredMethods()));
            currentClass = currentClass.getSuperclass();
        }
        sortMethods(methods);
        return methods;
    }

    private static void sortMethods(final List<Method> methods) {
        Collections.sort(methods, new Comparator<Method>() {
            @Override
            public int compare(final Method o1, final Method o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
    }
}
