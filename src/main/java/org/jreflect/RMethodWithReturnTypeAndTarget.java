package org.jreflect;

import static org.jreflect.ReflectionEngine.invokeValueReturningMethod;

public class RMethodWithReturnTypeAndTarget<ReturnType> {
    private final String name;
    private final Object targetObject;
    private final Class<?> targetClass;

    public RMethodWithReturnTypeAndTarget(final String name,
            final Class<?> targetClass) {
        this(name, null, targetClass);
    }

    public RMethodWithReturnTypeAndTarget(final String name,
            final Object targetObject) {
        this(name, targetObject, null);
    }

    private RMethodWithReturnTypeAndTarget(final String name,
            final Object targetObject, final Class<?> targetClass) {
        this.name = name;
        this.targetObject = targetObject;
        this.targetClass = targetClass;
    }

    @SuppressWarnings("unchecked")
    public ReturnType invoke(final Object... args) {
        if (targetObject != null) {
            return (ReturnType) invokeValueReturningMethod(targetObject, name,
                    args);
        } else {
            return (ReturnType) invokeValueReturningMethod(targetClass, name,
                    args);
        }
    }
}
