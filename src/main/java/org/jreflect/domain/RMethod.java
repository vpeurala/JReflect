package org.jreflect.domain;

public class RMethod {
    private final String name;

    public RMethod(final String name) {
        this.name = name;
    }

    public RMethodWithTarget in(final Class<?> targetClass) {
        return new RMethodWithTarget(name, targetClass);
    }

    public RMethodWithTarget in(final Object targetObject) {
        if (targetObject instanceof Class<?>) {
            return in((Class<?>) targetObject);
        } else {
            return new RMethodWithTarget(name, targetObject);
        }
    }

    public <ReturnType> RMethodWithReturnType<ReturnType> withReturnType(
            @SuppressWarnings("unused") final Class<ReturnType> returnType) {
        return new RMethodWithReturnType<ReturnType>(name);
    }
}
