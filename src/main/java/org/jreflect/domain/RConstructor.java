package org.jreflect.domain;

import static org.jreflect.engine.Constructors.invokeConstructor;

public class RConstructor<T> {
    private final Class<T> targetClass;

    public RConstructor(final Class<T> targetClass) {
        this.targetClass = targetClass;
    }

    public T invoke(final Object... args) {
        return invokeConstructor(targetClass, args);
    }
}
