package org.jreflect.engine;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jreflect.domain.ReflectException;
import org.jreflect.domain.ReflectException.FailureType;
import org.jreflect.domain.ReflectException.InvocationType;
import org.jreflect.domain.ReflectException.TargetType;

public class Methods {
    public static void invokeVoidMethod(final Class<?> targetClass,
            final String methodName, final Object... args) {
        invokeMethod(null,
                getAccessibleMethodOfClass(targetClass, methodName, args), args);
    }

    public static void invokeVoidMethod(final Object targetObject,
            final String methodName, final Object... args) {
        invokeMethod(targetObject,
                getAccessibleMethodOfObject(targetObject, methodName, args),
                args);
    }

    @SuppressWarnings("unchecked")
    public static <ReturnType> ReturnType invokeValueReturningMethod(
            final Object targetObject, final String methodName,
            final Object... args) {
        return (ReturnType) invokeMethod(targetObject,
                getAccessibleMethodOfObject(targetObject, methodName, args),
                args);
    }

    @SuppressWarnings("unchecked")
    public static <ReturnType> ReturnType invokeValueReturningMethod(
            final Class<?> targetClass, final String methodName,
            final Object... args) {
        return (ReturnType) invokeMethod(null,
                getAccessibleMethodOfClass(targetClass, methodName, args), args);
    }

    @SuppressWarnings("unchecked")
    public static <ReturnType> ReturnType invokeMethod(
            final Object targetObject, final Method targetMethod,
            final Object... args) {
        try {
            return (ReturnType) targetMethod.invoke(targetObject, args);
        } catch (final IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (final IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (final InvocationTargetException e) {
            final Throwable cause = e.getCause();
            if (cause instanceof RuntimeException) {
                throw (RuntimeException) cause;
            } else if (cause instanceof Error) {
                throw (Error) cause;
            } else {
                throw new ReflectException(e.getCause());
            }
        }
    }

    public static Method getAccessibleMethodOfObject(final Object targetObject,
            final String methodName, final Object[] args) {
        final Method foundMethod = findMatchingMethodAndSetAccessible(
                targetObject.getClass(), methodName, args);
        if (foundMethod == null) {
            throw new ReflectException(TargetType.METHOD,
                    InvocationType.INSTANCE,
                    // FIXME VP This is wrong
                    FailureType.NOT_FOUND_BY_NAME, targetObject);
        } else {
            return foundMethod;
        }
    }

    public static Method getAccessibleMethodOfClass(final Class<?> targetClass,
            final String methodName, final Object[] args) {
        final Method method = findMatchingMethodAndSetAccessible(targetClass,
                methodName, args);
        if (method == null) {
            throw new ReflectException(TargetType.METHOD,
                    InvocationType.STATIC,
                    // FIXME VP This is wrong
                    FailureType.NOT_FOUND_BY_NAME, targetClass);
        } else {
            return method;
        }
    }

    public static Method findMatchingMethodAndSetAccessible(
            final Class<?> targetClass, final String methodName,
            final Object[] args) {
        final Arguments arguments = new Arguments(args);
        final List<Method> methods = allMethodsOfClassAndSuperclasses(targetClass);
        for (final Method method : methods) {
            if (methodName.equals(method.getName())
                    && arguments.matchesMethod(method)) {
                AllMembers.setAccessible(method);
                return method;
            }
        }
        return null;
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
        return methods;
    }
}
