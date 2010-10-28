package org.jreflect;

import static org.jreflect.ReflectionEngine.invokeVoidMethod;

public class RMethodWithTarget {
    private final String name;
    private final Object targetObject;
    private final Class<?> targetClass;

    public RMethodWithTarget(final String name, final Class<?> targetClass) {
        this(name, null, targetClass);
    }

    public RMethodWithTarget(final String name, final Object targetObject) {
        this(name, targetObject, null);
    }

    private RMethodWithTarget(final String name, final Object targetObject,
            final Class<?> targetClass) {
        this.name = name;
        this.targetObject = targetObject;
        this.targetClass = targetClass;
    }

    public void invoke(final Object... args) {
        if (targetObject != null) {
            invokeVoidMethod(targetObject, name, args);
        } else {
            invokeVoidMethod(targetClass, name, args);
        }
    }
}
