package org.jreflect;


public abstract class JReflect {
    public static RField field(final String name) {
        return new RField(name);
    }

    public static RMethod method(final String name) {
        return new RMethod(name);
    }

    public static <T> RConstructor<T> constructorOf(final Class<T> targetClass) {
        return new RConstructor<T>(targetClass);
    }
}
