package org.jreflect;

public class RMethodWithReturnType<ReturnType> {
    private final String name;

    public RMethodWithReturnType(final String name) {
        this.name = name;
    }

    public RMethodWithReturnTypeAndTarget<ReturnType> in(
            final Class<?> targetClass) {
        return new RMethodWithReturnTypeAndTarget<ReturnType>(name, targetClass);
    }

    public RMethodWithReturnTypeAndTarget<ReturnType> in(
            final Object targetObject) {
        return new RMethodWithReturnTypeAndTarget<ReturnType>(name,
                targetObject);
    }
}
