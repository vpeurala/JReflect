package org.jreflect;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public static void setStaticFieldValue(final Class<?> targetClass,
            final String fieldName, final Object value) {
        setFieldValue(null, getAccessibleFieldOfClass(targetClass, fieldName),
                value);
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

    public static <T> T invokeConstructor(final Class<T> targetClass,
            final Object... args) {
        return invokeConstructor(
                getAccessibleConstructorOfClass(targetClass, args), args);
    }

    public static <T> T invokeConstructor(
            final Constructor<T> targetConstructor, final Object... args) {
        try {
            return targetConstructor.newInstance(args);
        } catch (final IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (final InstantiationException e) {
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
        final List<Field> fields = allFieldsOfClassAndSuperclasses(targetClass);
        for (final Field field : fields) {
            if (field.getName().equals(fieldName)) {
                setAccessible(field);
                return field;
            }
        }
        throw new RuntimeException("No field named '" + fieldName
                + "' found on class " + targetClass
                + " or its superclass hierarchy.");
    }

    public static Method getAccessibleMethodOfObject(final Object targetObject,
            final String methodName, final Object[] args) {
        return getAccessibleMethodOfClass(targetObject.getClass(), methodName,
                args);
    }

    public static Method getAccessibleMethodOfClass(final Class<?> targetClass,
            final String methodName, final Object[] args) {
        final Arguments arguments = new Arguments(args);
        final List<Method> methods = allMethodsOfClassAndSuperclasses(targetClass);
        for (final Method method : methods) {
            if (methodName.equals(method.getName())
                    && arguments.matchesMethod(method)) {
                setAccessible(method);
                return method;
            }
        }
        throw new RuntimeException("There is no method named '" + methodName
                + "' with arguments compatible with " + arguments
                + " in target class " + targetClass
                + " or its superclass hierarchy. Methods tried: " + methods);
    }

    public static <T> Constructor<T> getAccessibleConstructorOfClass(
            final Class<T> targetClass, final Object[] args) {
        final Arguments arguments = new Arguments(args);
        final List<Constructor<T>> constructors = allConstructorsOfClass(targetClass);
        for (final Constructor<T> constructor : constructors) {
            if (arguments.matchesConstructor(constructor)) {
                setAccessible(constructor);
                return constructor;
            }
        }
        throw new RuntimeException(
                "There is no constructor with arguments compatible with "
                        + arguments + " in target class " + targetClass
                        + ". Constructors tried: " + constructors);
    }

    public static void setAccessible(final AccessibleObject accessibleObject) {
        if (!accessibleObject.isAccessible()) {
            accessibleObject.setAccessible(true);
        }
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

    public static List<Field> allFieldsOfClassAndSuperclasses(
            final Class<?> targetClass) {
        final List<Field> fields = new ArrayList<Field>();
        Class<?> currentClass = targetClass;
        while (currentClass != null) {
            fields.addAll(Arrays.<Field> asList(currentClass
                    .getDeclaredFields()));
            currentClass = currentClass.getSuperclass();
        }
        return fields;
    }

    @SuppressWarnings("unchecked")
    public static <T> List<Constructor<T>> allConstructorsOfClass(
            final Class<T> targetClass) {
        final List<Constructor<T>> constructors = new ArrayList<Constructor<T>>();
        constructors.addAll(Arrays
                .<Constructor<T>> asList((Constructor<T>[]) targetClass
                        .getConstructors()));
        constructors.addAll(Arrays
                .<Constructor<T>> asList((Constructor<T>[]) targetClass
                        .getDeclaredConstructors()));
        return constructors;
    }
}
