package org.jreflect;

public class RField {
    private final String name;

    public RField(final String name) {
        this.name = name;
    }

    public RFieldWithTarget in(final Object targetObject) {
        return new RFieldWithTarget(name, targetObject);
    }

    public <T> RFieldWithType<T> ofType(
            @SuppressWarnings("unused") final Class<T> type) {
        return new RFieldWithType<T>(name);
    }
}
