package org.jreflect;

public class RFieldWithType<T> {
    private final String name;

    public RFieldWithType(String name) {
        this.name = name;
    }

    public RFieldWithTargetAndType<T> in(Object targetObject) {
        return new RFieldWithTargetAndType<T>(name, targetObject);
    }
}
