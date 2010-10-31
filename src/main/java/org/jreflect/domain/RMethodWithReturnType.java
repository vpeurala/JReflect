package org.jreflect.domain;

public class RMethodWithReturnType<ReturnType> {
    private final String methodName;
    private final Class<ReturnType> returnType;

    public RMethodWithReturnType(final String methodName,
            final Class<ReturnType> returnType) {
        this.methodName = methodName;
        this.returnType = returnType;
    }

    public RMethodWithReturnTypeAndTarget<ReturnType> in(
            final Class<?> targetClass) {
        return new RMethodWithReturnTypeAndTarget<ReturnType>(methodName,
                targetClass, returnType);
    }

    public RMethodWithReturnTypeAndTarget<ReturnType> in(
            final Object targetObject) {
        if (targetObject instanceof Class<?>) {
            return in((Class<?>) targetObject);
        } else {
            return new RMethodWithReturnTypeAndTarget<ReturnType>(methodName,
                    targetObject, returnType);
        }
    }
}
