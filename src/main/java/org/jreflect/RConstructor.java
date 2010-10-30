package org.jreflect;

import static org.jreflect.ReflectionEngine.invokeConstructor;

public class RConstructor<T> {
    private final Class<T> targetClass;

    public RConstructor(final Class<T> targetClass) {
        this.targetClass = targetClass;
    }

    public T invoke(final Object... args) {
        return invokeConstructor(targetClass, args);
    }
}
