package org.jreflect;

public class RMethod {
    private final String name;

    public RMethod(final String name) {
        this.name = name;
    }

    public RMethodWithTarget in(final Class<?> targetClass) {
        return new RMethodWithTarget(name, targetClass);
    }

    public RMethodWithTarget in(final Object targetObject) {
        return new RMethodWithTarget(name, targetObject);
    }

    public <ReturnType> RMethodWithReturnType<ReturnType> withReturnType(
            @SuppressWarnings("unused") final Class<ReturnType> returnType) {
        return new RMethodWithReturnType<ReturnType>(name);
    }
}
