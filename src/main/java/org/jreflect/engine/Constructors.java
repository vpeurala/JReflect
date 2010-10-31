package org.jreflect.engine;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jreflect.exception.ReflectException;

public class Constructors {
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

    public static <T> Constructor<T> getAccessibleConstructorOfClass(
            final Class<T> targetClass, final Object[] args) {
        final Arguments arguments = new Arguments(args);
        final List<Constructor<T>> constructors = allConstructorsOfClass(targetClass);
        for (final Constructor<T> constructor : constructors) {
            if (arguments.matchesConstructor(constructor)) {
                AllMembers.setAccessible(constructor);
                return constructor;
            }
        }
        throw new RuntimeException(
                "There is no constructor with arguments compatible with "
                        + arguments + " in target class " + targetClass
                        + ". Constructors tried: " + constructors);
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
}
