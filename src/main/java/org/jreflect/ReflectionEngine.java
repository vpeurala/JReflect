package org.jreflect;

import static java.util.Arrays.asList;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class ReflectionEngine {
    @SuppressWarnings("unchecked")
    public static <T> T getStaticFieldValue(final Class<?> targetClass,
            final String fieldName) {
        return (T) getStaticFieldValue(getAccessibleFieldOfClass(targetClass,
                fieldName));
    }

    @SuppressWarnings("unchecked")
    public static <T> T getFieldValue(final Object targetObject,
            final String fieldName) {
        return (T) getFieldValue(targetObject,
                getAccessibleFieldOfObject(targetObject, fieldName));
    }

    @SuppressWarnings("unchecked")
    public static <T> T getStaticFieldValue(final Field targetField) {
        return (T) getFieldValue(null, targetField);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getFieldValue(final Object targetObject,
            final Field targetField) {
        try {
            return (T) targetField.get(targetObject);
        } catch (final IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (final IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setFieldValue(final Object targetObject,
            final String fieldName, final Object value) {
        setFieldValue(targetObject,
                getAccessibleFieldOfObject(targetObject, fieldName), value);
    }

    public static void setFieldValue(final Object targetObject,
            final Field targetField, final Object value) {
        try {
            targetField.set(targetObject, value);
        } catch (final IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (final IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

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
            throw new RuntimeException(e);
        }
    }

    public static Field getAccessibleFieldOfObject(final Object targetObject,
            final String fieldName) {
        return getAccessibleFieldOfClass(targetObject.getClass(), fieldName);
    }

    public static Field getAccessibleFieldOfClass(final Class<?> targetClass,
            final String fieldName) {
        try {
            final Field targetField = targetClass.getDeclaredField(fieldName);
            setAccessible(targetField);
            return targetField;
        } catch (final SecurityException e) {
            throw new RuntimeException(e);
        } catch (final NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public static Method getAccessibleMethodOfObject(final Object targetObject,
            final String methodName, final Object[] args) {
        return getAccessibleMethodOfClass(targetObject.getClass(), methodName,
                args);
    }

    public static Method getAccessibleMethodOfClass(final Class<?> targetClass,
            final String methodName, final Object[] args) {
        final Arguments arguments = new Arguments(args);
        try {
            for (final Method m : targetClass.getDeclaredMethods()) {
                if (methodName.equals(m.getName())
                        && arguments.matchesMethod(m)) {
                    setAccessible(m);
                    return m;
                }
            }
        } catch (final SecurityException e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("There is no method named '" + methodName
                + "' with arguments compatible with " + arguments
                + " in target class " + targetClass + ". Methods tried: "
                + asList(targetClass.getDeclaredMethods()));
    }

    public static void setAccessible(final AccessibleObject accessibleObject) {
        if (!accessibleObject.isAccessible()) {
            accessibleObject.setAccessible(true);
        }
    }
}
