package org.jreflect;

public class RFieldWithType<T> {
    private final String name;

    public RFieldWithType(final String name) {
        this.name = name;
    }

    public RFieldWithTargetAndType<T> in(final Class<?> targetClass) {
        return new RFieldWithTargetAndType<T>(name, targetClass);
    }

    public RFieldWithTargetAndType<T> in(final Object targetObject) {
        return new RFieldWithTargetAndType<T>(name, targetObject);
    }
}
