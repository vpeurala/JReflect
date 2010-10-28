package org.jreflect;

import static java.util.Arrays.asList;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class ReflectionEngine {
    @SuppressWarnings("unchecked")
    public static <T> T getFieldValue(final Object targetObject,
            final String fieldName) {
        return (T) getFieldValue(targetObject,
                getAccessibleField(targetObject, fieldName));
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
                getAccessibleField(targetObject, fieldName), value);
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
        final Method targetMethod = getAccessibleMethod(targetClass,
                methodName, args);
        try {
            targetMethod.invoke(null, args);
        } catch (final IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (final IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (final InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static void invokeVoidMethod(final Object targetObject,
            final String methodName, final Object... args) {
        final Method targetMethod = getAccessibleMethod(targetObject,
                methodName, args);
        try {
            targetMethod.invoke(targetObject, args);
        } catch (final IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (final IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (final InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static <ReturnType> ReturnType invokeValueReturningMethod(
            final Object targetObject, final String methodName,
            final Object... args) {
        final Method targetMethod = getAccessibleMethod(targetObject,
                methodName, args);
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

    @SuppressWarnings("unchecked")
    public static <ReturnType> ReturnType invokeValueReturningMethod(
            final Class<?> targetClass, final String methodName,
            final Object... args) {
        final Method targetMethod = getAccessibleMethod(targetClass,
                methodName, args);
        try {
            return (ReturnType) targetMethod.invoke(null, args);
        } catch (final IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (final IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (final InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static Field getAccessibleField(final Object targetObject,
            final String fieldName) {
        final Class<? extends Object> targetClass = targetObject.getClass();
        Field targetField;
        try {
            targetField = targetClass.getDeclaredField(fieldName);
        } catch (final SecurityException e) {
            throw new RuntimeException(e);
        } catch (final NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        if (!targetField.isAccessible()) {
            targetField.setAccessible(true);
        }
        return targetField;
    }

    private static Method getAccessibleMethod(final Object targetObject,
            final String methodName, final Object[] args) {
        return getAccessibleMethod(targetObject.getClass(), methodName, args);
    }

    private static Method getAccessibleMethod(final Class<?> targetClass,
            final String methodName, final Object[] args) {
        final Arguments arguments = new Arguments(args);
        Method targetMethod = null;
        try {
            for (final Method m : targetClass.getDeclaredMethods()) {
                if (methodName.equals(m.getName())
                        && arguments.matchesMethod(m)) {
                    targetMethod = m;
                    break;
                }
            }
        } catch (final SecurityException e) {
            throw new RuntimeException(e);
        }
        if (targetMethod == null) {
            throw new RuntimeException("There is no method named '"
                    + methodName + "' with arguments compatible with "
                    + arguments + " in target class " + targetClass
                    + ". Methods tried: "
                    + asList(targetClass.getDeclaredMethods()));
        }
        if (!targetMethod.isAccessible()) {
            targetMethod.setAccessible(true);
        }
        return targetMethod;
    }
}
