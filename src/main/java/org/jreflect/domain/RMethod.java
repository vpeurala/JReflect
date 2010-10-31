package org.jreflect.domain;

public class RMethod {
    private final String methodName;

    public RMethod(final String name) {
        methodName = name;
    }

    public RMethodWithTarget in(final Class<?> targetClass) {
        return new RMethodWithTarget(methodName, targetClass);
    }

    public RMethodWithTarget in(final Object targetObject) {
        if (targetObject instanceof Class<?>) {
            return in((Class<?>) targetObject);
        } else {
            return new RMethodWithTarget(methodName, targetObject);
        }
    }

    public <ReturnType> RMethodWithReturnType<ReturnType> withReturnType(
            final Class<ReturnType> returnType) {
        return new RMethodWithReturnType<ReturnType>(methodName, returnType);
    }
}
